package com.axmor.mqtt.server.messageHandler.topicHandler;

import org.springframework.messaging.Message;

public interface TopicHandler {
    boolean isMatched(Message<?> message);
    void handle(Message<?> message);
}
