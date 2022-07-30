package com.bossware.app.business.utils.mapper.user;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.entities.Address;

@Mapper(componentModel = "spring")
public interface AddressSourceDestinationMapper {
    @Mapping(target="user.id", source="userId")
	public abstract Address dtoToEntity(AddressDto dto);

    @Mapping(target="userId", source="entity.user.id")
	public abstract AddressDto entityToDto(Address entity);

}
