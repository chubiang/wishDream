package kr.co.wishDream.automap;

import org.davidmoten.rx.jdbc.annotations.Column;
import org.davidmoten.rx.jdbc.annotations.Index;
import org.davidmoten.rx.jdbc.annotations.Query;

@Query("SELECT ID, AUTHORITY FROM AUTH ORDER BY ID")
public interface Auth {
	
	@Column
	@Index(1)
	Integer id();
	
	@Column
	@Index(2)
	String role();
	
	@Column
	@Index(3)
	String authority();
	
}
