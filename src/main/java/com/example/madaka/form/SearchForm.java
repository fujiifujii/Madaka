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

	/** from_年 */
	@Min(1900)
	private int fromYear;
	/** from_月 */
    @Min(1) @Max(12)
	private int fromMonth;
    /** from_日 */
    @Min(1) @Max(31)
	private int fromDay;
    /** to_年 */
    @Min(1900)
	private int toYear;
    /** to_月 */
    @Min(1) @Max(12)
	private int toMonth;
    /** to_日 */
	@Min(1) @Max(31)
	private int toDay;
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
        this.toYear = today.getYear();
        this.toMonth = today.getMonthValue();
        this.toDay = today.getDayOfMonth();
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
