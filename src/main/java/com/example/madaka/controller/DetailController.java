/**
 *
 */
package com.example.madaka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.madaka.form.DetailForm;
import com.example.madaka.response.DetailResponse;
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

	@GetMapping("/detail/{id}")
	public String show(@PathVariable String	 id, Model model, HttpSession session){
		DetailForm detailModel = new DetailForm();
		DetailResponse lateInfo = detailService.getLateInfo(id);
		detailService.mappingDetail(lateInfo, detailModel);

		model.addAttribute("detailModel", detailModel);
		return "detail";
	}
}