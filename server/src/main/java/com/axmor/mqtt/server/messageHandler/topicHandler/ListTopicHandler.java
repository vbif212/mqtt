package com.axmor.mqtt.server.messageHandler.topicHandler;

import com.axmor.mqtt.server.model.User;
import com.axmor.mqtt.server.service.UserService;
import com.axmor.mqtt.server.topics.ClientTopics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListTopicHandler extends AbstractTopicHandler {

    @Autowired
    UserService userService;

    protected ListTopicHandler() {
        super(ClientTopics.CLIENT_LIST_TOPIC);
    }

    @Override
    protected void innerHandle(Message<?> message) {
        List<User> userList = userService.getAllUsers();
        userList.forEach(System.out::println);
    }
}
