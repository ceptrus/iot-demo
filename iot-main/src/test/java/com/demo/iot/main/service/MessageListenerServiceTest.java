package com.demo.iot.main.service;

import com.demo.iot.main.entity.DeviceDataEntity;
import com.demo.iot.main.repository.DeviceDataRepository;
import com.demo.iot.shared.dto.DeviceData;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MessageListenerServiceTest {

    @Mock
    private DeviceDataRepository deviceDataRepository;

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private MessageListenerService messageListenerService;

    @Test
    public void shouldReceiveMessage() {
        DeviceData deviceData = new DeviceData(123L, "type");
        DeviceDataEntity type = new DeviceDataEntity("type", 123L);

        when(deviceDataRepository.save(type)).thenReturn(type);

        messageListenerService.receiveMessage(deviceData);

        verify(applicationEventPublisher).publishEvent(type);
    }
}
