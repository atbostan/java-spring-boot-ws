package com.bossware.app.api.business.services;

import java.util.List;

import com.bossware.app.api.shared.entities.User;
import com.bossware.app.api.shared.models.request.ResponseBaseModel;

public interface UserService {
	
	ResponseBaseModel<List<User>> getUsers();

}
