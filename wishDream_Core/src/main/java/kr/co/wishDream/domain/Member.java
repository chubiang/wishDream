package kr.co.wishDream.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.CredentialsContainer;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.Synchronized;

public class Member implements UserDetails, CredentialsContainer {

	private static final long serialVersionUID = 1L;

	@Getter(value = AccessLevel.NONE) @Setter(value = AccessLevel.NONE)
	private DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");

	private String email;
	private String username;
	private String password;
	private String etc;
	private Date birth;
	private Date joinDate;
	private Date leaveDate;
	private String role;
	private String authProvider;
	
	private boolean accountNonExpired = true;
	private boolean accountNonLocked = true;
	private boolean credentialsNonExpired = true;
	private boolean enabled = true;
	
	private Collection<? extends GrantedAuthority> authorities;
	
	private Pet pet;

	
	@Synchronized
	public String syncDateFormate(Date date) {
		return dateFormat.format(date);
	}

	public Member(String email, String password, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = email;
		this.password = password;
		this.authorities = authorities;
	}

	public Member(DateFormat dateFormat, String email, String username, String password, String etc, Date birth,
			Date joinDate, Date leaveDate, String role) {
		super();
		this.dateFormat = dateFormat;
		this.email = email;
		this.username = username;
		this.password = password;
		this.etc = etc;
		this.birth = birth;
		this.joinDate = joinDate;
		this.leaveDate = leaveDate;
		this.role = role;
	}

	public Member(String email, String username, String etc, Date birth, Date joinDate, Date leaveDate,
			boolean accountNonExpired, boolean accountNonLocked, boolean credentialsNonExpired, boolean enabled,
			Collection<? extends GrantedAuthority> authorities) {
		super();
		this.email = email;
		this.username = username;
		this.etc = etc;
		this.birth = birth;
		this.joinDate = joinDate;
		this.leaveDate = leaveDate;
		this.accountNonExpired = accountNonExpired;
		this.accountNonLocked = accountNonLocked;
		this.credentialsNonExpired = credentialsNonExpired;
		this.enabled = enabled;
		this.authorities = authorities;
	}

	public Member() {
	}

	public DateFormat getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(DateFormat dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEtc() {
		return etc;
	}

	public void setEtc(String etc) {
		this.etc = etc;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public Date getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Date leaveDate) {
		this.leaveDate = leaveDate;
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setAccountNonExpired(boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	@Override
	public String getUsername() {
		return username;
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

	public String getAuthProvider() {
		return authProvider;
	}

	public void setAuthProvider(String authProvider) {
		this.authProvider = authProvider;
	}

	@Override
	public String toString() {
		return "Member [email=" + email + ", username=" + username + ", password=[PROTECTED]" + ", etc=" + etc
				+ ", birth=" + birth + ", joinDate=" + joinDate + ", leaveDate=" + leaveDate + ", authorities="
				+ authorities + "]";
	}
	
	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public class Pet {
		
		private Integer petId;
		private String petName;
		private Integer petAge;
		private Integer subBreedId;
		private String petGender;
		private Date petBirth;
		
		public Pet() {}

		public Pet(Integer petId, String petName, Integer petAge, Integer subBreedId, String petGender, Date petBirth) {
			super();
			this.petId = petId;
			this.petName = petName;
			this.petAge = petAge;
			this.subBreedId = subBreedId;
			this.petGender = petGender;
			this.petBirth = petBirth;
		}

		public Integer getPetId() {
			return petId;
		}
		public String getPetName() {
			return petName;
		}
		public Integer getPetAge() {
			return petAge;
		}
		public Integer getSubBreedId() {
			return subBreedId;
		}
		public String getPetGender() {
			return petGender;
		}
		public Date getPetBirth() {
			return petBirth;
		}
		public void setPetId(Integer petId) {
			this.petId = petId;
		}
		public void setPetName(String petName) {
			this.petName = petName;
		}
		public void setPetAge(Integer petAge) {
			this.petAge = petAge;
		}
		public void setSubBreedId(Integer subBreedId) {
			this.subBreedId = subBreedId;
		}
		public void setPetGender(String petGender) {
			this.petGender = petGender;
		}		public void setPetBirth(Date petBirth) {
			this.petBirth = petBirth;
		}
	}
	



	
}
