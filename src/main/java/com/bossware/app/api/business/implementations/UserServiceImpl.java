package com.bossware.app.api.business.implementations;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.bossware.app.api.business.services.UserService;
import com.bossware.app.api.shared.entities.User;
import com.bossware.app.api.shared.models.request.ResponseBaseModel;

public class UserServiceImpl implements  UserService {

	@Override
	public ResponseBaseModel<List<User>> getUsers() {
		
		List<User> userList = new ArrayList<User>();
		
		// TODO Auto-generated method stub
		return new ResponseBaseModel(userList, HttpStatus.ACCEPTED);
	}

}
