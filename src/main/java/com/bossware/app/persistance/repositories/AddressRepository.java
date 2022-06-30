package com.bossware.app.persistance.repositories;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bossware.app.shared.entities.Address;
import com.bossware.app.shared.entities.User;

public interface AddressRepository extends PagingAndSortingRepository<Address, Long> {
	Address findByAddressId(String addressId);
	
	List<Address> findAllByUser(User user);
}
