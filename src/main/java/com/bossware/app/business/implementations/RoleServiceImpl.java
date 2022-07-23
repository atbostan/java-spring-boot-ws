package com.bossware.app.business.implementations;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bossware.app.business.services.RoleService;
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
	RoleRepository repository;
	
	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper mapper;

	@Override
	public ResponseBaseModel<RoleDto> create(RoleDto t) {
		Role entity = mapper.map(t, Role.class);
		Role createdEntity = repository.save(entity);
		RoleDto returnedValue = mapper.map(createdEntity, RoleDto.class);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<RoleDto> getEntityById(String id) {
		Role roleEntity = repository.findByRoleId(id);
		RoleDto returnedValue = mapper.map(roleEntity, RoleDto.class);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);

	}

	@Override
	public ResponseBaseModel<RoleDto> update(String id, RoleDto t) {
		Role roleEntity = repository.findByRoleId(id);
		if (roleEntity == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		mapper.map(t, roleEntity);
		Role updatedEntity = repository.save(roleEntity);
		RoleDto returnedValue = mapper.map(updatedEntity, RoleDto.class);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);

	}

	@Override
	public void delete(String id) {
		Role roleEntity = repository.findByRoleId(id);
		if (roleEntity == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		repository.delete(roleEntity);

	}

	@Override
	public ResponseBaseModel<List<RoleDto>> getAll(int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		Page<Role> roleList = repository.findAll(pageReq);
		List<RoleDto> returnedValue = roleList.stream().map(e -> mapper.map(e, RoleDto.class))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<RoleDto>>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<List<RoleDto>> getRolesByUserId(String id) {
		User entity = userRepository.findByUserId(id);
		if(entity==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		List<Role> roleList = repository.findAllByUser(entity);
		List<RoleDto> returnedValue = roleList.stream().map(e -> mapper.map(e, RoleDto.class))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<RoleDto>>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<RoleDto> findRoleById(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

}
