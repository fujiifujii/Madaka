package com.example.madaka.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.madaka.form.LoginForm;
import com.example.madaka.response.LoginResponse;


@Mapper
public interface LoginMapper {

	LoginResponse login(LoginForm form);
}


