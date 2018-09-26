package com.demo.iot.device.devices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Profile("Temperature")
public class TemperatureDevice extends Device {

    private Random random = new Random();

    public TemperatureDevice() {
        super("Temperature");
    }

    @Override
    long getValue() {
        return random.longs(1, -10, 35).findFirst().getAsLong();
    }
}
