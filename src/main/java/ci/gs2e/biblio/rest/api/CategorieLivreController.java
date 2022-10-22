

/*
 * Java controller for entity table categorie_livre 
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
Controller for table "categorie_livre"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/categorieLivre")
public class CategorieLivreController {

	@Autowired
    private ControllerFactory<CategorieLivreDto> controllerFactory;
	@Autowired
	private CategorieLivreBusiness categorieLivreBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategorieLivreDto> create(@RequestBody Request<CategorieLivreDto> request) {
    	log.info("start method /categorieLivre/create");
        Response<CategorieLivreDto> response = controllerFactory.create(categorieLivreBusiness, request, FunctionalityEnum.CREATE_CATEGORIE_LIVRE);
		log.info("end method /categorieLivre/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategorieLivreDto> update(@RequestBody Request<CategorieLivreDto> request) {
    	log.info("start method /categorieLivre/update");
        Response<CategorieLivreDto> response = controllerFactory.update(categorieLivreBusiness, request, FunctionalityEnum.UPDATE_CATEGORIE_LIVRE);
		log.info("end method /categorieLivre/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategorieLivreDto> delete(@RequestBody Request<CategorieLivreDto> request) {
    	log.info("start method /categorieLivre/delete");
        Response<CategorieLivreDto> response = controllerFactory.delete(categorieLivreBusiness, request, FunctionalityEnum.DELETE_CATEGORIE_LIVRE);
		log.info("end method /categorieLivre/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategorieLivreDto> forceDelete(@RequestBody Request<CategorieLivreDto> request) {
    	log.info("start method /categorieLivre/forceDelete");
        Response<CategorieLivreDto> response = controllerFactory.forceDelete(categorieLivreBusiness, request, FunctionalityEnum.DELETE_CATEGORIE_LIVRE);
		log.info("end method /categorieLivre/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<CategorieLivreDto> getByCriteria(@RequestBody Request<CategorieLivreDto> request) {
    	log.info("start method /categorieLivre/getByCriteria");
        Response<CategorieLivreDto> response = controllerFactory.getByCriteria(categorieLivreBusiness, request, FunctionalityEnum.VIEW_CATEGORIE_LIVRE);
		log.info("end method /categorieLivre/getByCriteria");
        return response;
    }
}
