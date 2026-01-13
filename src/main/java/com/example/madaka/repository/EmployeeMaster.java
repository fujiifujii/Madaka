package com.example.madaka.repository;


import lombok.Data;


/**
 * 社員マスタ情報
 * @author yu-kudo
 */
@Data
public class EmployeeMaster {
	/** 社員ID */
	private String empId;
	/** 部署ID */
	private String departmentId;
	/** チームID */
	private String teamId;
	/** 社員No */
	private String empNo;
	/** 役職 */
	private String role;
	/** 社員姓 */
	private String empLeame;
	/** 社員名 */
	private String empFname;
	/** 社員セイ */
	private String empLeameKana;
	/** 社員メイ */
	private String empFnameKana;
	/** 性別 */
	private String gender;
	/** 生年月日 */
	private String birthDay;
	/** 入社日 */
	private String startDate;
	/** 所属 */
	private String belong;
	/** 状態 */
	private String empStatus;
	/** 状態変更日 */
	private String changeDate;
	/** メールアドレス */
	private String mailAddress;
}
