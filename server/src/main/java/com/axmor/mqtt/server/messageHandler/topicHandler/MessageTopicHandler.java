package com.axmor.mqtt.server.messageHandler.topicHandler;

import com.axmor.mqtt.server.configs.MessageGateway;
import com.axmor.mqtt.server.topics.ClientTopics;
import com.axmor.mqtt.server.topics.ServerTopics;
import com.axmor.mqtt.server.util.TopicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class MessageTopicHandler extends AbstractTopicHandler {

    @Autowired
    MessageGateway messageGateway;

    protected MessageTopicHandler() {
        super(ClientTopics.CLIENT_MESSAGE_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        String userLogin = TopicUtils.getUserFromReceivedTopic(receivedTopic);
        Map<String,Object> header = Collections.singletonMap(MqttHeaders.TOPIC, ServerTopics.SERVER_ALL_MESSAGE_TOPIC);
        messageGateway.send(userLogin + ": " + payload, header);
    }
}
