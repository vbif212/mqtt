package com.axmor.mqtt.client;

import com.axmor.mqtt.client.configs.MessageGateway;
import com.axmor.mqtt.client.configs.MqttProp;
import com.axmor.mqtt.client.topics.ClientTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.mqtt.support.MqttHeaders;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Autowired
    MessageGateway messageGateway;

    @Autowired
    MqttProp mqttProp;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Connection with " + mqttProp.getServerUris()[0] + " established");
        sendJoin();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                String commandLine = reader.readLine();
                String[] words = commandLine.split(" ");
                switch (words[0]) {
                    case "bye": {
                        sendBye();
                        break;
                    }
                    case "list": {
                        sendList();
                        break;
                    }
                    case "kick": {
                        sendKick(words);
                        break;
                    }
                    default:
                        sendMessage(commandLine);
                }
            }
        }
    }

    private void sendJoin() {
        sendToMqtt("", String.format(ClientTopics.CLIENT_ID_JOIN_TOPIC, mqttProp.getClientId()));
    }

    private void sendBye() {
        sendToMqtt("", String.format(ClientTopics.CLIENT_ID_BYE_TOPIC, mqttProp.getClientId()));
        Runtime.getRuntime().halt(0);
    }

    private void sendList() {
        sendToMqtt("", String.format(ClientTopics.CLIENT_ID_LIST_TOPIC, mqttProp.getClientId()));
    }

    private void sendKick(String[] parameters) {
        if (parameters.length < 3) {
            System.out.println("ERROR: Not enough parameters!");
        } else {
            StringBuilder stringBuilder = new StringBuilder();
            Arrays.stream(parameters).skip(2).forEach(s -> stringBuilder.append(s).append(" "));
            sendToMqtt(
                    stringBuilder.toString(),
                    String.format(ClientTopics.CLIENT_ID_KICK_TOPIC, mqttProp.getClientId(), parameters[1])
            );
        }
    }

    private void sendMessage(String message) {
        sendToMqtt(message, String.format(ClientTopics.CLIENT_ID_MESSAGE_TOPIC, mqttProp.getClientId()));
    }

    private void sendToMqtt(String message, String topic) {
        messageGateway.send(message, Collections.singletonMap(MqttHeaders.TOPIC, topic));
    }
}
