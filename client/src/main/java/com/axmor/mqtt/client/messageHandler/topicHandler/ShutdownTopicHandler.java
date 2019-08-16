package com.axmor.mqtt.client.messageHandler.topicHandler;

import com.axmor.mqtt.client.topics.ServerTopics;
import org.springframework.stereotype.Component;

@Component
public class ShutdownTopicHandler extends AbstractTopicHandler {
    protected ShutdownTopicHandler() {
        super(ServerTopics.SERVER_ALL_SHUTDOWN_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        System.out.println(payload);
        Runtime.getRuntime().halt(0);
    }
}
