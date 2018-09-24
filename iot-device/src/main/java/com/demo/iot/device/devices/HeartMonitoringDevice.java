package com.demo.iot.device.devices;

import com.demo.iot.device.dto.DeviceData;
import org.springframework.stereotype.Component;

@Component
public class HeartMonitoringDevice implements Device {

    @Override
    public DeviceData read() {
        return new DeviceData("this is a message");
    }
}
