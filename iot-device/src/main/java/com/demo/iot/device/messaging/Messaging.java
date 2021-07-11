package com.demo.iot.device.messaging;

import com.demo.iot.shared.dto.DeviceData;

public interface Messaging {
    void sendMessage(DeviceData data);

    /**
     * This value can be dynamically configured using system environment variables, DB, application.properties, etc..
     *
     * @return if messages should be sent using this messaging type
     */
    boolean isActive();
}
