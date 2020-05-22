package kr.co.wishDream.automap;

import org.davidmoten.rx.jdbc.annotations.Column;

public interface SubPetBreed {

	@Column
	Integer subBreedId();
	@Column
	Integer breedId();
	@Column
	String breedName();
	@Column
	String breedEtc();
}
