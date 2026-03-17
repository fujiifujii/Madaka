package com.example.madaka.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	 @ExceptionHandler(NoSessionException.class)
	    public String handleNoSession() {
	        return "redirect:/madaka/login";
	    }

}
