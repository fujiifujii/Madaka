package com.example.madaka.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		LocalDate from = null;
		LocalDate to = null;

		if (form.getFromYear() != null && form.getFromMonth() != null && form.getFromDay() != null) {
		    from = LocalDate.of(form.getFromYear(), form.getFromMonth(), form.getFromDay());
		}

		if (form.getToYear() != null && form.getToMonth() != null && form.getToDay() != null) {
		    to = LocalDate.of(form.getToYear(), form.getToMonth(), form.getToDay());
		}

		// From <= To チェック
		if (from != null && to != null && from.isAfter(to)) {
		    throw new IllegalArgumentException("日付の範囲が不正です");
		}

		LocalDateTime fromDateTime = (from != null) ? from.atStartOfDay() : null;
		LocalDateTime toDateTime = (to != null) ? to.atTime(23, 59, 59) : null;

		// MyBatis に渡すパラメータ
		Map<String, Object> params = new HashMap<>();
		params.put("fromDate", fromDateTime);
		params.put("toDate", toDateTime);
		params.put("employeeId", form.getEmpId());
		params.put("reason", form.getReason());

		return searchMapper.search(params);
	}


}
