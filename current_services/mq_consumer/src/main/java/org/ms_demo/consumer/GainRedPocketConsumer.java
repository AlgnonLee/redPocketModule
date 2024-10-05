package org.ms_demo.consumer;

import com.alibaba.fastjson.JSON;
import org.ms_demo.config.GainRedPocketConfig;
import org.ms_demo.entity.RedPocket;
import org.ms_demo.util.JedisUtil;
import org.ms_demo.util.LockUtil;
import org.ms_demo.util.RandomUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author AutumnLeaf
 * @date 2024/10/4
 * @Description
 */
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
                        JedisUtil.hset("red_pockets", redPocket.getUuid(), JSON.toJSONString(redPocketObject));
                        System.out.println("最后一个红包被抢完啦");

                    }else if(redPocketObject.getRestPocketCount()>1){
                        double gainMoney = RandomUtil.getRandomDouble(pocketMoney);
                        redPocketObject.setPocketMoney(pocketMoney-gainMoney);
                        redPocketObject.setRestPocketCount(redPocketObject.getRestPocketCount()-1);
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
