package kr.co.wishDream.repository;

import java.util.List;

import org.davidmoten.rx.jdbc.Database;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.wishDream.connect.DatabaseConnect;
import kr.co.wishDream.domain.PetBreed;

@Repository
public class PetBreedRepository {

	@Autowired
	private DatabaseConnect databaseConnect;
	
	private Database database;
	
	public List<PetBreed> findPetBreedCategory() throws Exception {
		this.setDatabase(Database.from(databaseConnect.pool()));
		return null;
	}
	
	public List<PetBreed> findByBreedIdSubPetBreed(Integer breedId) throws Exception {
		this.setDatabase(Database.from(databaseConnect.pool()));
		return null;
	}
	
	public void setDatabase(Database database) {
		this.database = database;
	}
}
