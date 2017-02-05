package com.vimal.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.vimal.model.Users;
import com.vimal.repository.*;

@Component
public class MyAuthentication {

	
	@Autowired
	private UsersDao UsersDao;
	
	public String AuthenticateUser(Users user)
	{
		Users isuser = UsersDao.findByUserNameAndPassword(user.getUserName(),user.getPassword());
		if(isuser!=null)
		{
			//user successfully authenticated.
			return "success";
		}
		else
			return "failure";
	}
	
	
	
	
	

}
