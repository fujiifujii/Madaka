/**
 *
 */
package com.example.madaka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.madaka.response.DetailResponse;
import com.example.madaka.response.RegisterResponse;
import com.example.madaka.response.SearchResponse;
import com.example.madaka.service.DetailService;

import jakarta.servlet.http.HttpSession;

/**
 * @author yu-fujii
 *
 */
@Controller
@RequestMapping("/madaka")
public class DetailController {

    @Autowired
    private DetailService detailService;

	@GetMapping("/detail")
	public String show(Model model, HttpSession session){
		// セッションから登録データを取得
		RegisterResponse registerModel = (RegisterResponse) session.getAttribute("registerModel");

		model.addAttribute("registerModel", registerModel);
	    return "detail";
	}

	@SuppressWarnings("unchecked")
	@GetMapping("/detail/{id}")
	public String show(@RequestParam("id") Long id, Model model, HttpSession session){
		// セッションから登録データを取得
		List<SearchResponse> searchResult = (List<SearchResponse>) session.getAttribute("searchResult");
		DetailResponse detailModel = detailService.findRegistInfoById(searchResult, id);

		model.addAttribute("detailModel", detailModel);
		return "detail";
	}
}