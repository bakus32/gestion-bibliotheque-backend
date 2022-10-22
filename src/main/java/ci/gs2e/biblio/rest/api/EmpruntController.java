

/*
 * Java controller for entity table emprunt 
 * Created on 2022-10-22 ( Time 19:05:13 )
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
Controller for table "emprunt"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/emprunt")
public class EmpruntController {

	@Autowired
    private ControllerFactory<EmpruntDto> controllerFactory;
	@Autowired
	private EmpruntBusiness empruntBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<EmpruntDto> create(@RequestBody Request<EmpruntDto> request) {
    	log.info("start method /emprunt/create");
        Response<EmpruntDto> response = controllerFactory.create(empruntBusiness, request, FunctionalityEnum.CREATE_EMPRUNT);
		log.info("end method /emprunt/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<EmpruntDto> update(@RequestBody Request<EmpruntDto> request) {
    	log.info("start method /emprunt/update");
        Response<EmpruntDto> response = controllerFactory.update(empruntBusiness, request, FunctionalityEnum.UPDATE_EMPRUNT);
		log.info("end method /emprunt/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<EmpruntDto> delete(@RequestBody Request<EmpruntDto> request) {
    	log.info("start method /emprunt/delete");
        Response<EmpruntDto> response = controllerFactory.delete(empruntBusiness, request, FunctionalityEnum.DELETE_EMPRUNT);
		log.info("end method /emprunt/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<EmpruntDto> forceDelete(@RequestBody Request<EmpruntDto> request) {
    	log.info("start method /emprunt/forceDelete");
        Response<EmpruntDto> response = controllerFactory.forceDelete(empruntBusiness, request, FunctionalityEnum.DELETE_EMPRUNT);
		log.info("end method /emprunt/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<EmpruntDto> getByCriteria(@RequestBody Request<EmpruntDto> request) {
    	log.info("start method /emprunt/getByCriteria");
        Response<EmpruntDto> response = controllerFactory.getByCriteria(empruntBusiness, request, FunctionalityEnum.VIEW_EMPRUNT);
		log.info("end method /emprunt/getByCriteria");
        return response;
    }
}
