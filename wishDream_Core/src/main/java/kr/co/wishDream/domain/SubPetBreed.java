package kr.co.wishDream.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubPetBreed {

	private Integer subBreedId;
	private Integer breedId;
	private String breedName;
	private String breedEtc;
}
