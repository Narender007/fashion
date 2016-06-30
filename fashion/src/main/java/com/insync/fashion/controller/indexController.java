package com.insync.fashion.controller;



import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class indexController {
	
	private static final Logger logger = LoggerFactory.getLogger(indexController.class);
	
	// login page
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Login page");
		return "index";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(Locale locale, Model model,HttpServletRequest httpRequest ) {
		HttpSession httpSession = httpRequest.getSession(false);
		httpSession.invalidate();
		logger.info("Logout");
		return "index";
	}
	
   
	 @RequestMapping(value = "/userManage", method = RequestMethod.GET)
	 public String userManage()
	 {
		 return "userMaster";
	 }
	 
	 
	 @RequestMapping(value = "/user", method = RequestMethod.GET)
	 public String user()
	 {
		 return "home";
	 }
}
