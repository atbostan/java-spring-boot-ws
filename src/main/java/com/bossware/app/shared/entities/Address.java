package com.bossware.app.shared.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="addresses")
public class Address extends BaseEntity{

	private static final long serialVersionUID = -3392580769164257489L;


	
	@Column(length=15,nullable = false)
	private String city;
	
	@Column(length=15,nullable = false)
	private String country;
	
	@Column(length=100,nullable = false)
	private String streetName;
	
	@Column(length=7,nullable = false)
	private String postalCode;
	
	@Column(length=10,nullable = false)
	private String type;
	
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;
	

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(city, country, postalCode, streetName, type, user);
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
		Address other = (Address) obj;
		return Objects.equals(city, other.city) && Objects.equals(country, other.country)
				&& Objects.equals(postalCode, other.postalCode) && Objects.equals(streetName, other.streetName)
				&& Objects.equals(type, other.type) && Objects.equals(user, other.user);
	}
	@Override
	public String toString() {
		return "Address [city=" + city + ", country=" + country + ", streetName=" + streetName + ", postalCode="
				+ postalCode + ", type=" + type + ", user=" + user + ", id=" + id + ", creationTime=" + creationTime
				+ ", creatorId=" + creatorId + ", deletionTime=" + deletionTime + ", deletorUserId=" + deletorUserId
				+ ", modificationTime=" + modificationTime + ", modifierUserId=" + modifierUserId + "]";
	}

	
}
