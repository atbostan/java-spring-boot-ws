package com.bossware.app.shared.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDto {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long id;
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private String roleId;
	private String roleName;
	private long userId;	

    @JsonBackReference
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private UserDto user;

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
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
	
	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}
	
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}
