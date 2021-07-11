package com.demo.iot.main.service;

import com.demo.iot.main.entity.DeviceDataEntity;
import com.demo.iot.main.repository.DeviceDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@RequiredArgsConstructor
@Service
public class SensorService {

    private final DeviceDataRepository deviceDataRepository;

    private final AtomicLong totalEntries = new AtomicLong(0);

    public Page<DeviceDataEntity> getSensorsData(Pageable page) {
        return deviceDataRepository.findAll(page);
    }

    public long increment() {
        return totalEntries.incrementAndGet();
    }

    public long getCount() {
        return deviceDataRepository.count();
    }

    public long getSessionCount() {
        return totalEntries.get();
    }

    public long getLastMinuteAverageForSensorType(String type) {
        Pageable pageable = PageRequest.of(0, 500);
        Instant nowMinus60s = Instant.now().minusSeconds(60);

        return (long) deviceDataRepository.findAllByTypeAndCreatedAfter(type, nowMinus60s, pageable)
            .stream()
            .mapToLong(DeviceDataEntity::getValue)
            .average()
            .orElse(0);
    }
}
