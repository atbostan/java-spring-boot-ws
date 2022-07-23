package com.bossware.app.business.implementations;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
import com.bossware.app.core.utils.EntityStrIdGenerator;
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

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private EntityStrIdGenerator userIdGenerator;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ResponseBaseModel<UserDto> create(UserDto t) {

		for (int i = 0; i < t.getAddresses().size(); i++) {
			AddressDto addressDto = t.getAddresses().get(i);
			addressDto.setUser(t);
			addressDto.setAddressId(userIdGenerator.generateId(30));
			t.getAddresses().set(i, addressDto);	
		}
		
		for (int i = 0; i < t.getRoles().size(); i++) {
			RoleDto roleDto = t.getRoles().get(i);
			roleDto.setUser(t);
			roleDto.setRoleId(userIdGenerator.generateId(15));
			t.getRoles().set(i, roleDto);	
		}

		TypeMap<UserDto, User> typeMap = mapper.getTypeMap(UserDto.class, User.class);
		createEntityMapping();
		User user = mapper.map(t, User.class);
		user.setUserId(userIdGenerator.generateId(20));
		user.setPassword(encoder.encode(t.getPassword()));
	    User createdUser = userRepository.save(user);
	    UserDto returnedEntity = mapper.map(createdUser, UserDto.class);
	    return new ResponseBaseModel<UserDto>(returnedEntity, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<UserDto> getEntityById(String id) {
		User user = userRepository.findByUserId(id);
		UserDto returnedValue = mapper.map(user, UserDto.class);
		return new ResponseBaseModel<UserDto>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<UserDto> update(String id, UserDto t) {
		User user = userRepository.findByUserId(id);
		if (user == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		User userToUpdated = mapper.map(t, User.class);
		userToUpdated.setUserId(id);
		userToUpdated.setId(user.getId());
		User updatedEntity = userRepository.save(userToUpdated);
		UserDto returnedValue = mapper.map(updatedEntity, UserDto.class);
		List<Role> role = roleRepository.findAllByUser(user);
		List<Address> address = addressRepository.findAllByUser(user);
		returnedValue.setAddresses(address.stream().map(e->mapper.map(e, AddressDto.class)).collect(Collectors.toList()));
		returnedValue.setRoles(role.stream().map(e->mapper.map(e, RoleDto.class)).collect(Collectors.toList()));

		return new ResponseBaseModel<UserDto>(returnedValue, HttpStatus.OK);

	}

	@Override
	public void delete(String id) {
		User user = userRepository.findByUserId(id);
		if (user == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		userRepository.delete(user);
	}

	@Override
	public ResponseBaseModel<List<UserDto>> getAll(int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		Page<User> userList = userRepository.findAll(pageReq);
		createEntityMapping();
		List<UserDto> returnedValue = userList.stream().map(e -> mapper.map(e, UserDto.class))
				.collect(Collectors.toList());
		
		return new ResponseBaseModel<List<UserDto>>(returnedValue, HttpStatus.OK);
	}

	
	private void createEntityMapping() {
		TypeMap<UserDto, User> typeMap = mapper.getTypeMap(UserDto.class, User.class);
		if(typeMap==null) {
			mapper.createTypeMap(UserDto.class, User.class)
		    .addMapping(UserDto::getAddresses, User::setAdresses)
		    .addMapping(UserDto::getRoles, User::setRoles);
			mapper.createTypeMap(User.class, UserDto.class)
		    .addMapping(User::getAdresses, UserDto::setAddresses)
		    .addMapping(User::getRoles, UserDto::setRoles);

		}
	}


}
