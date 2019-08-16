package com.axmor.mqtt.client.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("mqtt")
public class MqttProp {

    private String clientId;

    private String[] serverUris;

    private String willMessageText;

    private int willMessageQos;

    private int inboundMessageQos;

    private int completionTimeout;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String[] getServerUris() {
        return serverUris;
    }

    public void setServerUris(String[] serverUris) {
        this.serverUris = serverUris;
    }

    public String getWillMessageText() {
        return willMessageText;
    }

    public void setWillMessageText(String willMessageText) {
        this.willMessageText = willMessageText;
    }

    public int getWillMessageQos() {
        return willMessageQos;
    }

    public void setWillMessageQos(int willMessageQos) {
        this.willMessageQos = willMessageQos;
    }

    public int getInboundMessageQos() {
        return inboundMessageQos;
    }

    public void setInboundMessageQos(int inboundMessageQos) {
        this.inboundMessageQos = inboundMessageQos;
    }

    public int getCompletionTimeout() {
        return completionTimeout;
    }

    public void setCompletionTimeout(int completionTimeout) {
        this.completionTimeout = completionTimeout;
    }
}
