package com.mycalories.caloriesrest.service;

import com.mycalories.model2.user.User;
import com.mycalories.model2.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

}
