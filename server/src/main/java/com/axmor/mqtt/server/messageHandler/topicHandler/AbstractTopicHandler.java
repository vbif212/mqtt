package com.axmor.mqtt.server.messageHandler.topicHandler;

import com.axmor.mqtt.server.messageHandler.MessageHandlerImpl;
import com.axmor.mqtt.server.util.TopicUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.messaging.Message;

import java.util.logging.Logger;

public abstract class AbstractTopicHandler implements TopicHandler {

    protected static final Logger log = Logger.getLogger(AbstractTopicHandler.class.getName());

    private final String TOPIC;

    protected AbstractTopicHandler(String topic) {
        this.TOPIC = topic;
    }

    @Override
    public boolean isMatched(Message<?> message) {
        return MqttTopic.isMatched(TOPIC, TopicUtils.getReceivedTopic(message));
    }

    @Override
    public void handle(Message<?> message) {
        innerHandle(TopicUtils.getReceivedTopic(message), message.getPayload().toString());
    }

    protected abstract void innerHandle(String receivedTopic, String payload);
}
