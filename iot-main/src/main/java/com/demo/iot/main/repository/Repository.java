package com.demo.iot.main.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface Repository extends MongoRepository<DeviceDataModel, String> {
}
