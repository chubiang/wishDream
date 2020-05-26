package kr.co.wishDream.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.davidmoten.rx.jdbc.Database;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.co.wishDream.connect.DatabaseConnect;
import kr.co.wishDream.domain.PetBreed;
import kr.co.wishDream.domain.SubPetBreed;
import reactor.core.publisher.Mono;

@Repository
public class PetBreedRepository {

	private Logger LOG = LoggerFactory.getLogger(PetBreedRepository.class);
	
	@Autowired
	private DatabaseConnect databaseConnect;
	
	private Database database;
	

	public Mono<List<?>> findPetBreedCategory()  {
		List<PetBreed> list = new ArrayList<>();
		List<PetBreed> list2 = new ArrayList<>();
		List<Object> result =  new ArrayList<>();
		try {
			this.setDatabase(Database.from(databaseConnect.pool()));
			database.select("SELECT * FROM pet_breed WHERE breed_pid is null")
			.getTupleN(PetBreed.class).blockingIterable().forEach(onNext-> {
				PetBreed pet = new PetBreed();
				List<?> values = onNext.values();
				pet.setBreedId((Integer) values.get(0));
				pet.setBreedPid((Integer) values.get(1));
				pet.setBreedName((String) values.get(2));
				pet.setBreedEtc((String) values.get(3));
				list.add(pet);
			});
			database.select("SELECT * FROM pet_breed WHERE breed_pid is not null")
			.getTupleN(PetBreed.class).blockingIterable().forEach(onNext-> {
				PetBreed pet = new PetBreed();
				List<?> values = onNext.values();
				pet.setBreedId((Integer) values.get(0));
				pet.setBreedPid((Integer) values.get(1));
				pet.setBreedName((String) values.get(2));
				pet.setBreedEtc((String) values.get(3));
				list2.add(pet);
			});
			for (int i = 0; i < list.size(); i++) {
				List<PetBreed> it = new ArrayList<>();
				for (int j = 0; j < list2.size(); j++) {
					if ( list.get(i).getBreedId() == list2.get(j).getBreedPid()) {
						if (it.isEmpty()) {					
							it.add(list.get(i));
						}
						it.add(list2.get(j));
					}
				}
				if(it.isEmpty()) {
					result.add(list.get(i));
				} else {
					result.add(it);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Mono.just(result);
	}
	
	public Mono<List<SubPetBreed>> findByBreedIdSubPetBreed(Integer breedId) {
		List<SubPetBreed> list = new ArrayList<>();
		try {
			this.setDatabase(Database.from(databaseConnect.pool()));
			database.select("SELECT p.* FROM sub_pet_breed p WHERE p.breed_id = ?")
				.parameter(breedId)
				.getTupleN().blockingIterable().forEach(
					e -> {
						List<?> values = e.values();
						SubPetBreed subBreed = new SubPetBreed();
						subBreed.setBreedId((Integer) values.get(0));
						subBreed.setSubBreedId((Integer) values.get(1));
						subBreed.setBreedName((String) values.get(2));
						subBreed.setBreedEtc((String) values.get(3));
						list.add(subBreed);
					
				});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Mono.just(list);
	}
	
	public void setDatabase(Database database) {
		this.database = database;
	}
}
