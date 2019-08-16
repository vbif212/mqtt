package com.axmor.mqtt.client.messageHandler.topicHandler;


import com.axmor.mqtt.client.topics.ServerTopics;
import org.springframework.stereotype.Component;


@Component
public class MessageTopicHandler extends AbstractTopicHandler {
    protected MessageTopicHandler() {
        super(ServerTopics.SERVER_ALL_MESSAGE_TOPIC);
    }
}
