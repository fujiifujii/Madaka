package com.example.madaka.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.madaka.repository.UserMaster;


@Mapper
public interface LoginMapper {
	@Select("SELECT * FROM UserMaster")
    List<UserMaster> select();

}


