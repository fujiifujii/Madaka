package com.example.madaka.repository;


import lombok.Data;


/**
 * 部署マスタ情報
 * @author yu-kudo
 */
@Data
public class DepartmentMaster {
	/** 部署ID */
	private String departmentId;
	/** 部署名 */
	private String departmentName;
}
