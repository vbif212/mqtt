package com.axmor.mqtt.server.repository;

import com.axmor.mqtt.server.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
    User findByLogin(String login);
    List<User> findAll();
}
