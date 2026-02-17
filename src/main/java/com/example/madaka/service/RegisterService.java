package com.example.madaka.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.madaka.form.RegisterForm;
import com.example.madaka.mapper.RegisterMapper;
import com.example.madaka.repository.RegisterRepository;

@Service
public class RegisterService {

	@Autowired
	private RegisterMapper registerMapper;

	/**
	 * 遅刻情報を登録する
	 *
	 * @param form 登録フォーム
	 * @param empId 社員ID
	 */
	public void register(RegisterForm form, String empId) {
		// late_idの生成：社員ID(10桁) + タイムスタンプ(yyMMdd HHmmss) + 連番2桁
		String lateId = generateLateId(empId);

		// RegisterRepositoryに値を詰める
		RegisterRepository repository = new RegisterRepository();
		repository.setLateId(lateId);
		repository.setEmpId(empId);
		repository.setLateDatetime(LocalDateTime.of(form.getDate(), form.getStartTime()));
		repository.setLateReason(form.getLateReason());
		repository.setLateMin(null);//到着時に計算する
		repository.setTrainDelayMin(form.getTrainDelayMinutes());
		repository.setTrainId(form.getTrainId());
		repository.setStartTime(form.getStartTime());
		repository.setUpdateDatetime(LocalDateTime.now());
		repository.setNote(form.getNote());

		// DBへインサート
		registerMapper.insert(repository);
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
}
