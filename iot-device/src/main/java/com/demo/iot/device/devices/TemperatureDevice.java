package com.demo.iot.device.devices;

import com.demo.iot.device.dto.DeviceData;
import org.springframework.stereotype.Component;

@Component
public class TemperatureDevice implements Device {
    
    @Override
    public DeviceData read() {
        return null;
    }
}
