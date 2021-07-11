package com.demo.iot.main.controller;

import com.demo.iot.main.entity.DeviceDataEntity;
import com.demo.iot.main.repository.DeviceDataRepository;
import com.demo.iot.main.service.SensorService;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class SensorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private SensorService sensorService;

    @Autowired
    private DeviceDataRepository deviceDataRepository;

    @BeforeEach
    public void beforeEach() {
        deviceDataRepository.deleteAll();
    }

    @Test
    public void testGetSensorData() throws Exception {
        DeviceDataEntity deviceData = new DeviceDataEntity("type", 64984687L);
        deviceDataRepository.save(deviceData);

        mockMvc.perform(get("/api/v1/sensor/"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.numberOfElements", Is.is(1)))
            .andExpect(jsonPath("$.content[0].value", Is.is(64984687)));
    }

    @Test
    public void testGetCount() throws Exception {
        DeviceDataEntity deviceData = new DeviceDataEntity("type", 12345L);
        deviceDataRepository.save(deviceData);

        mockMvc.perform(get("/api/v1/sensor/count/"))
            .andExpect(status().isOk())
            .andExpect(content().string("1"));
    }

    @Test
    public void testSessionCount() throws Exception {
        sensorService.increment();
        sensorService.increment();

        mockMvc.perform(get("/api/v1/sensor/session/count/"))
            .andExpect(status().isOk())
            .andExpect(content().string("2"));
    }

    @Test
    public void testGetLastMinuteAverageForSensorType() throws Exception {
        deviceDataRepository.save(new DeviceDataEntity("Temperature", 5L));
        deviceDataRepository.save(new DeviceDataEntity("Temperature", 5L));
        deviceDataRepository.save(new DeviceDataEntity("Temperature", 7L));
        deviceDataRepository.save(new DeviceDataEntity("Temperature", 7L));

        mockMvc.perform(get("/api/v1/sensor/average?type=Temperature"))
            .andExpect(status().isOk())
            .andExpect(content().string("6"));
    }

    @Test
    public void testGetLastMinuteAverageForSensorTypeNoData() throws Exception {
        deviceDataRepository.save(new DeviceDataEntity("Temperature", 5L));

        mockMvc.perform(get("/api/v1/sensor/average?type=wrongSensorType"))
            .andExpect(status().isOk())
            .andExpect(content().string("0"));
    }

}
