package org.ms_demo.consumer;

import com.alibaba.fastjson.JSON;
import org.ms_demo.client.RedPocketClient;
import org.ms_demo.config.DelayedRedPocketConfig;
import org.ms_demo.entity.RedPocket;
import org.ms_demo.util.JedisUtil;
import org.ms_demo.util.LockUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author AutumnLeaf
 * @date 2024/10/2
 * @Description
 */
@Component
@RabbitListener(queues = DelayedRedPocketConfig.DELAYED_RED_POCKET_QUEUE)
public class DelayedRedPocketConsumer {

    @Autowired
    private RedPocketClient redPocketClient;

    @RabbitHandler
    public void process(RedPocket redPocket) throws InterruptedException {
        boolean lock;
        do {
            lock = LockUtil.lock(redPocket.getUuid());
            Thread.sleep(50);
        }while (!lock);
        String redPocket_JSON = JedisUtil.hget("red_pockets", redPocket.getUuid());
        RedPocket redPocket_Class = JSON.parseObject(redPocket_JSON, RedPocket.class);
        System.out.println(redPocket_Class);

        do{
            lock = LockUtil.lock(String.valueOf(redPocket.getUid()));
            Thread.sleep(50);
        }while (!lock);
        String balance = JedisUtil.hget("accounts", String.valueOf(redPocket.getUid()));
        String balance_after = String.valueOf(Double.parseDouble(balance) + redPocket.getPocketMoney());

        redPocketClient.addRedPocketToDb(redPocket_Class);
        JedisUtil.hset("accounts", String.valueOf(redPocket.getUid()),balance_after);
        JedisUtil.hdel("red_pockets", redPocket.getUuid());
        LockUtil.unlock(redPocket.getUuid());
        LockUtil.unlock(String.valueOf(redPocket.getUid()));
        System.out.println("Delayed Message resolved over");
        return;
    }
}
