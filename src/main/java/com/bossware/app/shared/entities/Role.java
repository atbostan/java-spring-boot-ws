package com.bossware.app.shared.entities;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="roles")
public class Role extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3680139390894236847L;

	@Column(length=30,nullable = false)
	private String roleId;
	
	@Column(length=30,nullable = false)
	private String roleName;

	
	@ManyToOne
	@JoinColumn(name="users_id")
	private User user;

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

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
		result = prime * result + Objects.hash(roleId, roleName, user);
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
		return Objects.equals(roleId, other.roleId) && Objects.equals(roleName, other.roleName)
				&& Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", roleName=" + roleName + ", user=" + user + ", id=" + id + ", creationTime="
				+ creationTime + ", creatorId=" + creatorId + ", deletionTime=" + deletionTime + ", deletorUserId="
				+ deletorUserId + ", modificationTime=" + modificationTime + ", modifierUserId=" + modifierUserId + "]";
	}
	

}
