package com.example.madaka.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

/**
 * @author yu-fujii
 * 遅刻履歴テーブル
 */
@Data
public class RegisterRepository {
	/** 遅刻ID */
    private String lateId;
    /** 社員ID */
    private String empId;
    /** 遅刻日時 */
    private LocalDateTime lateDatetime;
    /** 遅刻理由 */
    private String lateReason;
    /** 遅刻分数 */
    private Integer lateMin;
    /** 電車遅延分数 */
    private Integer trainDelayMin;
    /** 電車ID */
    private String trainId;
    /** 始業時間 */
    private LocalTime startTime;
    /** 更新日時 */
    private LocalDateTime updateDatetime;
    /** 備考 */
    private String note;
}
