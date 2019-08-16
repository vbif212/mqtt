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
public class KickTopicHandler extends AbstractTopicHandler {

    private final int KICK_USER_LEVEL = 3;
    private final String KICK_MESSAGE = "%s kicked you for reason: %s";
    private final String KICK_MESSAGE_FOR_ALL = "%s was kicked from chat!";

    @Autowired
    UserService userService;

    @Autowired
    MessageGateway messageGateway;

    protected KickTopicHandler() {
        super(ClientTopics.CLIENT_KICK_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        String userLogin = TopicUtils.getUserFromReceivedTopic(receivedTopic);
        String kickedLogin = TopicUtils.getValueFromReceivedTopicByLevel(receivedTopic, KICK_USER_LEVEL);
        userService.offlineUserByLogin(kickedLogin);
        Map<String, Object> headers = Collections.singletonMap(
                MqttHeaders.TOPIC,
                String.format(ServerTopics.SERVER_CLIENT_ID_KICK_TOPIC, kickedLogin)
        );
        messageGateway.send(String.format(KICK_MESSAGE, userLogin, payload), headers);
        Map<String, Object> headersForAll = Collections.singletonMap(
                MqttHeaders.TOPIC,
                ServerTopics.SERVER_ALL_MESSAGE_TOPIC
        );
        messageGateway.send(String.format(KICK_MESSAGE_FOR_ALL, kickedLogin), headersForAll);
        log.info(userLogin + " kicked " + kickedLogin + " for reason: " + payload);
    }
}
