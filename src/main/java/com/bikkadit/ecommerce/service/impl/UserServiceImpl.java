package com.bikkadit.ecommerce.service.impl;

import com.bikkadit.ecommerce.dto.UserDto;
import com.bikkadit.ecommerce.entity.User;
import com.bikkadit.ecommerce.exception.ResourceNotFoundException;
import com.bikkadit.ecommerce.repository.UserRepository;
import com.bikkadit.ecommerce.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        User user = this.modelMapper.map(userDto, User.class);
        User savedUser = this.userRepository.save(user);

        return this.modelMapper.map(savedUser, UserDto.class);
    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        User user = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User Not found with","Email Id",email));
        User user1 = User.builder()
                .name(userDto.getName())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .about(userDto.getAbout())
                .build();

        User updatedUser = this.userRepository.save(user1);

        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(String id) {
        User userById = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found with", "UserId", id));
        return this.modelMapper.map(userById, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User userByEmail = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not Fount with ","Email Id",email));
        return this.modelMapper.map(userByEmail, UserDto.class);
    }

    @Override
    public UserDto getUserByUsernamePassword(String userName, String password) {
        User UserByEmailAndPassword = this.userRepository.findByEmailAndPassword(userName, password).orElseThrow(()->new ResourceNotFoundException("User Not found with ","email & password. email: ",userName));
        return this.modelMapper.map(UserByEmailAndPassword, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = this.userRepository.findAll();
        List<UserDto> userDtos = users.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        User user = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException("User not Fount with ","Email Id",email));
        if (user != null) {
            this.userRepository.delete(user);
            return true;
        }
        return false;
    }
}
