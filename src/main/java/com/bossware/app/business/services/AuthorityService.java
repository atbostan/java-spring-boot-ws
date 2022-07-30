package com.bossware.app.business.services;

import java.util.List;

import com.bossware.app.business.services.abstracts.ServiceGenericBase;
import com.bossware.app.shared.dto.AuthorityDto;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.models.response.ResponseBaseModel;

public interface AuthorityService extends ServiceGenericBase<AuthorityDto> {
	ResponseBaseModel<List<AuthorityDto>> getAuthoritiesByRoleId(long id);


}
