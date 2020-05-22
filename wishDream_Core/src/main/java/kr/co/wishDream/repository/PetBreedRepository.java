package kr.co.wishDream.repository;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import io.reactivex.Flowable;
import kr.co.wishDream.automap.PetBreed;
import kr.co.wishDream.automap.SubPetBreed;
import kr.co.wishDream.connect.DatabaseConnect;
import reactor.core.publisher.Mono;

@Repository
public class PetBreedRepository {

	@Autowired
	private DatabaseConnect databaseConnect;
	
	private Database database;
	
	public Mono<PetBreed> findPetBreedCategory()  {
		Flowable<PetBreed> petBreedFlowable = null;
		try {
			this.setDatabase(Database.from(databaseConnect.pool()));
			petBreedFlowable = database.select("SELECT * FROM pet_breed p WHERE p.breedPid = null")
			.autoMap(PetBreed.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Mono.from(petBreedFlowable);
	}
	
	public Mono<SubPetBreed> findByBreedIdSubPetBreed(Integer breedId) {
		Flowable<SubPetBreed> petBreedFlowable = null;
		
		try {
			this.setDatabase(Database.from(databaseConnect.pool()));
			petBreedFlowable = database.select("SELECT * FROM sub_pet_breed p WHERE p.breedId = ?")
				.parameter(breedId)
				.autoMap(SubPetBreed.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Mono.from(petBreedFlowable);
	}
	
	public void setDatabase(Database database) {
		this.database = database;
	}
}
