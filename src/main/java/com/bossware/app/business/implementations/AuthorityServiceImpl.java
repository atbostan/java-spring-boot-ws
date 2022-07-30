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

import com.bossware.app.business.services.AuthorityService;
import com.bossware.app.business.utils.mapper.user.AuthoritySourceDestinationMapper;
import com.bossware.app.business.utils.mapper.user.RoleSourceDestinationMapper;
import com.bossware.app.persistance.repositories.AuthorityRepository;
import com.bossware.app.persistance.repositories.RoleRepository;
import com.bossware.app.shared.dto.AuthorityDto;
import com.bossware.app.shared.entities.Authority;
import com.bossware.app.shared.entities.Role;
import com.bossware.app.shared.messages.ErrorMessages;
import com.bossware.app.shared.models.exceptions.ServiceExceptionBase;
import com.bossware.app.shared.models.response.ResponseBaseModel;
import com.bossware.app.shared.utils.EntityStrIdGenerator;

@Service
public class AuthorityServiceImpl implements AuthorityService {

	private AuthoritySourceDestinationMapper mapper
    = Mappers.getMapper(AuthoritySourceDestinationMapper.class);
	@Autowired
	private AuthorityRepository authorityRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
    //C - U - D
	@Override
	public ResponseBaseModel<AuthorityDto> create(AuthorityDto t) {
		Role role = checkIfRoleExist(t.getRoleId());
		Authority authority = new Authority();
		authority.setAuthName(t.getAuthName());
		authority.setRole(role);
		Authority createdEntity = authorityRepository.save(authority);
		AuthorityDto returnedValue = mapper.entityToDto(createdEntity);
		return new ResponseBaseModel<AuthorityDto>(returnedValue, HttpStatus.OK);
	}
	
	@Override
	public ResponseBaseModel<AuthorityDto> update(long id, AuthorityDto t) {
		Optional<Authority> auth = authorityRepository.findById(id);
		if (auth == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		Authority authToUpdate = mapper.dtoToEntity(t);
		authToUpdate.setId(auth.get().getId());
		Authority updatedEntity = authorityRepository.save(authToUpdate);
		AuthorityDto returnedValue = mapper.entityToDto(updatedEntity);
		return new ResponseBaseModel<AuthorityDto>(returnedValue, HttpStatus.OK);
	}
	
	@Override
	public void delete(long id) {
		Optional<Authority> auth = authorityRepository.findById(id);
		if (auth == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		authorityRepository.delete(auth.get());

		
	}

	@Override
	public ResponseBaseModel<AuthorityDto> getEntityById(long id) {
		Optional<Authority> authEntity = authorityRepository.findById(id);
		if (authEntity == null)
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		AuthorityDto returnedValue = mapper.entityToDto(authEntity.get());
		return new ResponseBaseModel<AuthorityDto>(returnedValue, HttpStatus.OK);
	}


	@Override
	public ResponseBaseModel<List<AuthorityDto>> getAll(int page, int limit) {
		Pageable pageReq = PageRequest.of(page, limit);
		Page<Authority> authList = authorityRepository.findAll(pageReq);
		List<AuthorityDto> returnedValue = authList.stream().map(e -> mapper.entityToDto(e))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<AuthorityDto>>(returnedValue, HttpStatus.OK);
	}

	@Override
	public ResponseBaseModel<List<AuthorityDto>> getAuthoritiesByRoleId(long id) {
		Optional<Role> entity = roleRepository.findById(id);
		if(entity==null)  throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		List<Authority> authList = authorityRepository.findAllByRole(entity.get());
		List<AuthorityDto> returnedValue = authList.stream().map(e -> mapper.entityToDto(e))
				.collect(Collectors.toList());
		return new ResponseBaseModel<List<AuthorityDto>>(returnedValue, HttpStatus.OK);
	}
	
	
	// Extra Business Logic
	private Role checkIfRoleExist (long id) {
		Optional<Role> role = roleRepository.findById(id);
		if (!role.isPresent())
			throw new ServiceExceptionBase(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
		return role.get();
	}

}
