package com.example.madaka.response;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import com.example.madaka.repository.TrainMaster;

import lombok.Data;

@Data
public class RegisterResponse {

	private LocalDate date; // 日付
	private String lateId; // 遅刻ID
	private String empId; // 社員ID
	private String lateReason; // 遅刻理由
	private List<TrainMaster> trainList; // 電車リスト
	private String trainId; // 電車ID
	private Integer trainDelayMinutes; // 遅刻時間
	private LocalTime startTime; // 始業時間
	private String note; // 備考
}
