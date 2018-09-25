package com.demo.iot.main.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class Receiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println("Received <" + new String(message.getBody()) + ">");
    }
}
