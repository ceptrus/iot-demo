package com.demo.iot.device.service;

import com.demo.iot.device.devices.Device;
import com.demo.iot.device.messaging.Messaging;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class DeviceService {

    private final List<Messaging> messagingList;
    private final Device device;

    @Scheduled(fixedRate = 3000)
    public void sendDeviceData() {
        messagingList.stream()
            .filter(Messaging::isActive)
            .forEach(messaging -> messaging.sendMessage(device.read()));
    }
}
