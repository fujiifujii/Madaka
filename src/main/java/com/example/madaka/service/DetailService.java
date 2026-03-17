package com.example.madaka.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.madaka.response.DetailResponse;
import com.example.madaka.response.SearchResponse;

/**
 * @author yu-kudo
 * 検索画面のserviceクラス
 */
@Component
public class DetailService {

    /**
     * 検索結果リストから詳細画面へ渡す情報を取得する。
     *
     * @param searchResList
     * @param id
     * @return
     */
    public DetailResponse findRegistInfoById(List<SearchResponse> searchResList, Long id) {

    	DetailResponse detailInfo = new DetailResponse();

    	SearchResponse searchRes = searchResList.stream()
    		    .filter(item -> item.getLate_id().equals(id))
    		    .findFirst()
    		    .orElse(null);

    	mappingRegister(searchRes, detailInfo);

    	return detailInfo;
    }

    /**
     * SearchResponseの値をDetailResponseに詰める
     *
     * @param searchRes
     * @param detailInfo
     */
    private void mappingRegister(SearchResponse searchRes, DetailResponse detailInfo) {
    	detailInfo.setDate(searchRes.getLate_datetime().toLocalDate());
    	detailInfo.setLateId(searchRes.getLate_id());
    	detailInfo.setEmpId(searchRes.getEmp_id());
    	detailInfo.setLateReason(searchRes.getLate_reason());
    	detailInfo.setNote(searchRes.getNote());
    	detailInfo.setStartTime(searchRes.getStart_time());
    	detailInfo.setTrainDelayMinutes(searchRes.getTrain_delay_min());
    	detailInfo.setTrainName(searchRes.getTrain_name());
    }
}
