package com.bossware.app.api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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


import com.bossware.app.business.services.UserService;
import com.bossware.app.shared.dto.UserDto;
import com.bossware.app.shared.models.request.RequestBaseModel;
import com.bossware.app.shared.models.response.ResponseBaseModel;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	

	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public  ResponseBaseModel<UserDto> getUser(@PathVariable String id) {
		ResponseBaseModel<UserDto> user = userService.getEntityById(id);
		return user;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<List<UserDto>> getUser(@RequestParam(value="page",defaultValue = "0") int page,@RequestParam(value="limit",defaultValue = "25") int limit) {
		ResponseBaseModel<List<UserDto>> userList = userService.getAll(page,limit);
		return new ResponseBaseModel<List<UserDto>>(userList.getData(),HttpStatus.OK);
	}


	@PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<UserDto> createUser(@RequestBody RequestBaseModel<UserDto> user) throws Exception {
		ResponseBaseModel<UserDto> createdUser = userService.create(user.getData());
		return createdUser;

	}
	

	@PutMapping(path = "/{id}",consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseBaseModel<UserDto> updateUser(@PathVariable String id ,@RequestBody RequestBaseModel<UserDto> user ) {
		ResponseBaseModel<UserDto> updatedUser = userService.update(id,user.getData());
		return updatedUser;
	}
	
	@DeleteMapping(path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void deleteUser(@PathVariable String id) {
		 userService.delete(id);

	}



}
