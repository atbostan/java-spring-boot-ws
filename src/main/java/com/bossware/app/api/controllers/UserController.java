package com.bossware.app.api.controllers;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bossware.app.business.services.UserService;
import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.models.request.RequestBaseModel;
import com.bossware.app.shared.models.request.ResponseBaseModel;

@RestController
@RequestMapping("users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping(consumes = { MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public ResponseBaseModel<User> createUser(@RequestBody RequestBaseModel<User> user) throws Exception {
		ResponseBaseModel<User> createdUser = userService.createUser(user.getData());
		return createdUser;

	}

}
