package com.digitalrazgrad.RestApiWithSwagger.service.impl;

import com.digitalrazgrad.RestApiWithSwagger.model.entity.User;
import com.digitalrazgrad.RestApiWithSwagger.repository.UserRepository;
import com.digitalrazgrad.RestApiWithSwagger.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<User> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, User.class))
                .collect(Collectors.toSet());
    }

    @Override
    public User addUser(User user) {
        return userRepository.saveAndFlush(modelMapper.map(user, User.class));
    }

    @Override
    public User updateUser(long userId, User user) {
        return userRepository.findById(userId).map(existingUser -> {
            user.setId(userId);
            modelMapper.map(user, existingUser);
            return userRepository.saveAndFlush(existingUser);
        }).orElseThrow(() -> new RuntimeException("User with id \\'" + userId + "\\' was not found!"));
    }

    @Override
    public void deleteUser(long userId) {
        if (!userRepository.existsById(userId)) {
            throw new RuntimeException("User with id \\'" + userId + "\\' was not found!");
        }
        userRepository.deleteById(userId);
    }
}
