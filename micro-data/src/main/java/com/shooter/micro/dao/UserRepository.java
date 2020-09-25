package com.shooter.micro.dao;

import com.shooter.micro.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long>,CrudRepository<User,Long> {

    User findUserByUserName(String userName);

    List<User> findUsersByUserName(String userName);
}

