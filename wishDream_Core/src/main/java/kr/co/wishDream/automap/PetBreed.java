package kr.co.wishDream.automap;

import org.davidmoten.rx.jdbc.annotations.Column;

public interface PetBreed {

	@Column
	Integer breedId();
	
	@Column
	Integer breedPid();
	
	@Column
	String breedName();
	
	@Column
	String breedEtc();
	
}
