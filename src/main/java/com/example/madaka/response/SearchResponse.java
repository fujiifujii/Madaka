package com.example.madaka.response;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;


/**
 * 検索画面の検索結果DTO
 */
@Data
public class SearchResponse {

	/** 遅刻ID */
    private String lateId;
    /** 社員ID */
    private String empId;
    /** 社員氏名 */
    private String empName;
    /** 遅刻日時 */
    private LocalDateTime lateDatetime;
    /** 遅刻理由 */
    private String lateReason;
    /** 遅刻分数 */
    private Integer lateMin;
    /** 電車遅延分数 */
    private Integer trainDelayMin;
    /** 電車ID */
    private String train;
    /** 電車名 */
    private String trainName;
    /** 始業時間 */
    private LocalTime startTime;
    /** 更新日時 */
    private LocalDateTime updateDatetime;
    /** 備考 */
    private String note;
}
