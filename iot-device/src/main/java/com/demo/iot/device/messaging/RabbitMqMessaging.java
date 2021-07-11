package com.demo.iot.device.messaging;

import com.demo.iot.device.config.RabbitMqConfig;
import com.demo.iot.shared.dto.DeviceData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class RabbitMqMessaging implements Messaging {

    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(DeviceData data) {
        rabbitTemplate.convertAndSend(RabbitMqConfig.TOPIC_EXCHANGE_NAME, null, data);
        log.info("Message sent.");
    }

    @Override
    public boolean isActive() {
        return true;
    }
}
