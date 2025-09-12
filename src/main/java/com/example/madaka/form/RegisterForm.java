package com.example.madaka.form;

import java.time.LocalDate;
import java.time.LocalTime;

import lombok.Data;

@Data
public class RegisterForm {

	// 日付
	private LocalDate date;
	// 社員名
	private String empName;
	// 遅刻理由
	private String lateReason;
	// 路線名
	private String lineName;
	// 遅刻時間
	private Integer delayMinutes;
	// 始業時間
	private LocalTime startTime;
	// 備考
	private String note;

}
