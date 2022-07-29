package com.bossware.app.business.utils.mapper.user;

import org.mapstruct.Mapper;

import com.bossware.app.shared.dto.AuthorityDto;
import com.bossware.app.shared.entities.Authority;

@Mapper(componentModel = "spring")
public interface AuthoritySourceDestinationMapper {
	public abstract Authority dtoToEntity(AuthorityDto dto);

	public abstract AuthorityDto entityToDto(Authority entity);
}
