package com.example.madaka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.madaka.common.Const;
import com.example.madaka.form.SearchForm;
import com.example.madaka.response.LoginResponse;
import com.example.madaka.response.SearchResponse;
import com.example.madaka.service.SearchService;

import jakarta.servlet.http.HttpSession;

/**
 * @author yu-kudo
 *
 */
@Controller
@RequestMapping("/madaka")
@SessionAttributes({"searchForm", "searchResult"})
public class SearchController {

    @Autowired
    private SearchService searchService;

	/** 画面名 */
	final private String screenName = "検索画面";

	@GetMapping("/search")
	public String search(HttpSession session,
						Model model) {

		// セッションからログインユーザー情報を取得
		LoginResponse loginInfo = (LoginResponse) session.getAttribute("loginUser");
		//プルダウンに表示するユーザ名を取得
		List<SelectOption> employees = searchService.findUserNameForSearch(loginInfo);
		session.setAttribute("employees", employees);
        model.addAttribute("employees", employees);
		model.addAttribute("searchForm", new SearchForm());
		model.addAttribute("AppName", Const.appName);
		model.addAttribute("searchResult", List.of());
		model.addAttribute("results", List.of());

		return "search";
	}

    /** 検索ボタン押下（1ページ目） */
    @PostMapping("/search")
    public String search(
            @ModelAttribute("searchForm") SearchForm form,
            Model model) {

        // 検索実行
        List<SearchResponse> searchResult = searchService.search(form);

        // 検索結果をセッションに保存
        model.addAttribute("searchResult", searchResult);

        // 1ページ目へリダイレクト
        return "redirect:/madaka/search?page=1";
    }

    /** ページング（GET） */
    @GetMapping(value = "/search", params = "page")
    public String searchPaging(
            @RequestParam("page") int page,
            @ModelAttribute("searchForm") SearchForm form,
            @ModelAttribute("searchResult") List<SearchResponse> searchResult,
            Model model) {

        int pageSize = 10;
        int total = searchResult.size();

        int totalPages = (int) Math.ceil((double) total / pageSize);
        int fromIndex = Math.min((page - 1) * pageSize, total);
        int toIndex = Math.min(fromIndex + pageSize, total);

        List<SearchResponse> pagedResults = searchResult.subList(fromIndex, toIndex);

        model.addAttribute("results", pagedResults);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("resultCount", total);

        return "search";
    }

    /** プルダウン用の軽量 DTO（クラス内クラス） */
    public static class SelectOption {
        private Integer value;
        private String label;

        public SelectOption(Integer value, String label) {
            this.value = value;
            this.label = label;
        }

        public Integer getValue() { return value; }
        public String getLabel() { return label; }
    }

}