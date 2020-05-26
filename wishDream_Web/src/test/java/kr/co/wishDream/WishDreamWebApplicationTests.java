package kr.co.wishDream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.assertj.core.util.Arrays;
import org.davidmoten.rx.jdbc.Database;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.client.OAuth2ClientProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.PetBreed;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WishDreamWebApplicationTests {
	
	@Autowired
	private TestDatabaseConnect testDatabaseConnect;
	
	@Autowired
	OAuth2ClientProperties properties;
	
	private Database database;
	
	Mono<Member> member;

	@Autowired
	InMemoryReactiveClientRegistrationRepository clientRegistrationRepository;
	
	@Test
	public void contextLoads() throws Exception {
		List<Object> list = findPetBreedCategory();
		list.forEach(x->System.out.println("list = "+ x));
		
//		String email = "nana@gmail.com";
//		List<ClientRegistration> registrations = StreamSupport.stream(clientRegistrationRepository.spliterator(), true)
//				.collect(Collectors.toList());
//		registrations.forEach(System.out::println);
//		Pattern urlMatcher = Pattern.compile("(login)|(\\/)*[!@#$%^&*(),.?\\\\\"~`:{}|<>+=_-]*");
//		System.out.println(urlMatcher.matcher("/fdsfddd.ddd.ffffffffffff&%$##@$()+=-_\"\\~`").find());
//		Flowable<Member> mem = findByEmailAutoMap(email);
//		member = Mono.justOrEmpty(mem.toObservable().blockingSingle());
//		System.out.print("mem = ");
//		member.subscribe(x -> System.out.println(x));
		
//		System.out.println(findByUsername(email).blockOptional().get().toString());
		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String password = encoder.encode("123");
//		System.out.println(password);
		
//		System.out.println(System.getProperty("java.version"));
	}
	
	public List<Object> findPetBreedCategory()  {
		List<PetBreed> list = new ArrayList<>();
		List<PetBreed> list2 = new ArrayList<>();
		List<Object> result =  new ArrayList<>();
		try {
			this.setDatabase(Database.from(testDatabaseConnect.pool()));
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
//			result = list.stream().filter(x-> x.getBreedPid() == null).map( item -> {
//				list2.stream().filter(x -> x.getBreedPid() == item.getBreedId()).forEach(x-> System.out.println(x));
//				Boolean condition = list2.stream().filter(x -> x.getBreedPid() == item.getBreedId()).anyMatch(x-> x.getBreedPid() == item.getBreedId());
//				if (condition) {
//					List<Object> it = new ArrayList<>();
//					it.add(item);
//					return it.addAll(list2.stream().filter(x -> x.getBreedPid() == item.getBreedId()).collect(Collectors.toList()));
//				} else {
//					return item;
//				}
//			}).collect(Collectors.toList());
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
			System.out.println(list2.size());
			
//			database.select("SELECT * FROM pet_breed WHERE breed_pid is null")
//			.getTupleN(PetBreed.class).blockingIterable().forEach(
//				e -> {
//					PetBreed pet = new PetBreed();
//					List<?> values = e.values();
//					pet.setBreedId((Integer) values.get(0));
//					pet.setBreedPid((Integer) values.get(1));
//					pet.setBreedName((String) values.get(2));
//					pet.setBreedEtc((String) values.get(3));
//					list.add(pet);
//				}
//			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (result);
	}	
	
	public void setDatabase(Database database) {
		this.database = database;
	}

}
