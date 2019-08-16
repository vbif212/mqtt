package com.axmor.mqtt.client.configs;

import com.axmor.mqtt.client.messageHandler.MessageHandlerImpl;
import com.axmor.mqtt.client.topics.ClientTopics;
import com.axmor.mqtt.client.topics.ServerTopics;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

@Configuration
public class ClientConfiguration {

    @Autowired
    MqttProp mqttProp;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(mqttProp.getServerUris());
        mqttConnectOptions.setWill(
                String.format(ClientTopics.CLIENT_ID_WILL_TOPIC, mqttProp.getClientId()),
                mqttProp.getWillMessageText().getBytes(StandardCharsets.UTF_8),
                mqttProp.getWillMessageQos(),
                false
        );
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttPahoClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(mqttConnectOptions());
        return factory;
    }

    @Bean
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttOutputChannel")
    public MessageHandler messageHandler() {
        return new MessageHandlerImpl();
    }

    @Bean
    public MessageProducer messageProducerSupport() {
        MqttPahoMessageDrivenChannelAdapter mqttPahoMessageDrivenChannelAdapter =
                new MqttPahoMessageDrivenChannelAdapter(
                        UUID.randomUUID().toString(),
                        mqttPahoClientFactory(),
                        ServerTopics.SERVER_ALL_TOPIC_FOR_SUBSCRIBE,
                        String.format(ServerTopics.SERVER_CLIENT_ID_KICK_TOPIC, mqttProp.getClientId()),
                        String.format(ServerTopics.SERVER_CLIENT_ID_LIST_TOPIC, mqttProp.getClientId()),
                        String.format(ServerTopics.SERVER_CLIENT_ID_ERROR_TOPIC, mqttProp.getClientId())
                );
        mqttPahoMessageDrivenChannelAdapter.setQos(mqttProp.getInboundMessageQos());
        mqttPahoMessageDrivenChannelAdapter.setCompletionTimeout(mqttProp.getCompletionTimeout());
        mqttPahoMessageDrivenChannelAdapter.setConverter(new DefaultPahoMessageConverter());
        mqttPahoMessageDrivenChannelAdapter.setOutputChannel(mqttOutputChannel());
        return mqttPahoMessageDrivenChannelAdapter;
    }

    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }

    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler mqttOutbound() {
        MqttPahoMessageHandler messageHandler =
                new MqttPahoMessageHandler(mqttProp.getClientId(), mqttPahoClientFactory());
        messageHandler.setAsync(true);
        return messageHandler;
    }
}
