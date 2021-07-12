package com.services.impl;

import com.enums.Provider;
import com.models.UserModel;
import com.repositories.UserRepository;
import com.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void processOAuthPostLogin(String name) {

        UserModel user = userRepository.getUserByUsername(name);

        if (user == null) {
            UserModel userModel = new UserModel(Provider.GOOGLE);
            userModel.setName(name);
            userRepository.save(userModel);
        }
    }
}
