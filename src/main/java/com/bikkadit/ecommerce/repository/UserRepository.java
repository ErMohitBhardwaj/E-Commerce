package com.bikkadit.ecommerce.repository;

import com.bikkadit.ecommerce.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,String> {
    User findByEmail(String email);

    User findByEmailAndPassword(String email, String password);

    List<User> findByNameContaining(String keyword);



}
