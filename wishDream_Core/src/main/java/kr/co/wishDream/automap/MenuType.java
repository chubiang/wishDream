package kr.co.wishDream.automap;

import org.davidmoten.rx.jdbc.annotations.Column;
import org.davidmoten.rx.jdbc.annotations.Query;

@Query("SELECT id, menu_type, name, authority FROM MENU_TYPE")
public interface MenuType {
	@Column
	Integer id();
	
	@Column
	Integer menuType();
	
	@Column
	String name();
	
	@Column
	String authority();
}
