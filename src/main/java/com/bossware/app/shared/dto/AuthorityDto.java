package com.bossware.app.shared.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthorityDto {

	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private long id;
	private String authName;
	
	private long roleId;
	
    @JsonBackReference
	@JsonProperty(access = JsonProperty.Access.READ_ONLY)
	private RoleDto role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getRoleId() {
		return roleId;
	}

	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public RoleDto getRole() {
		return role;
	}

	public void setRole(RoleDto role) {
		this.role = role;
	}
    
    
}
