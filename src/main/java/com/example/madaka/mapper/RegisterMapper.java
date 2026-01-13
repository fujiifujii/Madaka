package com.example.madaka.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.transaction.annotation.Transactional;

import com.example.madaka.repository.RegisterRepository;

@Mapper
public interface RegisterMapper {

	@Transactional
    int insert(RegisterRepository registerRepository);
}
