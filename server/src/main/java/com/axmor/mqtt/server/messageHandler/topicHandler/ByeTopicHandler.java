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
public class ByeTopicHandler extends AbstractTopicHandler {

    private final String BYE_MESSAGE = "%s said 'Bye' and left the chat!";

    @Autowired
    UserService userService;

    @Autowired
    MessageGateway messageGateway;

    protected ByeTopicHandler() {
        super(ClientTopics.CLIENT_BYE_TOPIC);
    }

    @Override
    protected void innerHandle(String receivedTopic, String payload) {
        String userLogin = TopicUtils.getUserFromReceivedTopic(receivedTopic);
        userService.offlineUserByLogin(userLogin);
        Map<String, Object> header = Collections.singletonMap(MqttHeaders.TOPIC, ServerTopics.SERVER_ALL_MESSAGE_TOPIC);
        messageGateway.send(String.format(BYE_MESSAGE, userLogin), header);
        log.info(userLogin + " send command 'bye'.");
    }
}
