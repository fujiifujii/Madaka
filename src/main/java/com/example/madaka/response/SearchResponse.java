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
    private String late_id;
    /** 社員ID */
    private String emp_id;
    /** 社員氏名 */
    private String emp_name;
    /** 遅刻日時 */
    private LocalDateTime late_datetime;
    /** 遅刻理由 */
    private String late_reason;
    /** 遅刻分数 */
    private Integer late_min;
    /** 電車遅延分数 */
    private Integer train_delay_min;
    /** 電車ID */
    private String trainId;
    /** 電車名 */
    private String train_name;
    /** 始業時間 */
    private LocalTime start_time;
    /** 更新日時 */
    private LocalDateTime update_datetime;
    /** 備考 */
    private String note;
}
