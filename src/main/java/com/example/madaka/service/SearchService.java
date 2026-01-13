package com.example.madaka.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.madaka.controller.SearchController;
import com.example.madaka.form.SearchForm;
import com.example.madaka.mapper.SearchMapper;
import com.example.madaka.response.LoginResponse;
import com.example.madaka.response.SearchResponse;

/**
 * @author yu-kudo
 * 検索画面のserviceクラス
 */
@Component
public class SearchService {

    @Autowired
    private SearchMapper searchMapper;

    //検索画面のプルダウン氏名を取得
    public List<SearchController.SelectOption> findUserNameForSearch(LoginResponse loginInfo) {
    	return searchMapper.findUserNameForSearch(loginInfo);
    }

	public List<SearchResponse> search(SearchForm form) {
		//TODO mapperで取得処理を実装
	    return searchMapper.search(form);
	}


}
