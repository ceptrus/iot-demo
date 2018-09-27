package com.demo.iot.main.repository;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "DeviceData")
public class DeviceDataModel {

    @Id
    private String id;

    @Indexed
    private String type;

    private long value;

    @CreatedDate
    private Date createdDate;

    public DeviceDataModel(String type, long value) {
        this.type = type;
        this.value = value;
    }
}
