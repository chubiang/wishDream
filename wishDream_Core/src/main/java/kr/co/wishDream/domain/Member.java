package kr.co.wishDream.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
public class Member implements UserDetails, CredentialsContainer {
	
	private static final long serialVersionUID = 1L;

	@Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");

	private String email;
	private String username;
	private String password;
	private String etc;
	private Date birth;
	private Date joinDate;
	private Date leaveDate;
	
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	
	@Synchronized
	public String syncDateFormate(Date date) {
		return dateFormat.format(date);
	}

	public Member(String email, String username, String password, String etc, Date birth, Date joinDate, Date leaveDate) {
		super();
		this.email = email;
		this.username = username;
		this.password = password;
		this.etc = etc;
		this.birth = birth;
		this.joinDate = joinDate;
		this.leaveDate = leaveDate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		return enabled;
	}

	@Override
	public void eraseCredentials() {
		password = "[PROTECTED]";
	}

	@Override
	public String toString() {
		return "Member [email=" + email + ", username=" + username + ", password=[PROTECTED]" + ", etc=" + etc
				+ ", birth=" + birth + ", joinDate=" + joinDate + ", leaveDate=" + leaveDate + ", authorities="
				+ authorities + "]";
	}

}
