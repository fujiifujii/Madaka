package com.example.madaka.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.madaka.controller.SearchController;
import com.example.madaka.response.LoginResponse;
import com.example.madaka.response.SearchResponse;

/**
 * @author yu-kudo
 * 検索画面のmapperクラス
 */
@Mapper
public interface SearchMapper {

	// 氏名検索プルダウン取得用
	List<SearchController.SelectOption> findUserNameForSearch(LoginResponse loginInfo);
	List<SearchResponse> search(Map<String, Object> params);
}
