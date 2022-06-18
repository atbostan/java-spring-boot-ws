package com.bossware.app.shared.entities;



import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name="users")
public class User extends BaseEntity{

	
	
	@Column(nullable=false)
	private String userId;
	
	@Column(nullable=false,length = 50)
	private String firstName;
	
	@Column(nullable=false,length = 50)
	private String lastName;
	
	@Column(nullable=false,length = 120,unique = true)
	private String email;
	

	@Column(nullable=false)
	private String encryptedPassword;
	private String emailVerificationToken;
	
	@Column(nullable=false)
	private Boolean emailVerificationStatus=false;


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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}



	public String getEncryptedPassword() {
		return encryptedPassword;
	}

	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, emailVerificationStatus, emailVerificationToken, encryptedPassword, firstName,
				lastName, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(email, other.email)
				&& Objects.equals(emailVerificationStatus, other.emailVerificationStatus)
				&& Objects.equals(emailVerificationToken, other.emailVerificationToken)
				&& Objects.equals(encryptedPassword, other.encryptedPassword)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(userId, other.userId);
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", encryptedPassword=" + encryptedPassword + ", emailVerificationToken=" + emailVerificationToken
				+ ", emailVerificationStatus=" + emailVerificationStatus + ", id=" + id + ", creationTime="
				+ creationTime + ", creatorId=" + creatorId + ", deletionTime=" + deletionTime + ", deletorUserId="
				+ deletorUserId + ", modificationTime=" + modificationTime + ", modifierUserId=" + modifierUserId + "]";
	}
	
	
	

}
