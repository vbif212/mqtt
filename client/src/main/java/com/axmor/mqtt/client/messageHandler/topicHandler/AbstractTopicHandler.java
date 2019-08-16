package com.axmor.mqtt.client.messageHandler.topicHandler;

import com.axmor.mqtt.client.util.TopicUtils;
import org.eclipse.paho.client.mqttv3.MqttTopic;
import org.springframework.messaging.Message;

public abstract class AbstractTopicHandler implements TopicHandler {

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

    protected void innerHandle(String receivedTopic, String payload) {
        System.out.println(payload);
    }
}
