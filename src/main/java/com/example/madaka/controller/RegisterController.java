/**
 *
 */
package com.example.madaka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.madaka.response.RegisterResponse;
import com.example.madaka.service.RegisterService;

import jakarta.servlet.http.HttpSession;

/**
 * @author yu-fujii
 *
 */
@Controller
@RequestMapping("/madaka")
public class RegisterController {

	@Autowired
    private RegisterService registerService;

	//モデル初期化
	  @ModelAttribute("registerModel")
	  public RegisterResponse registerModel() {
	    return new RegisterResponse();
	  }

	// 登録画面初期表示
	  @GetMapping("/register")
	  public String show(@ModelAttribute("registerModel") RegisterResponse registerModel,
	                     Model model,
	                     HttpSession session) {


//		  List<TrainMaster> trainListFromSession = (List<TrainMaster>) session.getAttribute("trains");

//		    model.addAttribute("trainList", trainListFromSession); // Modelに直接追加

	      return "register";
	  }

	    // ★ 登録ボタン押下（POSTリクエスト）を処理するメソッド
	    @PostMapping("/register")
	    public String register(@Validated RegisterResponse form,
	                           BindingResult bindingResult,
	                           Model model) {

	        // 1. 【バリデーションチェック】
	        if (bindingResult.hasErrors()) {
	            // エラーがある場合、フォームを再表示してエラーメッセージを表示する
	            // ★ trainListなど、再表示に必要なデータはここでModelに再度追加する
	            // model.addAttribute("trainList", ...);
	            return "register";
	        }

	        // 2. 【ビジネスロジックの実行】
	        try {
//	            registerService.executeRegister(form);

	        } catch (Exception e) {
	            // 登録処理中に予期せぬエラーが発生した場合
	            model.addAttribute("errorMessage", "登録処理中にエラーが発生しました。");
	            return "register";
	        }

	        // 3. 【処理成功】完了画面へリダイレクト
	        return "redirect:/complete";
	    }

}
