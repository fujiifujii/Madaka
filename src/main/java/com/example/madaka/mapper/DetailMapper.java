package com.example.madaka.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.madaka.response.DetailResponse;

@Mapper
public interface DetailMapper {
	// 遅刻IDで遅刻履歴を1件取得
	DetailResponse selectByLateId(@Param("lateId") String lateId);
}
