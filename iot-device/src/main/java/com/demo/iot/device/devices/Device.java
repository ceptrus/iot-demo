package com.demo.iot.device.devices;

import com.demo.iot.shared.dto.DeviceData;

public abstract class Device {

    private final String type;

    public Device(String type) {
        this.type = type;
    }

    public DeviceData read() {
        return new DeviceData(getValue(), this.type);
    }

    abstract long getValue();

}
