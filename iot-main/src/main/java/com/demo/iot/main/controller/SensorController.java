package com.demo.iot.main.controller;

import com.demo.iot.main.dto.DeviceDataDto;
import com.demo.iot.main.mapper.DeviceDataEntityToDtoMapper;
import com.demo.iot.main.service.SensorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "ReadOnly controller for sensor data")
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/sensor")
public class SensorController {

    private final SensorService sensorService;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Results page you want to retrieve (0..N)"),
        @ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Number of records per page."),
        @ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query",
            value = "Sorting criteria in the format: property(,asc|desc). Default sort order is ascending. Multiple sort criteria are supported.")
    })
    @GetMapping("/")
    public Page<DeviceDataDto> getSensorsData(@ApiIgnore Pageable page) {
        return sensorService.getSensorsData(page)
            .map(DeviceDataEntityToDtoMapper::toDto);
    }

    @GetMapping("/average")
    public long getLastMinuteAverageForSensorType(String type) {
        return sensorService.getLastMinuteAverageForSensorType(type);
    }

    @GetMapping("/session/count")
    public long getSessionCount() {
        return sensorService.getSessionCount();
    }

    @GetMapping("/count")
    public long getCount() {
        return sensorService.getCount();
    }
}
