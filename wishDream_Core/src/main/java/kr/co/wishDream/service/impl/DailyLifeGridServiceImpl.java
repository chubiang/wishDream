package kr.co.wishDream.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.stereotype.Service;

import kr.co.wishDream.document.DailyLifeGrid;
import kr.co.wishDream.repository.DailyLifeGridRepository;
import kr.co.wishDream.service.DailyLifeGridService;
import reactor.core.publisher.Flux;


@Service
public class DailyLifeGridServiceImpl implements DailyLifeGridService {

	private final DailyLifeGridRepository dailyLifeGridRepository;
	
	@Autowired
	ReactiveMongoTemplate mongoTemplate;
	
	public DailyLifeGridServiceImpl(DailyLifeGridRepository dailyLifeGridRepository) {
		this.dailyLifeGridRepository = dailyLifeGridRepository;
	}

	@Override
	public Flux<DailyLifeGrid> getAll() {
		return dailyLifeGridRepository.findAll(Sort.by("creDate"));
	}
}
