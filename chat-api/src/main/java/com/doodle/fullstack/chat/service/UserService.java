package com.doodle.fullstack.chat.service;

import com.doodle.fullstack.chat.model.User;
import com.doodle.fullstack.chat.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String createUser(String userName) {

        User user = userRepository.findByUsername(userName);

        if (user != null) {
            return "Invalid_Username";
        }
        User newUser = new User(userName);

        userRepository.save(newUser);
        return newUser.getUsername();
    }

}
