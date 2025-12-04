package com.example.madaka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.madaka.common.Const;
import com.example.madaka.form.SearchForm;
import com.example.madaka.response.SearchResponse;
import com.example.madaka.service.SearchService;

import jakarta.servlet.http.HttpSession;

/**
 * @author yu-kudo
 *
 */
@Component
@RequestMapping("/madaka")
@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

	/** 画面名 */
	final private String screenName = "検索画面";

	@GetMapping("/search")
	public String search(@RequestParam(required = false) SearchForm form
						,HttpSession session
						,Model model) {

		// SearchFormクラスがNULL＝検索画面初期表示の場合
		if (form != null) {

		} else {

		}

		model.addAttribute("searchForm", new SearchForm());
		model.addAttribute("AppName", Const.AppName);



		return "search";
	}

	// 検索ボタン押下
	@PostMapping("/search")
	public String doSearch(@ModelAttribute("searchForm") SearchForm form,
							@RequestParam(defaultValue = "1") int page,
							Model model) {
		//検索結果欄の1ページでの最大表示件数
		int pageSize = 10;

		// 検索処理
	    List<SearchResponse> searchResult = searchService.search(form);

	    int total = searchResult.size();
	    int totalPages = (int) Math.ceil((double) total / pageSize);
	    int fromIndex = Math.min((page - 1) * pageSize, total);
	    int toIndex = Math.min(fromIndex + pageSize, total);

	    List<SearchResponse> pagedResults = searchResult.subList(fromIndex, toIndex);

	    model.addAttribute("results", pagedResults);
	    model.addAttribute("resultCount", total);
	    model.addAttribute("currentPage", page);
	    model.addAttribute("totalPages", totalPages);
	    model.addAttribute("searchForm", form); // 再表示用

	    return "search";
	}
}