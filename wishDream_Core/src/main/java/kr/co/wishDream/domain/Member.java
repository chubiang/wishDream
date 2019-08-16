package kr.co.wishDream.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

	private String email;
	private String username;
	private String etc;
	private Date birth;
	private Date joinDate;
	private Date leaveDate;
	
	
	@Synchronized
	public String syncDateFormate(Date date) {
		return dateFormat.format(date);
	}

	public Member(String email, String username, String etc, Date birth, Date joinDate, Date leaveDate) {
		super();
		this.email = email;
		this.username = username;
		this.etc = etc;
		this.birth = birth;
		this.joinDate = joinDate;
		this.leaveDate = leaveDate;
	}

	@Override
	public String toString() {
		return "Member [username=" + username + ", email=" + email + ", etc=" + etc + ", birth=" + birth + ", joinDate="
				+ joinDate + ", leaveDate=" + leaveDate + "]";
	}

}
