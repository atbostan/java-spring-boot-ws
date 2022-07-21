package com.bossware.app.business.services;

import java.util.List;

import com.bossware.app.business.services.abstracts.ServiceGenericBase;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.models.response.ResponseBaseModel;

public interface RoleService extends ServiceGenericBase<RoleDto>  {
	ResponseBaseModel<List<RoleDto>> getRolesByUserId(String id);
	ResponseBaseModel<RoleDto> findRoleById(String roleId);

 
}
