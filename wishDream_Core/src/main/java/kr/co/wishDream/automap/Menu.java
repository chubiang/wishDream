package kr.co.wishDream.automap;

import org.davidmoten.rx.jdbc.annotations.Column;
import org.davidmoten.rx.jdbc.annotations.Query;

@Query("SELECT ID, PID, NAME, MENU_TYPE FROM MENU")
public interface Menu {

	@Column
	Integer id();
	
	@Column
	Integer pid();
	
	@Column
	String name();
	
	@Column
	Integer menuType();
}
