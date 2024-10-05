package org.ms_demo.service;

import org.ms_demo.config.DelayedRedPocketConfig;
import org.ms_demo.config.GainRedPocketConfig;
import org.ms_demo.config.InstantRedPocketConfig;
import org.ms_demo.entity.RedPocket;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class ProviderService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(RedPocket redPocket) {
        rabbitTemplate.convertAndSend(InstantRedPocketConfig.INSTANT_RED_POCKET_EXCHANGE,InstantRedPocketConfig.INSTANT_RED_POCKET_QUEUE,redPocket);
    }

    public void sendDelayedMessage(RedPocket redPocket) {
        rabbitTemplate.convertAndSend(DelayedRedPocketConfig.DELAYED_RED_POCKET_EXCHANGE, DelayedRedPocketConfig.DELAYED_RED_POCKET_QUEUE, redPocket, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay",6000*1000);
                return message;
            }
        });
    }

    public void sendGainRedPocketMessage(RedPocket redPocket,int uid){
        HashMap<String, Object> messageMap = new HashMap<>();
        messageMap.put("uid",uid);
        messageMap.put("RedPocket",redPocket);
        rabbitTemplate.convertAndSend(GainRedPocketConfig.GAIN_INSTANT_RED_POCKET_EXCHANGE,GainRedPocketConfig.GAIN_INSTANT_RED_POCKET_QUEUE,messageMap);
    }
}
