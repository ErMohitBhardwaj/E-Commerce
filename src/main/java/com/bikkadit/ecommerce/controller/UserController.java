package com.bikkadit.ecommerce.controller;

import com.bikkadit.ecommerce.dto.ApiResponse;
import com.bikkadit.ecommerce.dto.UserDto;
import com.bikkadit.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * @auther Mohit
     * @apiNote Create User
     * @param userDto
     * @return UserDto,Status
     */
    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        log.info("Request initiated for User Service to Create new User.");
       UserDto userDto1= this.userService.createUser(userDto);
       log.info("Request Completed for User Service to Create New User.");
       return new ResponseEntity<UserDto>(userDto, HttpStatus.CREATED);
    }

    /**
     * @auther Mohit
     * @apiNote Update User
     * @param email
     * @param userDto
     * @return UserDto,Status Code
     */
    @PutMapping("/")
    ResponseEntity<UserDto> updateUser(@PathVariable String email, @RequestBody UserDto userDto){
        log.info("Request initiated for User Service to Update User with email id {}",email);
       UserDto userDto1 = this.userService.updateUser(email,userDto);
       log.info("Request Completed for User Service to Update User with email id {}",email);
       return new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);

    }

    /**
     * @author Mohit
     * @apiNote Get User By Id
     * @param userId
     * @return UserDto, Status Code
     */
    @GetMapping("/userId/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable String userId){
        log.info("Request initiated for User Service to get User with user id {}",userId);
        UserDto userDto1 = this.userService.getUserById(userId);
        log.info("Request initiated for User Service to get User with user id {}",userId);
        return new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);

    }

    /**
     * @author Mohit
     * @apiNote get user by email
     * @param email
     * @return User by email, status code
     */
    @GetMapping("/email/{email}")
    ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        log.info("Request initiated for User Service to get User with email id {}",email);
        UserDto userDto1 = this.userService.getUserByEmail(email);
        log.info("Request Completed for User Service to get User with email id {}",email);
        return new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);

    }

    /**
     * @author Mohit
     * @param email
     * @param password
     * @return user by email and password, Status code
     */
    @GetMapping("/emailandpassword/{email}/{password}")
    ResponseEntity<UserDto> getUserByEmailAndPassword(@PathVariable String email,@PathVariable String password){
        log.info("Request initiated for User Service to get User with email id {} and Password {}",email,password);
        UserDto userDto1 = this.userService.getUserByUsernamePassword(email,password);
        log.info("Request Completed for User Service to get User with email id {} and Password {}",email,password);
        return new ResponseEntity<UserDto>(userDto1,HttpStatus.OK);

    }

    /**
     * @auther Mohit
     * @apiNote get all user
     * @return all User
     */
    @GetMapping("/allusers")
    ResponseEntity<List<UserDto>> getAllUser(){
        log.info("Request initiated for User Service to get all User.");
        List<UserDto> userDto1 = this.userService.getAllUsers();
        log.info("Request Completed for User Service to get all User.");
        return new ResponseEntity<List<UserDto>>(userDto1,HttpStatus.OK);

    }

    /**
     * @author Mohit
     * @apiNote  delete user by email
     * @param email
     * @return ApiResponse, Status code
     */
    @DeleteMapping("/{email}")
    ResponseEntity<ApiResponse> deleteUserByEmail(@PathVariable String email){
        log.info("Request initiated for User Service to delete User by email id {}.",email);
         this.userService.deleteUserByEmail(email);
        log.info("Request completed for User Service to delete User by email id {}.",email);
        return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully",true,HttpStatus.OK),HttpStatus.OK);

    }




}
