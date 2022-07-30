package com.bossware.app.shared.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RoleDto {
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long id;

	private String roleName;
	private long userId;	



	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	

}
