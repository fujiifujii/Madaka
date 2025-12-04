/**
 *
 */
package com.example.madaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.madaka.response.RegisterResponse;

/**
 * @author yu-fujii
 *
 */
@Controller
public class DetailController {

	  @GetMapping("/detail")
	  public String show(Model model){
	    model.addAttribute("registerModel", new RegisterResponse());
	    return "detail";
	  }

}
