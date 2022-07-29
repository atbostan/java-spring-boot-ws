package com.bossware.app.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.bossware.app.shared.entities.Authority;
import com.bossware.app.shared.entities.Role;

@Repository
public interface AuthorityRepository extends PagingAndSortingRepository<Authority, Long>{
	List<Authority> findAllByRole(Role role);
}
