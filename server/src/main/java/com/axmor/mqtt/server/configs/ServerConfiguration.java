package com.axmor.mqtt.server.configs;

import com.axmor.mqtt.server.messageHandler.MessageHandlerImpl;
import com.axmor.mqtt.server.topics.ClientTopics;
import com.axmor.mqtt.server.topics.ServerTopics;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
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
@PropertySource(value = "mqtt.properties")
public class ServerConfiguration {

    @Value("${mqtt.clientId}")
    private String CLIENT_ID;

    @Value("${mqtt.serverUris}")
    private String[] SERVER_URIS;

    @Value("${mqtt.willMessage.text}")
    private String WILL_MESSAGE_TEXT;

    @Value("${mqtt.willMessage.qos}")
    private int WILL_MESSAGE_QOS;

    @Value("${mqtt.inboundMessage.qos}")
    private int INBOUND_MESSAGE_QOS;

    @Value("${mqtt.completionTimeout}")
    private int COMPLETION_TIMEOUT;

    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setServerURIs(SERVER_URIS);
        mqttConnectOptions.setWill(
                ServerTopics.SERVER_ALL_SHUTDOWN_TOPIC,
                WILL_MESSAGE_TEXT.getBytes(StandardCharsets.UTF_8),
                WILL_MESSAGE_QOS,
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
                        ClientTopics.CLIENT_TOPIC_ALL
                );
        mqttPahoMessageDrivenChannelAdapter.setQos(INBOUND_MESSAGE_QOS);
        mqttPahoMessageDrivenChannelAdapter.setCompletionTimeout(COMPLETION_TIMEOUT);
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
                new MqttPahoMessageHandler(CLIENT_ID, mqttPahoClientFactory());
        messageHandler.setAsync(true);
        return messageHandler;
    }
}
