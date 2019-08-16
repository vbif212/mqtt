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
public class WillTopicHandler extends AbstractTopicHandler {

    private final String WILL_MESSAGE = "%s said '%s' and disconnected!";

    @Autowired
    UserService userService;

    @Autowired
    MessageGateway messageGateway;

    protected WillTopicHandler() {
        super(ClientTopics.CLIENT_WILL_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        try {
            String userLogin = TopicUtils.getUserFromReceivedTopic(receivedTopic);
            userService.offlineUserByLogin(userLogin);
            Map<String, Object> header = Collections.singletonMap(MqttHeaders.TOPIC, ServerTopics.SERVER_ALL_MESSAGE_TOPIC);
            messageGateway.send(String.format(WILL_MESSAGE, userLogin, payload), header);
        } catch (Exception ignored) {

        }
    }
}
