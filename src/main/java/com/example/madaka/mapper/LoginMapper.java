package com.example.madaka.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.example.madaka.form.LoginForm;


@Mapper
public interface LoginMapper {

	User login(LoginForm form);
}


