package com.bossware.app.business.implementations;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
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
import com.bossware.app.business.utils.mapper.user.AddressSourceDestinationMapper;
import com.bossware.app.business.utils.mapper.user.AuthoritySourceDestinationMapper;
import com.bossware.app.persistance.repositories.AddressRepository;
import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.entities.Address;
import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.messages.ErrorMessages;
import com.bossware.app.shared.models.exceptions.ServiceExceptionBase;
import com.bossware.app.shared.models.response.ResponseBaseModel;
import com.bossware.app.shared.utils.EntityStrIdGenerator;

@Service
public class AddressServiceImpl implements AddressService {
	

	private AddressSourceDestinationMapper mapper
    = Mappers.getMapper(AddressSourceDestinationMapper.class);
	
	
    @Autowired
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;

    //C - U - D
	@Override
	public ResponseBaseModel<AddressDto> create(AddressDto t) {
		checkIfUserExist(t.getUserId());
		Address mappedAddress = mapper.dtoToEntity(t);
		Address createdAdress = addressRepository.save(mappedAddress);
		AddressDto returnedValue = mapper.entityToDto(createdAdress);
		return new ResponseBaseModel<AddressDto>(returnedValue, HttpStatus.OK);
	}

    
	@Override
	public ResponseBaseModel<AddressDto> update(long id, AddressDto t) {
		Optional<Address> address = addressRepository.findById(id);
		if(address==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		Address addressToUpdate = mapper.dtoToEntity(t);
		addressToUpdate.setId(address.get().getId());
		Address updatedEntity = addressRepository.save(addressToUpdate);
		AddressDto returnedValue = mapper.entityToDto(updatedEntity);
		return new ResponseBaseModel<AddressDto>(returnedValue,HttpStatus.OK);
	}

	@Override
	public void delete(long id) {
		Optional<Address> address = addressRepository.findById(id);
		if (address == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		addressRepository.delete(address.get());
	}

    // R
	@Override
	public ResponseBaseModel<AddressDto> getEntityById(long id) {
		Optional<Address> address = addressRepository.findById(id);
		if (address == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		AddressDto returnedValue = mapper.entityToDto(address.get());
		return new ResponseBaseModel<AddressDto>(returnedValue,HttpStatus.OK);
	}


	@Override
	public ResponseBaseModel<List<AddressDto>> getAll(int page, int limit) {
		Pageable pageReq =  PageRequest.of(page,limit);
		Page<Address> addressList = addressRepository.findAll(pageReq);
		List<AddressDto> returnedValue = addressList.stream().map(e->mapper.entityToDto(e)).collect(Collectors.toList());
		return new ResponseBaseModel<List<AddressDto>>(returnedValue, HttpStatus.OK);

	}
	
	@Override
	public ResponseBaseModel<List<AddressDto>> getAddressByUserId(long id) {
		Optional<User> user = userRepository.findById(id);
		if(user==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		List<Address> addressList = addressRepository.findAllByUser(user.get());
		List<AddressDto> returnedValue = addressList.stream().map(e -> mapper.entityToDto(e))
				.collect(Collectors.toList());

		return new ResponseBaseModel<List<AddressDto>>(returnedValue, HttpStatus.OK);

	}

	// Extra Business Logic
	private void checkIfUserExist (long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
	}

}
