package com.example.madaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.madaka.form.SearchForm;

import jakarta.servlet.http.HttpSession;

/**
 * @author yu-kudo
 *
 */

@RequestMapping("/madaka")
@Controller
public class SearchController {
	/** 画面名 */
	final private String screenName = "検索画面";

	@GetMapping("/search")
	public String search(HttpSession session, Model model) {
		model.addAttribute("searchForm", new SearchForm());
		return "search";
	}

	@PostMapping("/search")
	public String doSearch(@ModelAttribute("searchForm") SearchForm form, Model model) {
	    // 検索処理など
	    model.addAttribute("searchForm", form); // 再表示用
	    return "search";
	}
}