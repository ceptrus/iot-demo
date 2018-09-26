package com.demo.iot.device.service;

import com.demo.iot.device.devices.Device;
import com.demo.iot.device.messaging.Messaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeviceService {

    @Autowired
    private List<Messaging> messagingList;

    @Autowired
    private Device device;

    @Scheduled(fixedRate = 500)
    public void sendDeviceData() {
        messagingList.stream()
                .filter(Messaging::isActive)
                .forEach(messaging -> messaging.sendMessage(device.read()));
    }
}
