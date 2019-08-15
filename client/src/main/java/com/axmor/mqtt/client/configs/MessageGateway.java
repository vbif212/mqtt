package com.axmor.mqtt.client.configs;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@MessagingGateway(defaultRequestChannel = "mqttInputChannel")
public interface MessageGateway {
    void send(@Payload Object data, @Headers Map<String, Object> headers);
}
