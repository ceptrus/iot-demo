package com.demo.iot.device.messaging;

import com.demo.iot.device.dto.DeviceData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
        try {
            byte[] bytes = new ObjectMapper().writeValueAsBytes(data);
            rabbitTemplate.send("amq.fanout", null, MessageBuilder.withBody(bytes).build());
            System.out.println("Message sent.");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
