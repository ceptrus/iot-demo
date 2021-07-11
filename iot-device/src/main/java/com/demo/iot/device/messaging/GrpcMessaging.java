package com.demo.iot.device.messaging;

import com.demo.iot.shared.dto.DeviceData;
import org.springframework.stereotype.Component;

@Component
public class GrpcMessaging implements Messaging {

    @Override
    public void sendMessage(DeviceData data) {
        throw new RuntimeException("Not implemented yet");
    }

    @Override
    public boolean isActive() {
        return false;
    }
}
