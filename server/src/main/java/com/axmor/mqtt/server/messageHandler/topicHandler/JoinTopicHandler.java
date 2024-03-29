package com.axmor.mqtt.server.messageHandler.topicHandler;

import com.axmor.mqtt.server.configs.MessageGateway;
import com.axmor.mqtt.server.service.UserService;
import com.axmor.mqtt.server.topics.ClientTopics;
import com.axmor.mqtt.server.topics.ServerTopics;
import com.axmor.mqtt.server.util.TopicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class JoinTopicHandler extends AbstractTopicHandler {
    private final String USER_JOIN_ALL = "%s joined to chat!";

    @Autowired
    UserService userService;

    @Autowired
    MessageGateway messageGateway;

    protected JoinTopicHandler() {
        super(ClientTopics.CLIENT_JOIN_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        String userLogin = TopicUtils.getUserFromReceivedTopic(receivedTopic);
        userService.joinUserByLogin(userLogin);
        Map<String, Object> joinAllHeader = Collections.singletonMap(
                MqttHeaders.TOPIC,
                ServerTopics.SERVER_ALL_MESSAGE_TOPIC
        );
        messageGateway.send(String.format(USER_JOIN_ALL, userLogin), joinAllHeader);
        log.info(userLogin + " join to chat.");
    }
}
