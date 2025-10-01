package com.example.madaka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Service;

import com.example.madaka.form.LoginForm;
import com.example.madaka.mapper.LoginMapper;

@Service
public class LoginService {

	 private static LoginMapper loginMapper;

	    @Autowired
	    public LoginService(LoginMapper loginMapper) {
	        this.loginMapper = loginMapper;
	    }

	    public static User login(LoginForm form) {
	        return loginMapper.login(form);
	    }
}
