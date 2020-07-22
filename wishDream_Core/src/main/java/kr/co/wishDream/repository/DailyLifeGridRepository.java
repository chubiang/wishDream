package kr.co.wishDream.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.wishDream.document.DailyLifeGrid;

public interface DailyLifeGridRepository extends MongoRepository<DailyLifeGrid, Long> {

	public List<DailyLifeGrid> findAll();

}
