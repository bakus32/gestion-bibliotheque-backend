

/*
 * Java controller for entity table auteur 
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
Controller for table "auteur"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/auteur")
public class AuteurController {

	@Autowired
    private ControllerFactory<AuteurDto> controllerFactory;
	@Autowired
	private AuteurBusiness auteurBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AuteurDto> create(@RequestBody Request<AuteurDto> request) {
    	log.info("start method /auteur/create");
        Response<AuteurDto> response = controllerFactory.create(auteurBusiness, request, FunctionalityEnum.CREATE_AUTEUR);
		log.info("end method /auteur/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AuteurDto> update(@RequestBody Request<AuteurDto> request) {
    	log.info("start method /auteur/update");
        Response<AuteurDto> response = controllerFactory.update(auteurBusiness, request, FunctionalityEnum.UPDATE_AUTEUR);
		log.info("end method /auteur/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AuteurDto> delete(@RequestBody Request<AuteurDto> request) {
    	log.info("start method /auteur/delete");
        Response<AuteurDto> response = controllerFactory.delete(auteurBusiness, request, FunctionalityEnum.DELETE_AUTEUR);
		log.info("end method /auteur/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AuteurDto> forceDelete(@RequestBody Request<AuteurDto> request) {
    	log.info("start method /auteur/forceDelete");
        Response<AuteurDto> response = controllerFactory.forceDelete(auteurBusiness, request, FunctionalityEnum.DELETE_AUTEUR);
		log.info("end method /auteur/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<AuteurDto> getByCriteria(@RequestBody Request<AuteurDto> request) {
    	log.info("start method /auteur/getByCriteria");
        Response<AuteurDto> response = controllerFactory.getByCriteria(auteurBusiness, request, FunctionalityEnum.VIEW_AUTEUR);
		log.info("end method /auteur/getByCriteria");
        return response;
    }
}
