package com.bikkadit.ecommerce.service.impl;

import com.bikkadit.ecommerce.dto.UserDto;
import com.bikkadit.ecommerce.entity.User;
import com.bikkadit.ecommerce.exception.ResourceNotFoundException;
import com.bikkadit.ecommerce.helper.AppConstant;
import com.bikkadit.ecommerce.repository.UserRepository;
import com.bikkadit.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
@Slf4j
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
        log.info("Request initiated for User Repository to Create new User.");
        User savedUser = this.userRepository.save(user);
        log.info("Request Completed for User Repository to Create New User.");
        return this.modelMapper.map(savedUser, UserDto.class);

    }

    @Override
    public UserDto updateUser(String email, UserDto userDto) {
        User user = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException(AppConstant.USER_NOT_FOUND,"Email Id",email));
        User user1 = User.builder()
                .name(userDto.getName())
                .password(userDto.getPassword())
                .gender(userDto.getGender())
                .about(userDto.getAbout())
                .build();
        log.info("Request initiated for User Repository to Update User with email id {}",email);
        User updatedUser = this.userRepository.save(user1);
        log.info("Request Completed for User Repository to Update User with email id {}",email);
        return this.modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public UserDto getUserById(String id) {
        log.info("Request initiated for User Repository to get User with user id {}",id);
        User userById = this.userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(AppConstant.USER_NOT_FOUND, "UserId", id));
        log.info("Request initiated for User Repository to get User with user id {}",id);
        return this.modelMapper.map(userById, UserDto.class);
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Request initiated for User Repository to get User with email id {}",email);
        User userByEmail = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException(AppConstant.USER_NOT_FOUND,"Email Id",email));
        log.info("Request Completed for User Repository to get User with email id {}",email);
        return this.modelMapper.map(userByEmail, UserDto.class);
    }

    @Override
    public UserDto getUserByUsernamePassword(String userName, String password) {
        log.info("Request initiated for User Repository to get User with email id {} and Password {}",userName,password);
        User UserByEmailAndPassword = this.userRepository.findByEmailAndPassword(userName, password).orElseThrow(()->new ResourceNotFoundException(AppConstant.USER_NOT_FOUND,"email & password. email: ",userName));
        log.info("Request Completed for User Repository to get User with email id {} and Password {}",userName,password);
        return this.modelMapper.map(UserByEmailAndPassword, UserDto.class);
    }

    @Override
    public List<UserDto> getAllUsers() {
        log.info("Request initiated for User Repository to get all User.");
        List<User> users = this.userRepository.findAll();
        log.info("Request Completed for User Repository to get all User.");
        List<UserDto> userDtos = users.stream().map((user) -> this.modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
        return userDtos;
    }

    @Override
    public boolean deleteUserByEmail(String email) {
        log.info("Request initiated for User Repository to delete User by email id {}.",email);
        User user = this.userRepository.findByEmail(email).orElseThrow(()->new ResourceNotFoundException(AppConstant.USER_NOT_FOUND,"Email Id",email));
        log.info("Request completed for User Repository to delete User by email id {}.",email);
        if (user != null) {
            this.userRepository.delete(user);
            return true;
        }
        return false;
    }

    @Override
    public List<UserDto> findByNameContaining(String keyword) {
        List<User> users=this.userRepository.findByNameContaining(keyword);
        List<UserDto> userDto =users.stream().map((user)-> this.modelMapper.map(user,UserDto.class)).collect(Collectors.toList());
        return userDto;
    }


}
