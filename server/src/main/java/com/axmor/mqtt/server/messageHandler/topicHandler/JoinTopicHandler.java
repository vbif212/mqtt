package com.axmor.mqtt.server.messageHandler.topicHandler;

import com.axmor.mqtt.server.service.UserService;
import com.axmor.mqtt.server.topics.ClientTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JoinTopicHandler extends AbstractTopicHandler{

    @Autowired
    UserService userService;

    protected JoinTopicHandler() {
        super(ClientTopics.CLIENT_JOIN_TOPIC);
    }

    @Override
    protected void innerHandle(Message<?> message) {
        String userLogin = Objects.requireNonNull(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC)).toString().split("/")[1];
        userService.joinUserByLogin(userLogin);
    }
}
