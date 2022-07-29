package com.bossware.app.persistance.repositories;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bossware.app.shared.entities.User;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {
	User findByUserName(String userName);
	User findUserByEmail(String email);

}
