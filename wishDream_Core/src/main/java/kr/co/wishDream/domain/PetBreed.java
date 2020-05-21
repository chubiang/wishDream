package kr.co.wishDream.domain;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Component
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetBreed {

	private Integer breedId;
	private Integer breedPid;
	private String breedName;
	private String breedEtc;
	
}
