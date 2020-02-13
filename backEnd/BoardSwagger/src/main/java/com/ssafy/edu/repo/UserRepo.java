package com.ssafy.edu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, String> {
	User findOneByUserId(String userId);
}
