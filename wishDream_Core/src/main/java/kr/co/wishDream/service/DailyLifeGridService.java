package kr.co.wishDream.service;

import kr.co.wishDream.document.DailyLifeGrid;
import reactor.core.publisher.Flux;

public interface DailyLifeGridService {

	public Flux<DailyLifeGrid> getAll();
	
}
