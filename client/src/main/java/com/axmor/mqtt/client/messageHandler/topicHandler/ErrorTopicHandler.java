package com.axmor.mqtt.client.messageHandler.topicHandler;

import com.axmor.mqtt.client.topics.ServerTopics;
import org.springframework.stereotype.Component;

@Component
public class ErrorTopicHandler extends AbstractTopicHandler {
    protected ErrorTopicHandler() {
        super(ServerTopics.SERVER_CLIENT_ERROR_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        System.out.println("ERROR: " + payload);
    }
}
