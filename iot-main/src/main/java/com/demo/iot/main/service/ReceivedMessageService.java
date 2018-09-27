package com.demo.iot.main.service;

import com.demo.iot.main.dto.DeviceData;
import com.demo.iot.main.repository.DeviceDataModel;
import com.demo.iot.main.repository.Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ReceivedMessageService implements MessageListener {

    @Autowired
    private Repository repository;

    @Override
    public void onMessage(Message message) {
        try {
            DeviceData data = new ObjectMapper().readValue(message.getBody(), DeviceData.class);

            System.out.println(data.toString());

            repository.save(new DeviceDataModel(data.getType(), data.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
