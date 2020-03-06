package kr.co.wishDream.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import kr.co.wishDream.document.DailyLifeGrid;

public interface DailyLifeGridRepository extends MongoRepository<DailyLifeGrid, Long> {


}
