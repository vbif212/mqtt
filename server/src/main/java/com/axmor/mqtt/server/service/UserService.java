package com.axmor.mqtt.server.service;

import com.axmor.mqtt.server.model.User;
import com.axmor.mqtt.server.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void joinUserByLogin(String login) {
        if (login.isEmpty()) {
            throw new IllegalArgumentException();
        }
        User user = userRepository.findByLogin(login);
        if (user != null && user.isOnline()) {
            throw new UnsupportedOperationException(String.format("User %s already exist!", login));
        }
        if (user == null) {
            user = new User(login);
        }
        user.setOnline(true);
        userRepository.save(user);
    }

    public void offlineUserByLogin(String login) {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UnsupportedOperationException(String.format("User %s not found!", login));
        }
        if (!user.isOnline()) {
            throw new UnsupportedOperationException(String.format("User %s already offline!", login));
        }
        user.setOnline(false);
        userRepository.save(user);
    }
}
