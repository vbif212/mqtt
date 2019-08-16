package com.axmor.mqtt.server.messageHandler.topicHandler;

import com.axmor.mqtt.server.configs.MessageGateway;
import com.axmor.mqtt.server.repository.UserRepository;
import com.axmor.mqtt.server.topics.ClientTopics;
import com.axmor.mqtt.server.topics.ServerTopics;
import com.axmor.mqtt.server.util.TopicUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;

@Component
public class ListTopicHandler extends AbstractTopicHandler {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MessageGateway messageGateway;

    protected ListTopicHandler() {
        super(ClientTopics.CLIENT_LIST_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        StringBuilder listOfUsers = new StringBuilder();
        userRepository
                .findAll()
                .stream()
                .map(user -> user.getLogin() + ": " + (user.isOnline() ? "online" : "offline"))
                .forEach(userString -> listOfUsers.append(userString).append("\n"));
        String userLogin = TopicUtils.getUserFromReceivedTopic(receivedTopic);
        Map<String, Object> header = Collections.singletonMap(
                MqttHeaders.TOPIC,
                String.format(ServerTopics.SERVER_CLIENT_ID_LIST_TOPIC, userLogin)
        );
        messageGateway.send(listOfUsers.toString(), header);
        log.info(userLogin + " send command 'list'.");
    }
}
