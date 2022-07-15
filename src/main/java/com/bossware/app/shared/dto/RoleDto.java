package com.bossware.app.shared.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;

public class RoleDto {
	private long id;
	private String roleId;
	private String roleName;
	
    @JsonBackReference
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
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
}
