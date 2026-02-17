package com.example.madaka.form;

import java.time.LocalTime;

import lombok.Data;

@Data
public class UpdateForm {
	private String lateId;
	private String date;
	private String empId;
	private String lateReason;
	private String trainId;
	private Integer trainDelayMinutes;
	private LocalTime startTime;
	private String note;
}
