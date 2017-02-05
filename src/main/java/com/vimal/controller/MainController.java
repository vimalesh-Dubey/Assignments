package com.vimal.controller;

import java.security.Principal;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vimal.authentication.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vimal.model.Users;
import com.vimal.repository.UsersDao;

@RestController
public class MainController {

	@Autowired
	private MyAuthentication MyAuthentication;

	@Autowired
	private UsersDao UsersDao;

	@RequestMapping(value ="/welcome" , method = RequestMethod.GET)
	public String welcomePage(Model model) {
		model.addAttribute("title", "Welcome");
		model.addAttribute("message", "Hello friend!");
		return "welcomePage";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(Model model) {
		return "adminPage";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage(@RequestParam(value="LoginObj") String LoginString) {

		try
		{
			//JSONObject Loginparam = new JSONObject(LoginString);
			// String username = Loginparam.getString("username");
			//String password = Loginparam.getString("password");
			ObjectMapper mapper = new ObjectMapper();
			Users user = mapper.readValue(LoginString,Users.class);
			//MyAuthentication.AuthenticateUser(user);


		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "failure";
		}


		return "success";
	}

	@RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
	public String logoutSuccessfulPage(Model model) {
		model.addAttribute("title", "Logout");
		return "logoutSuccessfulPage";
	}

	@RequestMapping(value = "/userInfo", method = RequestMethod.GET)
	public String userInfo(Model model, Principal principal) {

		// After user login successfully.
		String userName = principal.getName();

		System.out.println("User Name: "+ userName);

		return "userInfoPage";
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String CreateUser() {

		try
		{
			//ObjectMapper mapper = new ObjectMapper();
			//Users user = mapper.readValue(nuser,Users.class);
			Users user = new Users();
			user.setUserName("vimal");
			user.setPassword("password");
			Users userinserted = UsersDao.save(user);
			if(userinserted!=null)
			{
				return "success";
			}
			else
				return "failure";
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return null;

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public String accessDenied(Model model, Principal principal) {

		if (principal != null) {
			model.addAttribute("message", "Hi " + principal.getName()
			+ "<br> You do not have permission to access this page!");
		} else {
			model.addAttribute("msg",
					"You do not have permission to access this page!");
		}
		return "403Page";
	}
}