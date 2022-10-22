

/*
 * Java controller for entity table role 
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
Controller for table "role"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/role")
public class RoleController {

	@Autowired
    private ControllerFactory<RoleDto> controllerFactory;
	@Autowired
	private RoleBusiness roleBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> create(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/create");
        Response<RoleDto> response = controllerFactory.create(roleBusiness, request, FunctionalityEnum.CREATE_ROLE);
		log.info("end method /role/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> update(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/update");
        Response<RoleDto> response = controllerFactory.update(roleBusiness, request, FunctionalityEnum.UPDATE_ROLE);
		log.info("end method /role/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> delete(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/delete");
        Response<RoleDto> response = controllerFactory.delete(roleBusiness, request, FunctionalityEnum.DELETE_ROLE);
		log.info("end method /role/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> forceDelete(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/forceDelete");
        Response<RoleDto> response = controllerFactory.forceDelete(roleBusiness, request, FunctionalityEnum.DELETE_ROLE);
		log.info("end method /role/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleDto> getByCriteria(@RequestBody Request<RoleDto> request) {
    	log.info("start method /role/getByCriteria");
        Response<RoleDto> response = controllerFactory.getByCriteria(roleBusiness, request, FunctionalityEnum.VIEW_ROLE);
		log.info("end method /role/getByCriteria");
        return response;
    }
}
