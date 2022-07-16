package com.bossware.app.persistance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bossware.app.shared.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findUserByEmail(String email);

	User findByUserId(String userId);
	
	User findByUserName(String userName);
}
