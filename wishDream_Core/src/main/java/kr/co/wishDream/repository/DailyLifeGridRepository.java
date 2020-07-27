package kr.co.wishDream.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import kr.co.wishDream.document.DailyLifeGrid;

@Repository
public interface DailyLifeGridRepository 
	extends ReactiveMongoRepository<DailyLifeGrid, Long> {

}
