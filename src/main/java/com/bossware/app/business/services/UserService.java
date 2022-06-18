package com.bossware.app.business.services;

import java.util.List;

import com.bossware.app.shared.entities.User;
import com.bossware.app.shared.models.request.ResponseBaseModel;

public interface UserService {
	
	ResponseBaseModel<User> createUser(User user);

	ResponseBaseModel<User> getUserByUserId(String id);

	ResponseBaseModel<User> updateUser(String id, User userDto);

	void deleteUser(String id);

	ResponseBaseModel<List<User>> getAll(int page, int limit);
	

}
