package com.axmor.mqtt.client;

import com.axmor.mqtt.client.configs.MessageGateway;
import com.axmor.mqtt.client.topics.ClientTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.mqtt.support.MqttHeaders;

import java.util.Collections;

@SpringBootApplication
public class ClientApplication implements CommandLineRunner {

    @Autowired
    MessageGateway messageGateway;

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        messageGateway.send("lol", Collections.singletonMap(MqttHeaders.TOPIC, String.format(ClientTopics.CLIENT_ID_JOIN_TOPIC, "client")));
        messageGateway.send("lol", Collections.singletonMap(MqttHeaders.TOPIC, String.format(ClientTopics.CLIENT_ID_LIST_TOPIC, "client")));
    }
}
