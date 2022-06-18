package com.bossware.app.business.implementations;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.bossware.app.business.services.UserService;
import com.bossware.app.core.utils.UserIdGenerator;
import com.bossware.app.persistance.repositories.UserRepository;
import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.models.response.ResponseBaseModel;
@Service

public class UserServiceImpl implements  UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserIdGenerator userIdGenerator;
	

	
	@Override
	public ResponseBaseModel<User> create(User user) {
		user.setUserId(userIdGenerator.generateUserId(5));
		user.setEncryptedPassword("test");
	    User createdUser = userRepository.save(user);
	    ResponseBaseModel<User> usr = new ResponseBaseModel<User>(createdUser, HttpStatus.OK);
	    return usr;    
	    
	}

	@Override
	public ResponseBaseModel<User> getEntityById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBaseModel<List<User>> getAll(int page, int limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResponseBaseModel<User> update(String id, User t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(String id) {
		// TODO Auto-generated method stub
		
	}

}
