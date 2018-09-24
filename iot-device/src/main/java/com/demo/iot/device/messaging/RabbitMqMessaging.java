package com.demo.iot.device.messaging;

import com.demo.iot.device.dto.DeviceData;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqMessaging implements Messaging {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(DeviceData data) {
        byte[] bytes = data.getMessage().getBytes();

        rabbitTemplate.send("amq.fanout", null, MessageBuilder.withBody(bytes).build());
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
