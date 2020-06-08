package kr.co.wishDream.repository;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

import org.davidmoten.rx.jdbc.Database;
import org.davidmoten.rx.jdbc.Tx;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import com.sun.mail.iap.ConnectionException;

import io.reactivex.Flowable;
import io.reactivex.plugins.RxJavaPlugins;
import kr.co.wishDream.connect.DatabaseConnect;
import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.Menu;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Repository
public class MemberRepository {
	
	@Autowired
	private DatabaseConnect databaseConnect;

	private Database database;
	
	private Logger LOG = LoggerFactory.getLogger(MemberRepository.class);
	
	
	public List<SimpleGrantedAuthority> findByRoleFromAuth(String role) throws Exception {
		this.setDatabase(Database.from(databaseConnect.pool()));
		List<SimpleGrantedAuthority> authority = new ArrayList<>();
		database.select("SELECT * FROM auth WHERE role = ?")
			.parameter(role)
			.getTupleN()
			.blockingForEach(auth -> {
				if (auth.values().get(2) instanceof String) {
					authority.add(new SimpleGrantedAuthority((String) auth.values().get(2)));
				}
			});
		return authority;
	}
	
	public List<Integer> findByAuthMenuType(List<? extends GrantedAuthority> auths) throws Exception {
		this.setDatabase(Database.from(databaseConnect.pool()));
		List<Integer> menuTypes = new ArrayList<Integer>();
		List<String> arr = new ArrayList<>();
		auths.forEach(auth -> arr.add(auth.getAuthority()));
		database.select("SELECT menu_type FROM menu_type WHERE authority in (?)")
			.parameters(arr)
			.getAs(Integer.class)
			.blockingForEach(menuType -> {
				menuTypes.add(menuType);	
			});
		return menuTypes;
	}
	
	public Flux<List<Menu>> findByAuthMenu(List<Integer> menuTypes) throws Exception {
		this.setDatabase(Database.from(databaseConnect.pool()));
		ArrayList<Menu> menus = new ArrayList<Menu>();
		List<Menu> sortedMenus = new ArrayList<Menu>();
		if (!menuTypes.isEmpty()) {
			String sql = "WITH RECURSIVE menu_tree(id, pid, name, menu_type) AS " + 
					"(SELECT id, pid, name, menu_type " + 
					"FROM public.menu " + 
					"WHERE pid is null " + 
					"UNION ALL " + 
					"SELECT m.id, m.pid, m.name, m.menu_type " + 
					"FROM menu m, menu_tree tree " + 
					"WHERE m.pid = tree.id) " + 
					"SELECT id, pid, name, menu_type " + 
					"FROM menu_tree WHERE menu_type in (?) ORDER BY id";
			database.select(sql)
				.parameters(menuTypes)
				.getTupleN().blockingIterable().forEach(onNext -> {
					Menu m = Menu.menus(onNext.values());
					menus.add(m);
				});
			
			Comparator<Menu> compareId = (e1,e2) -> Integer.compare(e1.getId(), e2.getId()); 
			sortedMenus = menus.stream().sorted(compareId).collect(Collectors.toList());
			Menu.sortHierarchyMenus(sortedMenus);
		}
		return Flux.just(sortedMenus);
	}
	
	public Mono<UserDetails> findByEmailToUserDetails(String email) {
		Flowable<UserDetails> memberFlowable = null;
		try {
			this.setDatabase(Database.from(databaseConnect.pool()));
			
			String sql = "SELECT * FROM member WHERE email = ?";
	
			memberFlowable =
					database.select(sql)
						.parameter(email)
						.get(
							rs -> {
								Member member = new Member();
								member.setUsername(rs.getString("username"));
								member.setPassword(rs.getString("password"));
								member.setEmail(rs.getString("email"));
								member.setBirth(rs.getDate("birth"));
								member.setJoinDate(rs.getDate("join_date"));
								member.setLeaveDate(rs.getDate("leave_date"));
								member.setEtc(rs.getString("etc"));
								member.setRole(rs.getString("role"));
								try {
									member.setAuthorities(findByRoleFromAuth(rs.getString("role")));
								} catch (Exception e) {
									e.printStackTrace();
								}
								
								return (UserDetails) member;
						});
		} catch (Exception e) {
			e.getStackTrace();
		}
		return Mono.from(memberFlowable);
	}
	
	
	
	public Mono<Member> findByEmail(String email) {
		Flowable<Member> memberFlowable = null;
		try {
			this.setDatabase(Database.from(databaseConnect.pool()));
			
			String sql = "SELECT * FROM member WHERE email = ?";
	
			memberFlowable =
					database.select(sql)
						.parameter(email)
						.get(
							rs -> {
								Member member = new Member();
								member.setUsername(rs.getString("username"));
								member.setEmail(rs.getString("email"));
								member.setBirth(rs.getDate("birth"));
								member.setJoinDate(rs.getDate("join_date"));
								member.setLeaveDate(rs.getDate("leave_date"));
								member.setEtc(rs.getString("etc"));
								
								return member;
						});
		} catch (Exception e) {
			e.getStackTrace();
		}
		return Mono.from(memberFlowable);
	}
	
	public Mono<Object> signUp(Mono<MultiValueMap<String, String>> fm) throws Exception{
		return fm.flatMap(formdata -> {
			if (formdata.entrySet().size() == 0) {
				return Mono.error(new NullPointerException("No Receive Form Data"));
			}
			try {
				if (database == null) {
					this.setDatabase(Database.from(databaseConnect.pool()));
				}
				LOG.info("formData = "+ formdata);
				
				String password = new BCryptPasswordEncoder(11).encode(formdata.get("password").get(0));
				String sql = "insert into member(email, password, username, join_date, role) values "
						+ "(?,?,?,current_date,'USER')";
				Flowable<Tx<?>> flowable = database.update(sql).parameters(
						formdata.get("email").get(0), 
						password, 
						formdata.get("username").get(0))
				.transaction()
				.doOnNext(
					onNext -> {
						if (formdata.get("petName") != null && !formdata.get("petName").get(0).isEmpty()) {
							savePetInfo(formdata);
						}
					}
				)
				.doOnComplete(()->{  
					LOG.info("SignUp Member = "+formdata.get("email").get(0));
				});
				flowable.count().subscribe();
				
				return Mono.from(flowable.publish());
			} catch (Exception e) {
				return Mono.error(new ConnectionException("Failed db connection"));
			}
		});
	}
	
	public void savePetInfo(MultiValueMap<String, String> data) throws Exception{
		String sql = "insert into pet(pet_id, email, pet_name, pet_age, sub_breed_id, pet_gender, pet_birth) values "
				+ "(default,?,?,?,?,?,?)";
		String[] date = data.get("petBirth").get(0).split("-");
		Calendar cal = Calendar.getInstance();
		cal.set(new Integer(date[0]), new Integer(date[1]), new Integer(date[2]));
		database.update(sql).parameters(
			 data.get("email").get(0),
			 data.get("petName").get(0),
			 new Integer(data.get("petAge").get(0)), 
			 new Integer(data.get("petBreedId").get(0)),
			 data.get("petGender").get(0), 
			 cal.getTime())
		.transaction()
		.doOnNext(onNext -> {
			LOG.info("Saved PetInfo = "+data.get("email").get(0));
		})
		.doOnError(onError -> {
			LOG.error("onError Pet = "+onError);
		}).count().subscribe();
	}
	


	public void setDatabase(Database database) {
		this.database = database;
	}
	
}
