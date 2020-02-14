package kr.co.wishDream;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.davidmoten.rx.jdbc.Database;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import kr.co.wishDream.domain.Member;
import kr.co.wishDream.domain.Menu;
import reactor.core.publisher.Flux;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {TestDatabaseConnect.class})
@TestPropertySource(locations = {"classpath:application.yml"})
public class WishDreamCoreApplicationTests {
	
	@Autowired
	private TestDatabaseConnect testDatabaseConnect;
	
	private Database database;

	@Test
	public void contextLoads() throws Exception {
//		findByEmail("nana@gmail.com");
		List<? extends GrantedAuthority> s = findByRoleFromAuth("ADMIN");
		
		List<Integer> b = findByAuthMenuType(s);
		findByAuthMenu(b);
	}

	public void findByEmail(String email) throws Exception {
		this.setDatabase(Database.from(testDatabaseConnect.pool()));
		
		String sql = "SELECT * FROM member WHERE EMAIL = ?";
		
		database.select(sql)
				.parameter(email)
				.get(
						rs -> {
							Member member = new Member();
							member.setUsername(rs.getString("username"));
							member.setEmail(rs.getString("email"));
							member.setBirth(rs.getDate("birth"));
							member.setJoinDate(rs.getDate("joinDate"));
							member.setLeaveDate(rs.getDate("leaveDate"));
							member.setEtc(rs.getString("etc"));
							
							return member;
						})
				.blockingSubscribe(System.out::println);
		
	}
	
	public List<? extends GrantedAuthority> findByRoleFromAuth(String role) throws Exception {
		this.setDatabase(Database.from(testDatabaseConnect.pool()));
		List<SimpleGrantedAuthority> authority = new ArrayList<SimpleGrantedAuthority>();
		database.select("SELECT id, role, authority FROM AUTH WHERE ROLE = ?")
			.parameter(role)
			.getTupleN()
			.blockingForEach(auth -> {
			if (auth.values().get(2) instanceof String) {
				authority.add(new SimpleGrantedAuthority((String) auth.values().get(2)));
			}
			});
		this.setDatabase(null);
		return authority;
	}
	
	public List<Integer> findByAuthMenuType(List<? extends GrantedAuthority> auths) throws Exception {
		this.setDatabase(Database.from(testDatabaseConnect.pool()));
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
		this.setDatabase(Database.from(testDatabaseConnect.pool()));
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

	public void setDatabase(Database database) {
		this.database = database;
	}
}
