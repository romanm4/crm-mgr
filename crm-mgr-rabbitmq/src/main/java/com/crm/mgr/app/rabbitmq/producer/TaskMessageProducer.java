package com.crm.mgr.app.rabbitmq.producer;

import com.crm.mgr.app.rabbitmq.property.RabbitMqConfigProperties;
import com.crm.mgr.dto.TaskDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class TaskMessageProducer {
    private static final Logger log = LoggerFactory.getLogger(TaskMessageProducer.class);
    private final RabbitMqConfigProperties rabbitMqConfigProperties;
    private final RabbitTemplate rabbitTemplate;

    public TaskMessageProducer(RabbitMqConfigProperties rabbitMqConfigProperties, RabbitTemplate rabbitTemplate) {
        this.rabbitMqConfigProperties = rabbitMqConfigProperties;
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendTaskMessage(TaskDto task) {
        log.info("AMQP Message [--->] Body {}", task);
        rabbitTemplate.convertAndSend(rabbitMqConfigProperties.getExchange(), rabbitMqConfigProperties.getRoutingKey(), task);
    }
}