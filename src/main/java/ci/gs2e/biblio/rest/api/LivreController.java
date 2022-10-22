

/*
 * Java controller for entity table livre 
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
Controller for table "livre"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/livre")
public class LivreController {

	@Autowired
    private ControllerFactory<LivreDto> controllerFactory;
	@Autowired
	private LivreBusiness livreBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreDto> create(@RequestBody Request<LivreDto> request) {
    	log.info("start method /livre/create");
        Response<LivreDto> response = controllerFactory.create(livreBusiness, request, FunctionalityEnum.CREATE_LIVRE);
		log.info("end method /livre/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreDto> update(@RequestBody Request<LivreDto> request) {
    	log.info("start method /livre/update");
        Response<LivreDto> response = controllerFactory.update(livreBusiness, request, FunctionalityEnum.UPDATE_LIVRE);
		log.info("end method /livre/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreDto> delete(@RequestBody Request<LivreDto> request) {
    	log.info("start method /livre/delete");
        Response<LivreDto> response = controllerFactory.delete(livreBusiness, request, FunctionalityEnum.DELETE_LIVRE);
		log.info("end method /livre/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreDto> forceDelete(@RequestBody Request<LivreDto> request) {
    	log.info("start method /livre/forceDelete");
        Response<LivreDto> response = controllerFactory.forceDelete(livreBusiness, request, FunctionalityEnum.DELETE_LIVRE);
		log.info("end method /livre/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<LivreDto> getByCriteria(@RequestBody Request<LivreDto> request) {
    	log.info("start method /livre/getByCriteria");
        Response<LivreDto> response = controllerFactory.getByCriteria(livreBusiness, request, FunctionalityEnum.VIEW_LIVRE);
		log.info("end method /livre/getByCriteria");
        return response;
    }
}
