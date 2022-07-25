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
	private AddressRepository addressRepository;

	@Autowired
	private UserRepository userRepository;
		
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private EntityStrIdGenerator addressIdGenarator;

    //C - U - D
	@Override
	public ResponseBaseModel<AddressDto> create(AddressDto t) {
		checkIfUserExist(t.getUserId());
		Address mappedAddress = mapper.map(t, Address.class);
		mappedAddress.setAddressId(addressIdGenarator.generateId(20));
		Address createdAdress = addressRepository.save(mappedAddress);
		AddressDto returnedValue = mapper.map(createdAdress, AddressDto.class);
		return new ResponseBaseModel<AddressDto>(returnedValue, HttpStatus.OK);
	}

    
	@Override
	public ResponseBaseModel<AddressDto> update(String id, AddressDto t) {
		Address address = addressRepository.findByAddressId(id);
		if(address==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		Address addressToUpdate = mapper.map(t, Address.class);
		addressToUpdate.setAddressId(address.getAddressId());
		addressToUpdate.setId(address.getId());
		Address updatedEntity = addressRepository.save(addressToUpdate);
		AddressDto returnedValue = mapper.map(updatedEntity, AddressDto.class);
		return new ResponseBaseModel<AddressDto>(returnedValue,HttpStatus.OK);
	}

	@Override
	public void delete(String id) {
		Address address = addressRepository.findByAddressId(id);
		if (address == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		addressRepository.delete(address);
	}

    // R
	@Override
	public ResponseBaseModel<AddressDto> getEntityById(String id) {
		Address address = addressRepository.findByAddressId(id);
		if (address == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		AddressDto returnedValue = mapper.map(address, AddressDto.class);
		return new ResponseBaseModel<AddressDto>(returnedValue,HttpStatus.OK);
	}


	@Override
	public ResponseBaseModel<List<AddressDto>> getAll(int page, int limit) {
		Pageable pageReq =  PageRequest.of(page,limit);
		Page<Address> addressList = addressRepository.findAll(pageReq);
		List<AddressDto> returnedValue = addressList.stream().map(e->mapper.map(e, AddressDto.class)).collect(Collectors.toList());
		return new ResponseBaseModel<List<AddressDto>>(returnedValue, HttpStatus.OK);

	}
	
	@Override
	public ResponseBaseModel<List<AddressDto>> getAddressByUserId(String id) {
		User user = userRepository.findByUserId(id);
		if(user==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		List<Address> addressList = addressRepository.findAllByUser(user);
		List<AddressDto> returnedValue = new ArrayList<AddressDto>();
		if(!CollectionUtils.isEmpty(addressList)) {
			Type listType = new TypeToken<List<AddressDto>>() {}.getType();
			 returnedValue = mapper.map(addressList, listType);
		}
		return new ResponseBaseModel<List<AddressDto>>(returnedValue, HttpStatus.OK);

	}

	// Extra Business Logic
	private void checkIfUserExist (long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
	}

}
