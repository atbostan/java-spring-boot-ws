package com.bossware.app.business.utils.mapper.user;

import org.mapstruct.Mapper;

import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.entities.Address;

@Mapper(componentModel = "spring")
public interface AddressSourceDestinationMapper {
	public abstract Address dtoToEntity(AddressDto dto);

	public abstract AddressDto entityToDto(Address entity);

}
