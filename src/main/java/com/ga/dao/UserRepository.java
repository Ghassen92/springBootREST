package com.ga.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ga.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
