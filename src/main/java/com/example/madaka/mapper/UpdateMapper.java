package com.example.madaka.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.madaka.repository.RegisterRepository;

@Mapper
public interface UpdateMapper {
	// 遅刻IDで遅刻履歴を1件取得
	RegisterRepository selectByLateId(@Param("lateId") String lateId);

	@Transactional
	// 遅刻IDをキーに遅刻履歴を更新
	int updateByLateId(RegisterRepository registerRepository);
}
