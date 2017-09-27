package com.g.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.g.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
