package kr.co.wishDream.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.document.DailyLifeGrid;
import kr.co.wishDream.service.DailyLifeGridService;
import reactor.core.publisher.Mono;

@Component
public class PetHandler {
	
	@Autowired
	DailyLifeGridService dailyLifeGridService;

	public Mono<ServerResponse> dailyLifeGrid(ServerRequest request) {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(dailyLifeGridService.getAll(), DailyLifeGrid.class);
	}
	
}
