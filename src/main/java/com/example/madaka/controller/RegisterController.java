/**
 *
 */
package com.example.madaka.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.madaka.form.RegisterForm;
import com.example.madaka.repository.TrainMaster;
import com.example.madaka.response.LoginResponse;
import com.example.madaka.response.RegisterResponse;
import com.example.madaka.service.LoginService;
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

	@Autowired
	private LoginService loginService;

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

		  // セッションから電車名（trains）を取得
		  List<TrainMaster> trainMaster = loginService.getTrainMaster();

		  if (trainMaster != null) {
			  // セッションに電車名を設定
			  session.setAttribute("trains", trainMaster);
		  }

	      return "register";
	  }

	    // ★ 登録ボタン押下（POSTリクエスト）を処理するメソッド
	    @PostMapping("/register")
	    public String register(@Validated RegisterForm form,
	                           BindingResult bindingResult,
	                           Model model,
	                           HttpSession session) {

	        // 1. 【バリデーションチェック】
	        if (bindingResult.hasErrors()) {
	            // エラーがある場合、フォームを再表示してエラーメッセージを表示する
	            // 再表示に必要なデータをModelに追加
	            List<TrainMaster> trainMaster = loginService.getTrainMaster();
	            if (trainMaster != null) {
	                model.addAttribute("trainList", trainMaster);
	            }
	            return "register";
	        }

	        // 2. 【ビジネスロジックの実行】
//	        try {
	            // セッションからログイン情報（社員ID）を取得
	            LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");

//	            if (loginUser != null) {
	                // RegisterServiceを使用して遅刻情報を登録
	                registerService.register(form, loginUser.getEmpId());

//	                // 登録データをセッションに保持（detail画面で使用）
//	                session.setAttribute("registerModel", form);
//	            } else {
//	                // ログイン情報がない場合はエラーメッセージを表示
//	                model.addAttribute("errorMessage", "ログイン情報が見つかりません。");
//	                return "register";
//	            }
//
//	        } catch (Exception e) {
//	            // 登録処理中に予期せぬエラーが発生した場合
//	            model.addAttribute("errorMessage", "登録処理中にエラーが発生しました。");
//	            return "register";
//	        }

	        // 3. 【処理成功】detail画面へリダイレクト
	        return "redirect:/madaka/detail";
	    }

	    // detail画面初期表示
	    @GetMapping("/detail")
	    public String showDetail(Model model,
	                             HttpSession session) {
	        // セッションから登録データを取得
	        RegisterResponse registerModel = (RegisterResponse) session.getAttribute("registerModel");

	        if (registerModel != null) {
	            model.addAttribute("registerModel", registerModel);
	        }

	        return "detail";
	    }
}
