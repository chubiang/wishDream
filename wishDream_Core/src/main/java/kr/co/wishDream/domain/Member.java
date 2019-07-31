package kr.co.wishDream.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Synchronized;

@NoArgsConstructor
@Data(staticConstructor = "of")
public class Member {
	
	@Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

	private String username;
	private String email;
	private String etc;
	private Date birth;
	private Date joinDate;
	private Date leaveDate;
	
	
	@Synchronized
	public String syncDateFormate(Date date) {
		return dateFormat.format(date);
	}

	public Member(String username, String email, String etc, Date birth, Date joinDate, Date leaveDate) {
		super();
		this.username = username;
		this.email = email;
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
