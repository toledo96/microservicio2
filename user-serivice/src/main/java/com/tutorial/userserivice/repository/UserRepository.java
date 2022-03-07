package com.tutorial.userserivice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tutorial.userserivice.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
