package com.bossware.app.business.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bossware.app.business.services.RoleService;
import com.bossware.app.core.utils.EntityStrIdGenerator;
import com.bossware.app.persistance.repositories.RoleRepository;
import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.entities.Role;
import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.messages.ErrorMessages;
import com.bossware.app.shared.models.exceptions.ServiceExceptionBase;
import com.bossware.app.shared.models.response.ResponseBaseModel;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private EntityStrIdGenerator roleIdGenerator;

    //C - U - D
	@Override
	public ResponseBaseModel<RoleDto> create(RoleDto t) {
		checkIfUserExist(t.getUserId());
		Role mappedRoles = mapper.map(t, Role.class);
		mappedRoles.setRoleId(roleIdGenerator.generateId(20));
		Role createdEntity = roleRepository.save(mappedRoles);
		RoleDto returnedValue = mapper.map(createdEntity, RoleDto.class);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);
	}

    @Override
	public ResponseBaseModel<RoleDto> update(String id, RoleDto t) {
		Role role = roleRepository.findByRoleId(id);
		if (role == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		Role roleToUpdate = mapper.map(t, Role.class);
		roleToUpdate.setRoleId(role.getRoleId());
		roleToUpdate.setId(role.getId());
		Role updatedEntity = roleRepository.save(roleToUpdate);
		RoleDto returnedValue = mapper.map(updatedEntity, RoleDto.class);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);
	}

    @Override
	public void delete(String id) {
		Role role = roleRepository.findByRoleId(id);
		if (role == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		roleRepository.delete(role);

	}

    // R
	@Override
	public ResponseBaseModel<RoleDto> getEntityById(String id) {
		Role roleEntity = roleRepository.findByRoleId(id);
		if (roleEntity == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		RoleDto returnedValue = mapper.map(roleEntity, RoleDto.class);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);

	}

	@Override
	public ResponseBaseModel<List<RoleDto>> getAll(int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		Page<Role> roleList = roleRepository.findAll(pageReq);
		List<RoleDto> returnedValue = roleList.stream().map(e -> mapper.map(e, RoleDto.class))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<RoleDto>>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<List<RoleDto>> getRolesByUserId(String id) {
		User entity = userRepository.findByUserId(id);
		if(entity==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		List<Role> roleList = roleRepository.findAllByUser(entity);
		List<RoleDto> returnedValue = roleList.stream().map(e -> mapper.map(e, RoleDto.class))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<RoleDto>>(returnedValue, HttpStatus.OK);
	}


	// Extra Business Logic
	private void checkIfUserExist (long id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent())
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
	}

}
