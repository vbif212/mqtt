package com.axmor.mqtt.server.messageHandler.topicHandler;

import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;

import java.util.Objects;

public abstract class AbstractTopicHandler implements TopicHandler {

    private final String TOPIC;

    protected AbstractTopicHandler(String topic) {
        this.TOPIC = topic;
    }

    @Override
    public boolean isMatched(Message<?> message) {
        return MqttTopic.isMatched(TOPIC, Objects.requireNonNull(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC)).toString());
    }

    @Override
    public void handle(Message<?> message) {
        innerHandle(message);
    }

    protected abstract void innerHandle(Message<?> message);
}
