package com.example.madaka.repository;


import lombok.Data;


/**
 * チームマスタ情報
 * @author yu-kudo
 */
@Data
public class TeamMaster {
	/** チームID */
	private String teamId;
	/** チーム名 */
	private String teamName;
	/** ユニットNo */
	private String unitNo;
}
