package com.example.madaka.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.madaka.form.UpdateForm;
import com.example.madaka.repository.EmployeeMaster;
import com.example.madaka.repository.RegisterRepository;
import com.example.madaka.repository.TeamMaster;
import com.example.madaka.repository.TrainMaster;
import com.example.madaka.response.LoginResponse;
import com.example.madaka.response.RegisterResponse;
import com.example.madaka.service.LoginService;
import com.example.madaka.service.UpdateService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/madaka")
public class UpdateController {
	// 日付入力許容フォーマット（画面入力の揺れを吸収）
	private static final DateTimeFormatter SLASH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static final DateTimeFormatter HYPHEN_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	// DBのTIME値を画面表示（HH:mm）に合わせる
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
	private static final String ROLE_GENERAL_EMPLOYEE = "1";
	private static final String ROLE_CHIEF = "2";
	private static final String ROLE_LEADER = "3";
	private static final String ROLE_ASSISTANT_MANAGER = "4";
	private static final String ROLE_MANAGER = "5";

	private final UpdateService updateService;
	private final LoginService loginService;

	public UpdateController(UpdateService updateService, LoginService loginService) {
		this.updateService = updateService;
		this.loginService = loginService;
	}

	// 詳細画面から遷移した更新画面の初期表示
	@PostMapping("/update")
	public String showUpdate(@RequestParam("lateId") String lateId,
	                         Model model,
	                         HttpSession session) {
		// 登録画面と同じ社員プルダウン制御を適用
		prepareRegisterLikeScreenModel(model, session);

		// 遅刻IDで対象データを取得
		RegisterRepository registerData = updateService.findByLateId(lateId);
		if (registerData == null) {
			model.addAttribute("errorMessage", "対象データが見つかりません。");
			RegisterResponse registerModel = (RegisterResponse) session.getAttribute("registerModel");
			if (registerModel != null) {
				model.addAttribute("registerModel", registerModel);
			}
			return "detail";
		}

		RegisterResponse updateModel = new RegisterResponse();
		updateModel.setLateId(registerData.getLateId());
		updateModel.setEmpId(registerData.getEmpId());
		if (registerData.getLateDatetime() != null) {
			updateModel.setDate(registerData.getLateDatetime().toLocalDate());
		}
		updateModel.setLateReason(registerData.getLateReason());
		updateModel.setTrainId(registerData.getTrainId());
		updateModel.setTrainDelayMinutes(registerData.getTrainDelayMin());
		updateModel.setStartTime(registerData.getStartTime());
		updateModel.setNote(registerData.getNote());

		model.addAttribute("updateModel", updateModel);
		// START_TIME(TIME) を画面入力用 HH:mm 文字列に変換
		model.addAttribute("initialStartTime", registerData.getStartTime() != null
			? registerData.getStartTime().format(TIME_FORMATTER)
			: "");
		return "update";
	}

	// 更新ボタン押下時のDB更新処理
	@PostMapping("/update/submit")
	public String submitUpdate(UpdateForm form,
	                           HttpSession session) {
		updateService.update(form);

		RegisterResponse registerModel = new RegisterResponse();
		registerModel.setLateId(form.getLateId());
		registerModel.setEmpId(form.getEmpId());
		registerModel.setDate(parseDate(form.getDate()));
		registerModel.setLateReason(form.getLateReason());
		registerModel.setTrainId(form.getTrainId());
		registerModel.setTrainDelayMinutes(form.getTrainDelayMinutes());
		registerModel.setStartTime(form.getStartTime());
		registerModel.setNote(form.getNote());
		session.setAttribute("registerModel", registerModel);

		return "redirect:/madaka/detail";
	}

	// 社員名プルダウンの活性/候補をログイン権限で制御
	private void prepareRegisterLikeScreenModel(Model model, HttpSession session) {
		List<TrainMaster> trainMaster = loginService.getTrainMaster();
		if (trainMaster != null) {
			session.setAttribute("trains", trainMaster);
		}

		List<EmployeeMaster> employeeMasterRaw = loginService.getEmployeeMaster();
		final List<EmployeeMaster> employeeMaster = employeeMasterRaw == null ? List.of() : employeeMasterRaw;

		LoginResponse loginUser = (LoginResponse) session.getAttribute("loginUser");
		boolean disableEmpSelect = false;
		List<EmployeeMaster> filteredEmployeeList = employeeMaster;
		if (loginUser != null) {
			model.addAttribute("loginEmpId", loginUser.getEmpId());

			Optional<EmployeeMaster> loginEmployee = employeeMaster.stream()
				.filter(emp -> emp.getEmpId() != null && emp.getEmpId().equals(loginUser.getEmpId()))
				.findFirst();

			filteredEmployeeList = loginEmployee.map(emp -> {
				String role = emp.getRole();
				if (ROLE_GENERAL_EMPLOYEE.equals(role)) {
					return employeeMaster.stream()
						.filter(e -> e.getEmpId() != null && e.getEmpId().equals(emp.getEmpId()))
						.collect(Collectors.toList());
				}
				if (ROLE_CHIEF.equals(role) || ROLE_LEADER.equals(role)) {
					return employeeMaster.stream()
						.filter(e -> e.getTeamId() != null && e.getTeamId().equals(emp.getTeamId()))
						.filter(e -> "1".equals(e.getBelong()))
						.filter(e -> e.getChangeDate() == null || !"9".equals(e.getChangeDate()))
						.collect(Collectors.toList());
				}
				if (ROLE_ASSISTANT_MANAGER.equals(role) || ROLE_MANAGER.equals(role)) {
					List<TeamMaster> teamMasterRaw = loginService.getTeamMaster();
					List<TeamMaster> teamMaster = teamMasterRaw == null ? List.of() : teamMasterRaw;

					Optional<String> loginUnitNo = teamMaster.stream()
						.filter(team -> team.getTeamId() != null && team.getTeamId().equals(emp.getTeamId()))
						.map(TeamMaster::getUnitNo)
						.filter(unitNo -> unitNo != null && !unitNo.isBlank())
						.findFirst();

					if (loginUnitNo.isEmpty()) {
						return employeeMaster.stream()
							.filter(e -> e.getTeamId() != null && e.getTeamId().equals(emp.getTeamId()))
							.filter(e -> "1".equals(e.getBelong()))
							.filter(e -> e.getEmpStatus() == null || !"9".equals(e.getEmpStatus()))
							.collect(Collectors.toList());
					}

					Set<String> unitTeamIds = teamMaster.stream()
						.filter(team -> loginUnitNo.get().equals(team.getUnitNo()))
						.map(TeamMaster::getTeamId)
						.filter(teamId -> teamId != null && !teamId.isBlank())
						.collect(Collectors.toSet());

					return employeeMaster.stream()
						.filter(e -> e.getTeamId() != null && unitTeamIds.contains(e.getTeamId()))
						.filter(e -> "1".equals(e.getBelong()))
						.filter(e -> e.getEmpStatus() == null || !"9".equals(e.getEmpStatus()))
						.collect(Collectors.toList());
				}
				return employeeMaster;
			}).orElse(employeeMaster);

			disableEmpSelect = loginEmployee
				.map(EmployeeMaster::getRole)
				.map(ROLE_GENERAL_EMPLOYEE::equals)
				.orElse(false);
		}

		model.addAttribute("disableEmpSelect", disableEmpSelect);
		model.addAttribute("employeeList", filteredEmployeeList);
	}

	// 日付文字列を LocalDate に変換
	private LocalDate parseDate(String dateText) {
		if (dateText == null || dateText.isBlank()) {
			return null;
		}
		try {
			return LocalDate.parse(dateText, SLASH_DATE_FORMATTER);
		} catch (Exception e) {
			return LocalDate.parse(dateText, HYPHEN_DATE_FORMATTER);
		}
	}
}
