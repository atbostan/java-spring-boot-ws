package com.bossware.app.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bossware.app.business.services.AddressService;
import com.bossware.app.shared.dto.AddressDto;
import com.bossware.app.shared.models.request.RequestBaseModel;
import com.bossware.app.shared.models.response.ResponseBaseModel;

@RestController
@RequestMapping("address")
public class AddressController {
	@Autowired
	AddressService addressService;
	
    //C - U - D
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<AddressDto> createAddress(@RequestBody RequestBaseModel<AddressDto> address) throws Exception {
		ResponseBaseModel<AddressDto> createdAddress = addressService.create(address.getData());
		return createdAddress;
	}
	

	@PutMapping(path = "/{id}",consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<AddressDto> updateAddress(@PathVariable String id ,@RequestBody RequestBaseModel<AddressDto> address ) {
		ResponseBaseModel<AddressDto> updateAddress = addressService.update(id,address.getData());
		return updateAddress;
	}
	
	@DeleteMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public void deleteAddress(@PathVariable String id) {
		addressService.delete(id);
	}

    //R
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public  ResponseBaseModel<AddressDto> getAddress(@PathVariable String id) {
		ResponseBaseModel<AddressDto> address = addressService.getEntityById(id);
		return address;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<List<AddressDto>> getAddress(@RequestParam(value="page",defaultValue = "0") int page,@RequestParam(value="limit",defaultValue = "25") int limit) {
		ResponseBaseModel<List<AddressDto>> addressList = addressService.getAll(page,limit);
		return new ResponseBaseModel<List<AddressDto>>(addressList.getData(),HttpStatus.OK);
	}

    @GetMapping(path = "/{id}/address", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
	public ResponseBaseModel<List<AddressDto>> getAddressesByUser(@PathVariable String id){
		ResponseBaseModel<List<AddressDto>> addressList = addressService.getAddressByUserId(id);
		return new ResponseBaseModel<List<AddressDto>>(addressList.getData(),HttpStatus.OK);
	}



}
