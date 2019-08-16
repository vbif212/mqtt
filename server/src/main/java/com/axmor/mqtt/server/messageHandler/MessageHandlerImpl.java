package com.axmor.mqtt.server.messageHandler;

import com.axmor.mqtt.server.configs.MessageGateway;
import com.axmor.mqtt.server.messageHandler.topicHandler.TopicHandler;
import com.axmor.mqtt.server.topics.ServerTopics;
import com.axmor.mqtt.server.util.TopicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@Component
public class MessageHandlerImpl implements MessageHandler {

    private static final Logger log = Logger.getLogger(MessageHandlerImpl.class.getName());

    @Autowired
    private List<TopicHandler> topicHandlerList;

    @Autowired
    private MessageGateway messageGateway;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        try {
            topicHandlerList
                    .stream()
                    .filter(topicHandler -> topicHandler.isMatched(message))
                    .findAny()
                    .ifPresent(rightTopicHandler -> rightTopicHandler.handle(message));
        } catch (UnsupportedOperationException e) {
            log.warning(e.getMessage());
            String userLogin = TopicUtils.getUserFromReceivedTopic(TopicUtils.getReceivedTopic(message));
            Map<String, Object> header = Collections.singletonMap(
                    MqttHeaders.TOPIC,
                    String.format(ServerTopics.SERVER_CLIENT_ID_ERROR_TOPIC, userLogin)
            );
            messageGateway.send(e.getMessage(), header);
        }
    }
}
