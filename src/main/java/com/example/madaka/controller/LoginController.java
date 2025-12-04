package com.example.madaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.madaka.common.Const;
import com.example.madaka.form.LoginForm;
import com.example.madaka.response.LoginResponse;
import com.example.madaka.service.LoginService;

import jakarta.servlet.http.HttpSession;


@RequestMapping("/madaka")
@Controller
public class LoginController {

	@RequestMapping("/login")
	public ModelAndView open( ModelAndView mav) {
		mav.setViewName("login");
	    mav.addObject("screenName", Const.screenName);
	    mav.addObject("AppName", Const.AppName);
	    return mav;
	  }
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(@ModelAttribute LoginForm form, HttpSession session) {
		LoginResponse response = LoginService.login(form);
		session.setAttribute("loginUser", response);
		System.out.println(session.getAttribute("loginUser"));
	    return "result";
	}


}