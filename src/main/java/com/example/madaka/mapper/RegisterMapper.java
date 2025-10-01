package com.example.madaka.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.madaka.repository.RegisterRepository;

@Mapper
public interface RegisterMapper {

    int insert(RegisterRepository registerRepository);
}
