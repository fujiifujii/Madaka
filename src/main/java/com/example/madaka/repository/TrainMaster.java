package com.example.madaka.repository;


import lombok.Data;


/**
 * 電車マスタ情報
 * @author yu-kudo
 */
@Data
public class TrainMaster {
	/** 電車ID */
	private String trainId;
	/** 電車名 */
	private String trainName;
}
