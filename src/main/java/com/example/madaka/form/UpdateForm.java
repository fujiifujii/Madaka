package com.example.madaka.form;

import java.time.LocalTime;

import lombok.Data;

@Data
public class UpdateForm {
	// 遅刻ID（更新対象キー）
	private String lateId;
	// 日付（yyyy/MM/dd）
	private String date;
	// 社員ID
	private String empId;
	// 遅刻理由
	private String lateReason;
	// 路線ID
	private String trainId;
	// 電車遅延時間
	private Integer trainDelayMinutes;
	// 始業時間
	private LocalTime startTime;
	// 備考
	private String note;
}
