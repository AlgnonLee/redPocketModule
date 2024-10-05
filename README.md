# 商城抢红包模块
商城抢红包模块后端业务逻辑代码，数据预热，流量削峰，分布式锁，RPC
## 技术栈
### SpringBoot
基于Java的开源框架，旨在简化创建独立、生产级的Spring应用程序。它提供了一种快速开发的方法，通过自动配置和约定优于配置的原则，减少了开发人员的配置工作量。Spring Boot还支持嵌入式Web服务器，如Tomcat和Jetty，使得应用程序可以独立运行，无需部署到外部服务器。
### SpringCloud
微服务解决方案，它为基于Spring Boot构建的应用程序提供了一系列的工具和服务，用于构建分布式系统。Spring Cloud包括多个子项目，如Config Server、Eureka、Ribbon、Feign等，这些子项目分别提供了配置管理、服务注册与发现、负载均衡等功能，帮助开发者更容易地构建和管理微服务架构。
### GateWay
基于Spring Framework 5、Project Reactor和Spring Boot 2的API网关。它可以作为微服务架构中的入口点，提供路由、过滤、限流等功能。GateWay支持多种协议，如HTTP、WebSocket等，并可以通过动态路由、熔断器等功能实现高可用性和容错性。
### Redis
开源的内存数据结构存储系统，用作数据库、缓存和消息代理。它支持多种数据结构，如字符串、哈希表、列表、集合、有序集合等。Redis具有高性能、可扩展性和丰富的功能集，广泛应用于各种场景，如缓存、会话管理、实时分析等。
### Redisson
在Redis基础上实现的分布式Java对象和服务的库。它提供了丰富的分布式功能，如分布式锁、分布式集合、分布式队列等。Redisson可以帮助开发者轻松地在分布式环境中实现复杂的并发控制和数据同步。
### RabbitMQ
开源的消息代理和队列服务器，用于实现应用程序之间的异步通信。它支持多种消息协议，如AMQP、STOMP等，并提供了高可用性、持久化、路由、集群等功能。RabbitMQ可以用于解耦系统组件、提高系统的可伸缩性和可靠性。
### Eureka
服务注册与发现组件，主要用于构建高可用的微服务架构。Eureka提供了一个服务注册中心，各个微服务实例可以在启动时向Eureka注册自己的信息，其他服务可以通过Eureka获取到所需服务的地址信息。Eureka还支持心跳检测和自我保护机制，确保服务的可用性。
### OpenFeign
声明式的Web服务客户端，它使得编写Web服务客户端变得更加容易。使用Feign，只需要创建一个接口并注解。在运行时，Feign会为这个接口创建一个代理对象。当你调用这个接口的方法时，Feign会帮你发起HTTP请求，并根据响应返回结果。这样，你只需要关注接口的定义和业务逻辑，而不需要关心底层的HTTP通信细节。
## 分布式锁
通过Redisson封装的分布式锁功能创建，RabbitMQ消息消费者接收到消息时尝试获取锁，获取到红包锁之后再进行其他的数据处理。
### LockUti 分布式锁工具类
```java
public class LockUtil {

    private static final RedissonClient redisson = RedissonConfig.getRedisson();
    private static final String LOCK_KEY_PREFIX = "lock_";

    public static boolean lock(String lockKey) throws InterruptedException {
        String key = LOCK_KEY_PREFIX + lockKey;
        RLock lock = redisson.getLock(key);

        boolean tryLock = lock.tryLock(30, TimeUnit.SECONDS);
        return tryLock;

    }

    public static boolean isMyLock(String lockKey) {
        String key = LOCK_KEY_PREFIX + lockKey;
        RLock lock = redisson.getLock(key);
        return lock.isHeldByCurrentThread();
    }

    public static void unlock(String lockKey) {
        String key = LOCK_KEY_PREFIX + lockKey;
        RLock lock = redisson.getLock(key);
        lock.unlock();
    }
}
```
### 抢红包后端业务逻辑
```java
@Component
@RabbitListener(queues = GainRedPocketConfig.GAIN_INSTANT_RED_POCKET_QUEUE)
public class GainRedPocketConsumer {

    @RabbitHandler
    public void receive(Map message) throws InterruptedException {
        RedPocket redPocket = (RedPocket) message.get("RedPocket");
        int uid = (int) message.get("uid");
        boolean lockRedPocket = LockUtil.lock(redPocket.getUuid());

        while (!lockRedPocket) {
            Thread.sleep(50);
            lockRedPocket = LockUtil.lock(redPocket.getUuid());
        }

        String redPocketJson = JedisUtil.hget("red_pockets", redPocket.getUuid());
        RedPocket redPocketObject = JSON.parseObject(redPocketJson, RedPocket.class);

        try{
            if(redPocketObject.getPocketMoney()>0&&redPocketObject.getRestPocketCount()>0){
                boolean lock = LockUtil.lock(String.valueOf(uid));
                while (!lock) {
                    Thread.sleep(50);
                    lock = LockUtil.lock(String.valueOf(uid));
                }
                double pocketMoney = redPocketObject.getPocketMoney();
                if(redPocketObject.getRedPocketType().equals("random")){
                    if(redPocketObject.getRestPocketCount()==1){
                        double gainMoney = pocketMoney;
                        redPocketObject.setPocketMoney(0);
                        redPocketObject.setRestPocketCount(0);
                        String balance = JedisUtil.hget("accouts", String.valueOf(uid));
                        JedisUtil.hset("accouts", String.valueOf(uid), String.valueOf(Double.parseDouble(balance)+gainMoney));
                        JedisUtil.hset("red_pockets", redPocket.getUuid(), JSON.toJSONString(redPocketObject));
                        System.out.println("最后一个红包被抢完啦");

                    }else if(redPocketObject.getRestPocketCount()>1){
                        double gainMoney = RandomUtil.getRandomDouble(pocketMoney);
                        redPocketObject.setPocketMoney(pocketMoney-gainMoney);
                        redPocketObject.setRestPocketCount(redPocketObject.getRestPocketCount()-1);
                        String balance = JedisUtil.hget("accouts", String.valueOf(uid));
                        JedisUtil.hset("accouts", String.valueOf(uid), String.valueOf(Double.parseDouble(balance)+gainMoney));
                        JedisUtil.hset("red_pockets", redPocket.getUuid(), JSON.toJSONString(redPocketObject));
                        System.out.println("uid为" +uid+ "的用户抢到了红包，数额为" +gainMoney);
                    }
                }
            }else{
                System.out.println("红包已经没有了");
            }
        }finally {
            if(LockUtil.isMyLock(redPocket.getUuid())){
                LockUtil.unlock(redPocket.getUuid());
            }
            if(LockUtil.isMyLock(String.valueOf(uid))){
                LockUtil.unlock(String.valueOf(uid));
            }
        }
    }
}
```
