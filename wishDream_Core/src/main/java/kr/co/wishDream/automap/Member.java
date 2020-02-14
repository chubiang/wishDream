package kr.co.wishDream.automap;

import java.util.Date;

import org.davidmoten.rx.jdbc.annotations.Column;

public interface Member {
	
	@Column("email")
	String email();
	
	@Column("username")
	String username();
	
	@Column("etc")
	String etc();
	
	@Column("birth")
	Date birth();
	
	@Column("joinDate")
	Date joinDate();
	
	@Column("leaveDate")
	Date leaveDate();
	
	@Column("role")
	String role();
	
}
