/**
 *
 */
package com.example.madaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.madaka.response.RegisterResponse;

/**
 * @author yu-fujii
 *
 */
@Controller
@RequestMapping("/madaka")
public class RegisterController {

	  @ModelAttribute("registerModel")
	  public RegisterResponse registerModel() {
	    return new RegisterResponse();
	  }

	  @GetMapping("/register")
	  public String show(Model model){
	    model.addAttribute("registerModel", new RegisterResponse());
	    return "register";
	  }

}
