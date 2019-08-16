package com.axmor.mqtt.client.messageHandler;


import com.axmor.mqtt.client.messageHandler.topicHandler.TopicHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageHandlerImpl implements MessageHandler {

    @Autowired
    private List<TopicHandler> topicHandlerList;

    @Override
    public void handleMessage(Message<?> message) throws MessagingException {
        topicHandlerList
                .stream()
                .filter(topicHandler -> topicHandler.isMatched(message))
                .findAny()
                .ifPresent(rightTopicHandler -> rightTopicHandler.handle(message));
    }
}
