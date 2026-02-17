package com.example.madaka.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.madaka.form.RegisterForm;
import com.example.madaka.mapper.RegisterMapper;
import com.example.madaka.repository.RegisterRepository;

@Service
public class RegisterService {
	private static final DateTimeFormatter SLASH_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");
	private static final DateTimeFormatter HYPHEN_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	@Autowired
	private RegisterMapper registerMapper;

	/**
	 * 遅刻情報を登録する
	 *
	 * @param form 登録フォーム
	 * @param empId 社員ID
	 * @return 生成した遅刻ID
	 */
	public String register(RegisterForm form, String empId) {
		// late_idの生成：社員ID(10桁) + タイムスタンプ(yyMMdd HHmmss) + 連番2桁
		String lateId = generateLateId(empId);

		// RegisterRepositoryに値を詰める
		RegisterRepository repository = new RegisterRepository();
		repository.setLateId(lateId);
		repository.setEmpId(empId);
		LocalDate registerDate = parseDate(form.getDate());
		LocalTime startTime = form.getStartTime() != null ? form.getStartTime() : LocalTime.of(9, 0);
		repository.setLateDatetime(LocalDateTime.of(registerDate, startTime));
		repository.setLateReason(form.getLateReason());
		repository.setLateMin(null);//到着時に計算する
		repository.setTrainDelayMin(form.getTrainDelayMinutes());
		repository.setTrainId(form.getTrainId());
		repository.setStartTime(startTime);
		repository.setUpdateDatetime(LocalDateTime.now());
		repository.setNote(form.getNote());

		// DBへインサート
		registerMapper.insert(repository);
		return lateId;
	}

	/**
	 * late_idを生成する
	 * フォーマット：社員ID(10桁) + タイムスタンプ(yyMMdd HHmmss) + 連番2桁
	 *
	 * @param empId 社員ID
	 * @return 生成されたlate_id
	 */
	private String generateLateId(String empId) {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyMMddHHmmss");
		String timestamp = now.format(formatter);

		// 連番2桁（簡略的な実装：00から始まる）
		// 実装では、同一社員の同一日時に複数登録がある場合の連番を考慮する必要があります
		String sequenceNumber = "00";

		return empId + timestamp + sequenceNumber;
	}

	private LocalDate parseDate(String dateText) {
		if (dateText == null || dateText.isBlank()) {
			return LocalDate.now();
		}
		try {
			return LocalDate.parse(dateText, SLASH_DATE_FORMATTER);
		} catch (Exception e) {
			return LocalDate.parse(dateText, HYPHEN_DATE_FORMATTER);
		}
	}
}
