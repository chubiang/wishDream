package kr.co.wishDream.repository;

import org.springframework.data.domain.Page;

import dailyLifeGridRepository.DailyLifeGridSearch;
import kr.co.wishDream.document.DailyLifeGrid;

public interface DailyLifeGridRepositoryCustom {

	public Page<DailyLifeGrid> searchDailyLifeGrid(DailyLifeGridSearch search);
	
}
