package com.bossware.app.business.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bossware.app.business.services.UserService;
import com.bossware.app.business.utils.mapper.user.UserSourceDestinationMapper;
import com.bossware.app.persistance.repositories.AddressRepository;
import com.bossware.app.persistance.repositories.RoleRepository;
import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.dto.UserDto;
import com.bossware.app.shared.entities.Address;
import com.bossware.app.shared.entities.Role;
import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.messages.ErrorMessages;
import com.bossware.app.shared.models.exceptions.ServiceExceptionBase;
import com.bossware.app.shared.models.response.ResponseBaseModel;
import com.bossware.app.shared.utils.EntityStrIdGenerator;

@Service
public class UserServiceImpl implements UserService {

	private UserSourceDestinationMapper mapper
    = Mappers.getMapper(UserSourceDestinationMapper.class);
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;


    //C - U - D
	@Override
	public ResponseBaseModel<UserDto> create(UserDto t) {
		User mappedUser = mapper.dtoToEntity(t);
		mappedUser.setPassword(encoder.encode(t.getPassword()));
	    User createdUser = userRepository.save(mappedUser);
	    UserDto returnedValue = mapper.entityToDto(createdUser);
	    return new ResponseBaseModel<UserDto>(returnedValue, HttpStatus.OK);
	}

    @Override
	public ResponseBaseModel<UserDto> update(long id, UserDto t) {
		Optional<User> user = userRepository.findById(id);
		if (user == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		User userToUpdated = mapper.dtoToEntity(t);
		userToUpdated.setId(user.get().getId());
		User updatedEntity = userRepository.save(userToUpdated);
	    UserDto returnedValue = mapper.entityToDto(updatedEntity);

		return new ResponseBaseModel<UserDto>(returnedValue, HttpStatus.OK);
	}

	@Override
	public void delete(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(user.get());
	}

    // R
	@Override
	public ResponseBaseModel<UserDto> getEntityById(long id) {
		Optional<User> user = userRepository.findById(id);
		if (user == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		UserDto returnedValue = mapper.entityToDto(user.get());
		return new ResponseBaseModel<UserDto>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<List<UserDto>> getAll(int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		Page<User> userList = userRepository.findAll(pageReq);
		List<UserDto> returnedValue = userList.stream().map(e -> mapper.entityToDto(e))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<UserDto>>(returnedValue, HttpStatus.OK);
	}

	// Extra Business Logic
	


}
