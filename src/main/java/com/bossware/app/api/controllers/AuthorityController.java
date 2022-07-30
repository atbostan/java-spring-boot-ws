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

import com.bossware.app.business.services.AuthorityService;
import com.bossware.app.shared.dto.AuthorityDto;
import com.bossware.app.shared.dto.RoleDto;
import com.bossware.app.shared.models.request.RequestBaseModel;
import com.bossware.app.shared.models.response.ResponseBaseModel;

@RestController
@RequestMapping("authority")
public class AuthorityController {

	@Autowired
	AuthorityService authorityService;
	
	 //C - U - D
    @PostMapping(consumes = { MediaType.APPLICATION_JSON_VALUE  }, produces = {
			MediaType.APPLICATION_JSON_VALUE  })
	public ResponseBaseModel<AuthorityDto> createAuth(@RequestBody RequestBaseModel<AuthorityDto> auth) throws Exception {
		ResponseBaseModel<AuthorityDto> createdAuth = authorityService.create(auth.getData());
		return createdAuth;
	}
	

	@PutMapping(path = "/{id}",consumes = {MediaType.APPLICATION_JSON_VALUE }, produces = {
			 MediaType.APPLICATION_JSON_VALUE })
	public ResponseBaseModel<AuthorityDto> updateAuth(@PathVariable long id ,@RequestBody RequestBaseModel<AuthorityDto> auth ) {
		ResponseBaseModel<AuthorityDto> updateAuth = authorityService.update(id,auth.getData());
		return updateAuth;
	}
	
	@DeleteMapping(path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE  })
	public void deleteAuth(@PathVariable long id) {
		authorityService.delete(id);
	}

    //R
	@GetMapping(path = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE  })
	public  ResponseBaseModel<AuthorityDto> getAuths(@PathVariable long id) {
		ResponseBaseModel<AuthorityDto> auth = authorityService.getEntityById(id);
		return auth;
	}
	
	@GetMapping(produces = { MediaType.APPLICATION_JSON_VALUE  })
	public ResponseBaseModel<List<AuthorityDto>> getAuths(@RequestParam(value="page",defaultValue = "0") int page,@RequestParam(value="limit",defaultValue = "25") int limit) {
		ResponseBaseModel<List<AuthorityDto>> authList = authorityService.getAll(page,limit);
		return new ResponseBaseModel<List<AuthorityDto>>(authList.getData(),HttpStatus.OK);
	}
	
	
    @GetMapping(path = "/{id}/authorities", produces = { MediaType.APPLICATION_JSON_VALUE  })
	public ResponseBaseModel<List<AuthorityDto>> getAuthsByRole(@PathVariable long id){
		ResponseBaseModel<List<AuthorityDto>> authList = authorityService.getAuthoritiesByRoleId(id);
		return new ResponseBaseModel<List<AuthorityDto>>(authList.getData(),HttpStatus.OK);
	}
	
}
