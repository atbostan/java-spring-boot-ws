package com.bossware.app.business.utils.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bossware.app.shared.dto.AuthorityDto;
import com.bossware.app.shared.entities.Authority;

@Mapper(componentModel = "spring")
public interface AuthoritySourceDestinationMapper {
	
    @Mapping(target="role.id", source="roleId")
	public abstract Authority dtoToEntity(AuthorityDto dto);

    @Mapping(target="roleId", source="role.id")
	public abstract AuthorityDto entityToDto(Authority entity);
}
