package com.example.madaka.repository;


import lombok.Data;


/**
 * ログインテーブル
 * @author yu-kudo
 */
@Data
public class UserMaster {
	/** 社員ID */
	private String empId;
	/** パスワード */
	private String password;
}
