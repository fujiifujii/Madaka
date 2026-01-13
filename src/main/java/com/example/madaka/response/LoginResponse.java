package com.example.madaka.response;

import lombok.Data;

@Data
public class LoginResponse {
	private String empId;
	private String departmentId;
	private String teamId;
	private String empNo;
	private String role;
	private String empLname;
	private String empFname;
	private String empLnameKana;
	private String empFnameKana;
	private String gender;
	private String birthDay;
	private String startDate;
	private String belong;
	private String empStatus;
	private String changeDate;
	private String mailAddress;
	private String unitNo;
}
