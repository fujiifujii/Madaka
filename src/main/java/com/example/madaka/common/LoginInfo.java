package com.example.madaka.common;

import lombok.Data;

/**
 * @author yu-kudo
 * ログイン時にとってきてセッションで持ちまわる
 * 取得元は社員マスタとチームマスタ
 */
@Data
public class LoginInfo {

	/** 社員ID */
	private String empId;
	/** チームID */
	private String teamId;
	/** 役職 */
	private int role;
	/** 社員名_姓 */
	private String empLname;
	/** 社員名_名 */
	private String empFname;
	/** 社員名_姓 */
	private String empLnameKana;
	/** 社員名_名 */
	private String empFnameKana;
	/** ユニット */
	private int unitNo;

}
