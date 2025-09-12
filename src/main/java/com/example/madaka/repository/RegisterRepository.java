package com.example.madaka.repository;

import java.time.LocalDateTime;
import java.time.LocalTime;

import lombok.Data;

@Data
public class RegisterRepository {
    private String lateId;                 // 遅刻ID
    private String empId;                  // 社員ID
    private LocalDateTime lateDatetime;    // 遅刻日時
    private String lateReason;             // 遅刻理由
    private Integer lateMin;               // 遅刻分数
    private Integer trainDelayMin;         // 電車遅延分数
    private String trainId;                // 電車ID
    private LocalTime startTime;           // 始業時間
    private LocalDateTime updateDatetime;  // 更新日時
    private String note;                   // 備考
}
