package com.bikkadit.ecommerce.controller;

import com.bikkadit.ecommerce.dto.UserDto;
import com.bikkadit.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     *
     * @param userDto
     * @return
     */
    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        log.info("Request initiated for User Service to Create new User.");
       UserDto userDto1= this.userService.createUser(userDto);
       log.info("Request Completed for User Service to Create New User.");
       return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    /**
     *
     * @param email
     * @param userDto
     * @return
     */
    @PutMapping("/")
    ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody UserDto userDto){
        log.info("Request initiated for User Service to Update User with email id {}",email);
       UserDto userDto1 = this.userService.updateUser(email,userDto);
       log.info("Request Completed for User Service to Update User with email id {}",email);
       return new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);

    }

}
