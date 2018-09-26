package com.demo.iot.device.config;

import com.demo.iot.device.dto.DeviceData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Receiver implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            DeviceData data = new ObjectMapper().readValue(message.getBody(), DeviceData.class);

            System.out.println(data.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
