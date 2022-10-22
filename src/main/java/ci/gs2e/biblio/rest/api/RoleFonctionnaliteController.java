

/*
 * Java controller for entity table role_fonctionnalite 
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
Controller for table "role_fonctionnalite"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/roleFonctionnalite")
public class RoleFonctionnaliteController {

	@Autowired
    private ControllerFactory<RoleFonctionnaliteDto> controllerFactory;
	@Autowired
	private RoleFonctionnaliteBusiness roleFonctionnaliteBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionnaliteDto> create(@RequestBody Request<RoleFonctionnaliteDto> request) {
    	log.info("start method /roleFonctionnalite/create");
        Response<RoleFonctionnaliteDto> response = controllerFactory.create(roleFonctionnaliteBusiness, request, FunctionalityEnum.CREATE_ROLE_FONCTIONNALITE);
		log.info("end method /roleFonctionnalite/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionnaliteDto> update(@RequestBody Request<RoleFonctionnaliteDto> request) {
    	log.info("start method /roleFonctionnalite/update");
        Response<RoleFonctionnaliteDto> response = controllerFactory.update(roleFonctionnaliteBusiness, request, FunctionalityEnum.UPDATE_ROLE_FONCTIONNALITE);
		log.info("end method /roleFonctionnalite/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionnaliteDto> delete(@RequestBody Request<RoleFonctionnaliteDto> request) {
    	log.info("start method /roleFonctionnalite/delete");
        Response<RoleFonctionnaliteDto> response = controllerFactory.delete(roleFonctionnaliteBusiness, request, FunctionalityEnum.DELETE_ROLE_FONCTIONNALITE);
		log.info("end method /roleFonctionnalite/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionnaliteDto> forceDelete(@RequestBody Request<RoleFonctionnaliteDto> request) {
    	log.info("start method /roleFonctionnalite/forceDelete");
        Response<RoleFonctionnaliteDto> response = controllerFactory.forceDelete(roleFonctionnaliteBusiness, request, FunctionalityEnum.DELETE_ROLE_FONCTIONNALITE);
		log.info("end method /roleFonctionnalite/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<RoleFonctionnaliteDto> getByCriteria(@RequestBody Request<RoleFonctionnaliteDto> request) {
    	log.info("start method /roleFonctionnalite/getByCriteria");
        Response<RoleFonctionnaliteDto> response = controllerFactory.getByCriteria(roleFonctionnaliteBusiness, request, FunctionalityEnum.VIEW_ROLE_FONCTIONNALITE);
		log.info("end method /roleFonctionnalite/getByCriteria");
        return response;
    }
}
