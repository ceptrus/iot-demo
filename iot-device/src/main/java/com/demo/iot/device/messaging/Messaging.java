package com.demo.iot.device.messaging;

import com.demo.iot.device.dto.DeviceData;

public interface Messaging {
    void sendMessage(DeviceData data);
    boolean isActive();
}
