

/*
 * Java controller for entity table users 
 * Created on 2022-10-21 ( Time 23:35:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017  Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.rest.api;

import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ci.gs2e.biblio.helper.*;
import ci.gs2e.biblio.helper.dto.*;
import ci.gs2e.biblio.helper.contrat.*;
import ci.gs2e.biblio.helper.contrat.Request;
import ci.gs2e.biblio.helper.contrat.Response;
import ci.gs2e.biblio.helper.enums.FunctionalityEnum;
import ci.gs2e.biblio.business.*;
import ci.gs2e.biblio.rest.fact.ControllerFactory;

/**
Controller for table "users"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/users")
public class UsersController {

	@Autowired
    private ControllerFactory<UsersDto> controllerFactory;
	@Autowired
	private UsersBusiness usersBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> create(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/create");
        Response<UsersDto> response = controllerFactory.create(usersBusiness, request, FunctionalityEnum.CREATE_USERS);
		log.info("end method /users/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> update(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/update");
        Response<UsersDto> response = controllerFactory.update(usersBusiness, request, FunctionalityEnum.UPDATE_USERS);
		log.info("end method /users/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> delete(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/delete");
        Response<UsersDto> response = controllerFactory.delete(usersBusiness, request, FunctionalityEnum.DELETE_USERS);
		log.info("end method /users/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> forceDelete(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/forceDelete");
        Response<UsersDto> response = controllerFactory.forceDelete(usersBusiness, request, FunctionalityEnum.DELETE_USERS);
		log.info("end method /users/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> getByCriteria(@RequestBody Request<UsersDto> request) {
    	log.info("start method /users/getByCriteria");
        Response<UsersDto> response = controllerFactory.getByCriteria(usersBusiness, request, FunctionalityEnum.VIEW_USERS);
		log.info("end method /users/getByCriteria");
        return response;
    }
	@RequestMapping(value="/changePassword",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> changePassword(@RequestBody Request<UsersDto> request) {
    	log.info("start method /user/changePassword");
        Response<UsersDto> response = controllerFactory.changePassword(usersBusiness, request, FunctionalityEnum.VIEW_USERS);
		log.info("end method /user/changePassword");
        return response;
    }
	
	@RequestMapping(value="/resetAccount",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> resetAccount(@RequestBody Request<UsersDto> request) {
    	log.info("start method /user/resetAccount");
        Response<UsersDto> response = controllerFactory.resetAccount(usersBusiness, request, FunctionalityEnum.VIEW_USERS);
		log.info("end method /user/resetAccount");
        return response;
    }

	@RequestMapping(value="/login",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsersDto> login(@RequestBody Request<UsersDto> request) {
    	log.info("start method /user/login");
        Response<UsersDto> response = controllerFactory.login(usersBusiness, request, FunctionalityEnum.VIEW_USERS);
		log.info("end method /user/login");
        return response;
    }

}
