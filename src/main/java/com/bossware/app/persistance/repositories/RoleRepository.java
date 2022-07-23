package com.bossware.app.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bossware.app.shared.entities.Role;
import com.bossware.app.shared.entities.User;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
	List<Role> findAllByUser(User user);
	
	Role findByRoleId(String roleId);

}
