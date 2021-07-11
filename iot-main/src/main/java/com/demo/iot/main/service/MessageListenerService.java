package com.demo.iot.main.service;

import com.demo.iot.main.config.RabbitMqConfig;
import com.demo.iot.main.entity.DeviceDataEntity;
import com.demo.iot.main.repository.DeviceDataRepository;
import com.demo.iot.shared.dto.DeviceData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MessageListenerService {

    private final DeviceDataRepository deviceDataRepository;
    private final ApplicationEventPublisher applicationEventPublisher;

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage(final DeviceData deviceData) {
        DeviceDataEntity savedData = deviceDataRepository.save(new DeviceDataEntity(deviceData.getType(), deviceData.getValue()));
        applicationEventPublisher.publishEvent(savedData);
    }

    @RabbitListener(queues = RabbitMqConfig.QUEUE_NAME)
    public void receiveMessage(final Message message) {
        log.info("Received generic message: {}", message.toString());
    }
}
