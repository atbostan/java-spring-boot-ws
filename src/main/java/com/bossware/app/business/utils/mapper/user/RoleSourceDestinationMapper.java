package com.bossware.app.business.utils.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.entities.Role;

@Mapper(componentModel = "spring")
public interface RoleSourceDestinationMapper {

    @Mapping(target="user.id", source="userId")
	public abstract Role dtoToEntity(RoleDto dto);

    @Mapping(target="userId", source="entity.user.id")
	public abstract RoleDto entityToDto(Role entity);
}
