package com.demo.iot.main.repository;

import com.demo.iot.main.entity.DeviceDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;

@Repository
public interface DeviceDataRepository extends MongoRepository<DeviceDataEntity, String> {
    Page<DeviceDataEntity> findAllByTypeAndCreatedAfter(String type, Instant createdDate, Pageable page);
}
