package com.vimal.repository;

import org.springframework.data.repository.CrudRepository;

import com.vimal.model.Users;

public interface UsersDao extends CrudRepository<Users, Integer>{
	
	Users findByUserNameAndPassword(String userName,String Password);

}
