package com.bossware.app.business.services;

import java.util.List;

import com.bossware.app.business.services.abstracts.ServiceGenericBase;
import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.models.response.ResponseBaseModel;

public interface AddressService extends ServiceGenericBase<AddressDto>  {
	ResponseBaseModel<List<AddressDto>> getAddressByUserId(String id);
	
	ResponseBaseModel<AddressDto> getAddressBelongsToUsersByAddressId(String userId,String addressId);

}
