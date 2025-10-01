package com.example.madaka.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/madaka/search")
@Controller
public class SearchController {
	/** 画面名 */
	final private String screenName = "検索画面";

	@GetMapping
	public String search() {
		return "search";
	}
}