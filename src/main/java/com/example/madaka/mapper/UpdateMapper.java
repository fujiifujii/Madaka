package com.example.madaka.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.madaka.repository.RegisterRepository;

@Mapper
public interface UpdateMapper {
	RegisterRepository selectByLateId(@Param("lateId") String lateId);

	@Transactional
	int updateByLateId(RegisterRepository registerRepository);
}
