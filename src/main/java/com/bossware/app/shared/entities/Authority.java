package com.bossware.app.shared.entities;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="authorities")
public class Authority extends BaseEntity {

	private static final long serialVersionUID = 5074732866470580676L;

	
	private String authName;
	
    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name="roles_id")
	private Role role;
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public String getAuthName() {
		return authName;
	}
	public void setAuthName(String authName) {
		this.authName = authName;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(authName, role);
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
		Authority other = (Authority) obj;
		return Objects.equals(authName, other.authName) && Objects.equals(role, other.role);
	}
	@Override
	public String toString() {
		return "Authority [authName=" + authName + ", role=" + role + ", id=" + id + ", creationTime=" + creationTime
				+ ", creatorId=" + creatorId + ", deletionTime=" + deletionTime + ", deletorUserId=" + deletorUserId
				+ ", modificationTime=" + modificationTime + ", modifierUserId=" + modifierUserId + "]";
	}

}
