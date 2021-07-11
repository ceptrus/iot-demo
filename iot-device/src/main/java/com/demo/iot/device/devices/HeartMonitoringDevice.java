package com.demo.iot.device.devices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Profile("HeartMonitor")
public class HeartMonitoringDevice extends Device {

    private final Random random = new Random();

    public HeartMonitoringDevice() {
        super("HeartMonitor");
    }

    @Override
    long getValue() {
        return random.longs(1, 60, 160)
            .findFirst()
            .getAsLong();
    }
}
