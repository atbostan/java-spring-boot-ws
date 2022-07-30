package com.bossware.app.business.implementations;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.mapstruct.factory.Mappers;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bossware.app.business.services.RoleService;
import com.bossware.app.business.utils.mapper.user.RoleSourceDestinationMapper;
import com.bossware.app.business.utils.mapper.user.UserSourceDestinationMapper;
import com.bossware.app.persistance.repositories.RoleRepository;
import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.entities.Address;
import com.bossware.app.shared.entities.Role;
import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.messages.ErrorMessages;
import com.bossware.app.shared.models.exceptions.ServiceExceptionBase;
import com.bossware.app.shared.models.response.ResponseBaseModel;
import com.bossware.app.shared.utils.EntityStrIdGenerator;

@Service
public class RoleServiceImpl implements RoleService {

	private RoleSourceDestinationMapper mapper
    = Mappers.getMapper(RoleSourceDestinationMapper.class);
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;

    //C - U - D
	@Override
	public ResponseBaseModel<RoleDto> create(RoleDto t) {
		checkIfUserExist(t.getUserId());
		Role mappedRoles = mapper.dtoToEntity(t);
		Role createdEntity = roleRepository.save(mappedRoles);
		RoleDto returnedValue = mapper.entityToDto(createdEntity);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);
	}

    @Override
	public ResponseBaseModel<RoleDto> update(long id, RoleDto t) {
		Optional<Role> role = roleRepository.findById(id);
		if (role == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		Role roleToUpdate = mapper.dtoToEntity(t);
		roleToUpdate.setId(role.get().getId());
		Role updatedEntity = roleRepository.save(roleToUpdate);
		RoleDto returnedValue = mapper.entityToDto(updatedEntity);
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);
	}

    @Override
	public void delete(long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (role == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		roleRepository.delete(role.get());

	}

    // R
	@Override
	public ResponseBaseModel<RoleDto> getEntityById(long id) {
		Optional<Role> roleEntity = roleRepository.findById(id);
		if (roleEntity == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		RoleDto returnedValue = mapper.entityToDto(roleEntity.get());
		return new ResponseBaseModel<RoleDto>(returnedValue, HttpStatus.OK);

	}

	@Override
	public ResponseBaseModel<List<RoleDto>> getAll(int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		Page<Role> roleList = roleRepository.findAll(pageReq);
		List<RoleDto> returnedValue = roleList.stream().map(e -> mapper.entityToDto(e))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<RoleDto>>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<List<RoleDto>> getRolesByUserId(long id) {
		Optional<User> entity = userRepository.findById(id);
		if(entity==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		List<Role> roleList = roleRepository.findAllByUser(entity.get());
		List<RoleDto> returnedValue = roleList.stream().map(e -> mapper.entityToDto(e))
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
