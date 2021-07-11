package com.demo.iot.main.service;

import com.demo.iot.main.entity.DeviceDataEntity;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class DataProcessor {

    private final SensorService sensorService;

    @EventListener
    public void processDataEvent(DeviceDataEntity deviceDataEntity) {
        log.info(deviceDataEntity.toString());
        // transform data, etc...
        log.info("{} processed entries", sensorService.increment());
    }
}
