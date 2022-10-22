

/*
 * Java controller for entity table livre_auteur 
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
Controller for table "livre_auteur"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/livreAuteur")
public class LivreAuteurController {

	@Autowired
    private ControllerFactory<LivreAuteurDto> controllerFactory;
	@Autowired
	private LivreAuteurBusiness livreAuteurBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreAuteurDto> create(@RequestBody Request<LivreAuteurDto> request) {
    	log.info("start method /livreAuteur/create");
        Response<LivreAuteurDto> response = controllerFactory.create(livreAuteurBusiness, request, FunctionalityEnum.CREATE_LIVRE_AUTEUR);
		log.info("end method /livreAuteur/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreAuteurDto> update(@RequestBody Request<LivreAuteurDto> request) {
    	log.info("start method /livreAuteur/update");
        Response<LivreAuteurDto> response = controllerFactory.update(livreAuteurBusiness, request, FunctionalityEnum.UPDATE_LIVRE_AUTEUR);
		log.info("end method /livreAuteur/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreAuteurDto> delete(@RequestBody Request<LivreAuteurDto> request) {
    	log.info("start method /livreAuteur/delete");
        Response<LivreAuteurDto> response = controllerFactory.delete(livreAuteurBusiness, request, FunctionalityEnum.DELETE_LIVRE_AUTEUR);
		log.info("end method /livreAuteur/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreAuteurDto> forceDelete(@RequestBody Request<LivreAuteurDto> request) {
    	log.info("start method /livreAuteur/forceDelete");
        Response<LivreAuteurDto> response = controllerFactory.forceDelete(livreAuteurBusiness, request, FunctionalityEnum.DELETE_LIVRE_AUTEUR);
		log.info("end method /livreAuteur/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreAuteurDto> getByCriteria(@RequestBody Request<LivreAuteurDto> request) {
    	log.info("start method /livreAuteur/getByCriteria");
        Response<LivreAuteurDto> response = controllerFactory.getByCriteria(livreAuteurBusiness, request, FunctionalityEnum.VIEW_LIVRE_AUTEUR);
		log.info("end method /livreAuteur/getByCriteria");
        return response;
    }
}
