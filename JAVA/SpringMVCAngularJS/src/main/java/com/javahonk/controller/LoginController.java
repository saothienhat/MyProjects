package com.javahonk.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javahonk.model.User;
import com.javahonk.services.impl.LoginServiceImpl;

@Controller
public class LoginController {
	
//	@Autowired
//	private LoginDelegate loginDelegate;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView displayLogin(HttpServletRequest request, HttpServletResponse response, User user) {
		ModelAndView model = new ModelAndView("login");
		model.addObject("user", user);
		return model;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView executeLogin(HttpServletRequest request, HttpServletResponse response,
			@ModelAttribute("user") User user) {
		ModelAndView model = null;
		try {
			System.out.println("call LoginController.executeLogin().....................");  
			LoginServiceImpl loginServiceImpl = new LoginServiceImpl();
			boolean isValidUser = loginServiceImpl.isValidUser(user.getUsername(), user.getPassword());
			if (isValidUser) {
				System.out.println("User Login Successful");
				request.setAttribute("loggedInUser", user.getUsername());
				model = new ModelAndView("welcome");
			} else {
				model = new ModelAndView("login");
				request.setAttribute("loginmessage", "Invalid credentials!!");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return model;
	}

}
