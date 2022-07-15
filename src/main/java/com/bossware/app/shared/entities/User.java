package com.bossware.app.shared.entities;



import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity(name="users")
public class User extends BaseEntity{

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -2178377859388012685L;

	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false,length = 50)
	private String firstName;
	
	@Column(nullable=false,length = 50)
	private String lastName;
	
	@Column(nullable=false,length = 50)
	private String userName;
	
	@Column(nullable=false,length = 120,unique = true)
	private String email;
	
	@Column(nullable=false)
	private String password;
	
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus=false;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Role> roles;

	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<Address> adresses;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailVerificationToken() {
		return emailVerificationToken;
	}

	public void setEmailVerificationToken(String emailVerificationToken) {
		this.emailVerificationToken = emailVerificationToken;
	}

	public Boolean getEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public List<Address> getAdresses() {
		return adresses;
	}

	public void setAdresses(List<Address> adresses) {
		this.adresses = adresses;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(adresses, email, emailVerificationStatus, emailVerificationToken,
				password, firstName, lastName, roles, userId, userName);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(adresses, other.adresses) && Objects.equals(email, other.email)
				&& Objects.equals(emailVerificationStatus, other.emailVerificationStatus)
				&& Objects.equals(emailVerificationToken, other.emailVerificationToken)
				&& Objects.equals(password, other.password)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(roles, other.roles) && Objects.equals(userId, other.userId)
				&& Objects.equals(userName, other.userName);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", userName="
				+ userName + ", email=" + email + ", encryptedPassword=" + password
				+ ", emailVerificationToken=" + emailVerificationToken + ", emailVerificationStatus="
				+ emailVerificationStatus + ", roles=" + roles + ", adresses=" + adresses + ", id=" + id
				+ ", creationTime=" + creationTime + ", creatorId=" + creatorId + ", deletionTime=" + deletionTime
				+ ", deletorUserId=" + deletorUserId + ", modificationTime=" + modificationTime + ", modifierUserId="
				+ modifierUserId + "]";
	}
	
	
	

}
