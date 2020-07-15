package kr.co.wishDream.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoTemplate;

import kr.co.wishDream.document.DailyLifeGrid;
import kr.co.wishDream.repository.DailyLifeGridRepository;
import kr.co.wishDream.repository.DailyLifeGridRepositoryCustom;
import kr.co.wishDream.request.DailyLifeGridSearch;
import kr.co.wishDream.service.DailyLifeGridService;

public class DailyLifeGridServiceImpl implements DailyLifeGridService, DailyLifeGridRepositoryCustom {

	@Autowired
	DailyLifeGridRepository dailyLifeGridRepository;
	
	@Autowired
	MongoTemplate mongoTemplate;

	@Override
	public Page<DailyLifeGrid> searchDailyLifeGrid(DailyLifeGridSearch search) {
		return null;
	}
}
