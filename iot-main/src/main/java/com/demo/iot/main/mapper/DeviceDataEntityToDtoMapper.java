package com.demo.iot.main.mapper;

import com.demo.iot.main.dto.DeviceDataDto;
import com.demo.iot.main.entity.DeviceDataEntity;

public class DeviceDataEntityToDtoMapper {

    public static DeviceDataDto toDto(DeviceDataEntity deviceDataEntity) {
        return new DeviceDataDto(deviceDataEntity.getValue());
    }
}
