package com.doodle.fullstack.chat.repository;

import com.doodle.fullstack.chat.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(String username);
    List<User> findAll();
}
