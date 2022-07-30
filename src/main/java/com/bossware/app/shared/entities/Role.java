package com.bossware.app.shared.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity(name="roles")
public class Role extends BaseEntity {

	private static final long serialVersionUID = -3680139390894236847L;


	
	@Column(length=30,nullable = false)
	private String roleName;
	
    @OnDelete(action = OnDeleteAction.CASCADE)
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;


	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
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
		result = prime * result + Objects.hash(roleName, user);
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
		Role other = (Role) obj;
		return Objects.equals(roleName, other.roleName) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Role [roleName=" + roleName + ", user=" + user + ", id=" + id + ", creationTime=" + creationTime
				+ ", creatorId=" + creatorId + ", deletionTime=" + deletionTime + ", deletorUserId=" + deletorUserId
				+ ", modificationTime=" + modificationTime + ", modifierUserId=" + modifierUserId + "]";
	}
	

}
