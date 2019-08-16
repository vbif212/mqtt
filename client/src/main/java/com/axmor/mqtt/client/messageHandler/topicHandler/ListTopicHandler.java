package com.axmor.mqtt.client.messageHandler.topicHandler;

import com.axmor.mqtt.client.topics.ServerTopics;
import org.springframework.stereotype.Component;

@Component
public class ListTopicHandler extends AbstractTopicHandler {
    protected ListTopicHandler() {
        super(ServerTopics.SERVER_CLIENT_LIST_TOPIC);
    }
}
