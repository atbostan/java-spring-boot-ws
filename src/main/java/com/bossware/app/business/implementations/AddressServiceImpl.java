package com.bossware.app.business.implementations;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.bossware.app.business.services.AddressService;
import com.bossware.app.core.utils.EntityStrIdGenerator;
import com.bossware.app.persistance.repositories.AddressRepository;
import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.entities.Address;
import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.messages.ErrorMessages;
import com.bossware.app.shared.models.exceptions.ServiceExceptionBase;
import com.bossware.app.shared.models.response.ResponseBaseModel;

@Service
public class AddressServiceImpl implements AddressService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository repository;
	
	@Autowired
	ModelMapper mapper;
	
	@Autowired
	EntityStrIdGenerator addressIdGenarator;

	@Override
	public ResponseBaseModel<AddressDto> create(AddressDto t) {
		Address address = mapper.map(t, Address.class);
		address.setAddressId(addressIdGenarator.generateId(20));
		Address createdAdress = repository.save(address);
		AddressDto returnedEntity = mapper.map(createdAdress, AddressDto.class);
		return new ResponseBaseModel<AddressDto>(returnedEntity, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<AddressDto> getEntityById(String id) {
		Address entity = repository.findByAddressId(id);
		AddressDto returnedEntity = mapper.map(entity, AddressDto.class);
		return new ResponseBaseModel<AddressDto>(returnedEntity,HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<AddressDto> update(String id, AddressDto t) {
		Address entity = repository.findByAddressId(id);
		if(entity==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		mapper.map(t, entity);
		Address updatedEntity = repository.save(entity);
		AddressDto returnedEntity = mapper.map(updatedEntity, AddressDto.class);
		return new ResponseBaseModel<AddressDto>(returnedEntity,HttpStatus.OK);
	}

	@Override
	public void delete(String id) {
		Address entity = repository.findByAddressId(id);
		repository.delete(entity);
		
	}

	@Override
	public ResponseBaseModel<List<AddressDto>> getAll(int page, int limit) {
		Pageable pageReq =  PageRequest.of(page,limit);
		Page<Address> addressList = repository.findAll(pageReq);
		List<AddressDto> returnedValue = addressList.stream().map(e->mapper.map(e, AddressDto.class)).collect(Collectors.toList());
		return new ResponseBaseModel<List<AddressDto>>(returnedValue, HttpStatus.OK);

	}
	
	@Override
	public ResponseBaseModel<List<AddressDto>> getAddressByUserId(String id) {
		User user = userRepository.findByUserId(id);
		if(user==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());

		List<Address> addressList = repository.findAllByUser(user);
		List<AddressDto> returnedValue = new ArrayList<AddressDto>();
		if(!CollectionUtils.isEmpty(addressList)) {
			Type listType = new TypeToken<List<AddressDto>>() {}.getType();
			 returnedValue = mapper.map(addressList, listType);
		}
		//List<AddressDto> returnedValue = addressList.stream().map(e->mapper.map(e, AddressDto.class)).collect(Collectors.toList());
		return new ResponseBaseModel<List<AddressDto>>(returnedValue, HttpStatus.OK);

	}

	@Override
	public ResponseBaseModel<AddressDto> getAddressBelongsToUsersByAddressId(String userId, String addressId) {
		List<AddressDto> addresses = getAddressByUserId(userId).getData();
		AddressDto selectedAddress = addresses.stream().filter(x->x.getAddressId().equals(addressId)).findAny().get();	
		return new ResponseBaseModel<AddressDto>(selectedAddress, HttpStatus.OK);
	}

}
