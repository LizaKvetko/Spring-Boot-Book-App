package com.app.controller;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.app.model.User;
import com.app.service.UserService;

@Controller
@SessionAttributes({"loggedinUser"})
@RequestMapping("/user/")
public class UserController {
	
	@Autowired
	private UserService us;
	
	// when user presses register link User object is created. This object is sent to register form
	@GetMapping("/register")
	public String showUserForm(@ModelAttribute("user") User user) {
		//go to register page
		return "register";
	}
	
	// @ModelAttributre is the object created in showUserForm()
	@PostMapping("/register_user")
	public String addUser(@ModelAttribute("user") User user, Model m) {
		us.insert(user);
		return "login";
	}
	
	//when user presses login link
	@GetMapping("/login")
	public String showLoginForm(@ModelAttribute("login") User user) {
		return "login";
	}
	
	@PostMapping("/login_user")
	public String loginUser(@ModelAttribute("login") User loggedin, Model m) {
		User user = us.validateLogin(loggedin.getEmail(), loggedin.getPassword());
		if(user != null) {
			m.addAttribute("loggedinUser", user);
			//loggedin = ur.getCurrUser(loggedin.getEmail());
			return "/home";
		}
		else 
			return "login";
	}
		
	@GetMapping("/profile")
	public String viewUser(Model m, HttpSession session) {
		//int id = (Integer) m.getAttribute(Integer.toString(loggedin.getId()));
		User user = (User) session.getAttribute("loggedinUser");
		m.addAttribute("user", us.selectOne(user.getId()));
		return "profile";
	}
}

