package kr.co.wishDream.repository;

import org.springframework.data.domain.Page;

import kr.co.wishDream.document.DailyLifeGrid;
import kr.co.wishDream.request.DailyLifeGridSearch;

public interface DailyLifeGridRepositoryCustom {

	public Page<DailyLifeGrid> searchDailyLifeGrid(DailyLifeGridSearch search);
	
}
