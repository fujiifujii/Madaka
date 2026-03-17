package com.example.madaka.repository;

import lombok.Data;

@Data
public class EmployeeMaster {
	private String empId; //社員ID
	private String departmentId; //	部署
	private String teamId; //チームID
	private String empNo; //社員番号
	private String role; //役職
	private String empLeame; //社員姓
	private String empFname; //社員名
	private String empLeameKana; //社員セイ
	private String empFnameKana; //社員メイ
	private String gender; //性別
	private String birthDay; //誕生日
	private String startDate; //入社日
	private String belong; //所属
	private String empStatus; //状態
	private String changeDate; //状態変更日
	private String mailAddress; //メールアドレス
}
