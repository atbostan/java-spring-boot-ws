package com.bossware.app.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bossware.app.business.services.RoleService;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.models.request.RequestBaseModel;
import com.bossware.app.shared.models.response.ResponseBaseModel;

@RestController
@RequestMapping("role")
public class RoleController {
	@Autowired
	RoleService roleService;
	

    //C - U - D
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<RoleDto> createAddress(@RequestBody RequestBaseModel<RoleDto> role) throws Exception {
		ResponseBaseModel<RoleDto> createdRoles = roleService.create(role.getData());
		return createdRoles;
	}
	

	@PutMapping(path = "/{id}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseBaseModel<RoleDto> updateAddress(@PathVariable String id ,@RequestBody RequestBaseModel<RoleDto> role ) {
		ResponseBaseModel<RoleDto> updateRole = roleService.update(id,role.getData());
		return updateRole;
	}
	
	@DeleteMapping(path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void deleteAddress(@PathVariable String id) {
		roleService.delete(id);
	}

    //R
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public  ResponseBaseModel<RoleDto> getRoles(@PathVariable String id) {
		ResponseBaseModel<RoleDto> roles = roleService.getEntityById(id);
		return roles;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<List<RoleDto>> getRoles(@RequestParam(value="page",defaultValue = "0") int page,@RequestParam(value="limit",defaultValue = "25") int limit) {
		ResponseBaseModel<List<RoleDto>> roleList = roleService.getAll(page,limit);
		return new ResponseBaseModel<List<RoleDto>>(roleList.getData(),HttpStatus.OK);
	}


	
}
