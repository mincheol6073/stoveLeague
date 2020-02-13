package com.ssafy.edu.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ssafy.edu.model.UserList;

@Repository
public interface UserListRepo extends JpaRepository<UserList, Integer>{

}
