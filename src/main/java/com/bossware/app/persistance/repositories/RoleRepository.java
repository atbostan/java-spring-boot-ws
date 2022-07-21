package com.bossware.app.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bossware.app.shared.entities.Role;

@Repository
public interface RoleRepository extends PagingAndSortingRepository<Role, Long> {
	List<Role> findAllByUserId(String user_id);
	
	Role findByRoleId(String roleId);

}
