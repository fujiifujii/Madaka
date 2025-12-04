package com.example.madaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.madaka.form.LoginForm;
import com.example.madaka.mapper.LoginMapper;
import com.example.madaka.response.LoginResponse;

@Service
public class LoginService {

	 private static LoginMapper loginMapper;

	    @Autowired
	    public LoginService(LoginMapper loginMapper) {
	        LoginService.loginMapper = loginMapper;
	    }

	    public static LoginResponse login(LoginForm form) {
	    	return loginMapper.login(form);
	    }
}
