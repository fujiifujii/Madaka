package com.example.madaka.response;

import lombok.Data;

@Data
public class LoginResponse {
	private String emp_Id;
	private String department_Id;
	private String team_Id;
	private String emp_No;
	private String role;
	private String emp_Lname;
	private String emp_Fname;
	private String emp_Lname_Kana;
	private String emp_Fname_Kana;
	private String gender;
	private String birthDay;
	private String startDate;
	private String belong;
	private String emp_Status;
	private String changeDate;
	private String mail_Address;
}
