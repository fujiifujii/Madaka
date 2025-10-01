package com.example.madaka.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import com.example.madaka.form.SearchForm;
import com.example.madaka.response.SearchResponse;

/**
 * @author yu-kudo
 * 検索画面のmapperクラス
 */
@Mapper
public interface SearchMapper {

	List<SearchResponse> search(SearchForm form);
}
