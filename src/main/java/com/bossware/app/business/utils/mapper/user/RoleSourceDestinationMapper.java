package com.bossware.app.business.utils.mapper.user;

import org.mapstruct.Mapper;

import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleSourceDestinationMapper {

	public abstract Role dtoToEntity(RoleDto dto);

	public abstract RoleDto entityToDto(Role entity);
}
