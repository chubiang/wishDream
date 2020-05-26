package kr.co.wishDream.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;

import kr.co.wishDream.domain.PetBreed;
import kr.co.wishDream.domain.SubPetBreed;
import kr.co.wishDream.repository.PetBreedRepository;
import reactor.core.publisher.Mono;

@Component
public class PetBreedHandler {

	private Logger LOG = LoggerFactory.getLogger(PetBreedHandler.class);
	
	@Autowired
	private PetBreedRepository petBreedRepository;
	
	public Mono<ServerResponse> getPetBreedCategory() throws Exception {
		return ServerResponse
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(petBreedRepository.findPetBreedCategory(), 
						PetBreed.class
				);
	}
	
	public Mono<ServerResponse> getSubPetBreed(Integer breedId) throws Exception {
		return ServerResponse.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(petBreedRepository.findByBreedIdSubPetBreed(breedId), SubPetBreed.class);
	}
	
}
