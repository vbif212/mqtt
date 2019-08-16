package com.axmor.mqtt.client.util;

import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.Message;

import java.util.Objects;

public class TopicUtils {

    private static int USER_LEVEL = 1;

    public static String getReceivedTopic(Message<?> message) {
        return Objects.requireNonNull(message.getHeaders().get(MqttHeaders.RECEIVED_TOPIC)).toString();
    }

    public static String getValueFromReceivedTopicByLevel(String topic, int level) {
        return topic.split("/")[level];
    }

    public static String getUserFromReceivedTopic(String topic) {
        return topic.split("/")[USER_LEVEL];
    }
}
