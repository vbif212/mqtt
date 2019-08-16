package com.axmor.mqtt.client.messageHandler.topicHandler;

import com.axmor.mqtt.client.topics.ServerTopics;
import org.springframework.stereotype.Component;

@Component
public class KickTopicHandler extends AbstractTopicHandler {

    protected KickTopicHandler() {
        super(ServerTopics.SERVER_CLIENT_KICK_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        System.out.println(payload);
        Runtime.getRuntime().halt(0);
    }
}
