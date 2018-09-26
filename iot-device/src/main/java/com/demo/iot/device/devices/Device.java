package com.demo.iot.device.devices;

import com.demo.iot.device.dto.DeviceData;

public abstract class Device {

    private String type;

    public Device(String type) {
        this.type = type;
    }

    public DeviceData read() {
        return new DeviceData(getValue(), this.type);
    }

    abstract long getValue();

}
