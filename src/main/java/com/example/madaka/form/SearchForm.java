package com.example.madaka.form;

import java.time.LocalDate;

import com.example.madaka.util.ReasonType;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

/**
 * @author yu-kudo
 * 検索画面のFormクラス
 */
@Data
public class SearchForm {

	//NOTE 初期値をnullにしたいのでInteger型にした

	/** from_年 */
	@Min(1900)
	private Integer fromYear;
	/** from_月 */
    @Min(1) @Max(12)
	private Integer fromMonth;
    /** from_日 */
    @Min(1) @Max(31)
	private Integer fromDay;
    /** to_年 */
    @Min(1900)
	private Integer toYear;
    /** to_月 */
    @Min(1) @Max(12)
	private Integer toMonth;
    /** to_日 */
	@Min(1) @Max(31)
	private Integer toDay;
	/** 氏名 */
	private String name;
	/** 遅刻理由 */
	private ReasonType reason;

	/**
	 *日付の初期値を当日に設定するコンストラクタ
	 */
    public SearchForm() {
        LocalDate today = LocalDate.now();
        this.fromYear = today.getYear();
        this.fromMonth = today.getMonthValue();
        this.fromDay = today.getDayOfMonth();
    }


	/**
	 * from年月日を生成
	 */
    public LocalDate getFromDate() {
        return LocalDate.of(fromYear, fromMonth, fromDay);
    }

	/**
	 * to年月日を生成
	 */
    public LocalDate getToDate() {
        return LocalDate.of(toYear, toMonth, toDay);
    }
}
