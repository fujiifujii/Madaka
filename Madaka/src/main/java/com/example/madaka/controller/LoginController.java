package com.example.madaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/madaka")
@Controller
public class LoginController {
	/** 画面名 */
	final private String screenName = "ログイン画面";
	/** システム名 */
	final private String AppName = "勤怠連絡システム";


	@RequestMapping("/login")
	  public ModelAndView open( ModelAndView mav) {
		mav.setViewName("login");
	    mav.addObject("screenName", screenName);
		mav.addObject("AppName", AppName);
	    return mav;
	  }
}