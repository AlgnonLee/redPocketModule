package org.ms_demo.consumer;

import com.alibaba.fastjson.JSON;
import org.ms_demo.config.InstantRedPocketConfig;
import org.ms_demo.entity.RedPocket;
import org.ms_demo.services.RedPocketService;
import org.ms_demo.util.JedisUtil;
import org.ms_demo.util.LockUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RabbitListener(queues = InstantRedPocketConfig.INSTANT_RED_POCKET_QUEUE)
public class RedPocketConsumer {


    @Autowired
    private RedPocketService redPocketService;

    @RabbitHandler
    public void receive(RedPocket redPocket) throws InterruptedException {
        System.out.println("Direct Queue Received: " + redPocket);
        boolean lock;
        do {
            Thread.sleep(50);
            lock = LockUtil.lock(String.valueOf(redPocket.getUid()));
        }while (!lock);
        float balance = Float.parseFloat(JedisUtil.hget("accounts", String.valueOf(redPocket.getUid())));
        if(balance < redPocket.getPocketMoney()){
            System.out.println("余额不足");
            return;
        }else {
            JedisUtil.hset("accounts",String.valueOf(redPocket.getUid()),String.valueOf(balance - redPocket.getPocketMoney()));
            JedisUtil.hset("red_pockets",redPocket.getUuid(), JSON.toJSONString(redPocket));
        }
        LockUtil.unlock(String.valueOf(redPocket.getUid()));
    }

}
