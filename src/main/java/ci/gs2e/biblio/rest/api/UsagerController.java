

/*
 * Java controller for entity table usager 
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
Controller for table "usager"
 * 
 * @author SFL Back-End developper
 *
 */
@Log
@CrossOrigin("*")
@RestController
@RequestMapping(value="/usager")
public class UsagerController {

	@Autowired
    private ControllerFactory<UsagerDto> controllerFactory;
	@Autowired
	private UsagerBusiness usagerBusiness;

	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> create(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /usager/create");
        Response<UsagerDto> response = controllerFactory.create(usagerBusiness, request, FunctionalityEnum.CREATE_USAGER);
		log.info("end method /usager/create");
        return response;
    }

	@RequestMapping(value="/update",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> update(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /usager/update");
        Response<UsagerDto> response = controllerFactory.update(usagerBusiness, request, FunctionalityEnum.UPDATE_USAGER);
		log.info("end method /usager/update");
        return response;
    }

	@RequestMapping(value="/delete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> delete(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /usager/delete");
        Response<UsagerDto> response = controllerFactory.delete(usagerBusiness, request, FunctionalityEnum.DELETE_USAGER);
		log.info("end method /usager/delete");
        return response;
    }

	@RequestMapping(value="/forceDelete",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> forceDelete(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /usager/forceDelete");
        Response<UsagerDto> response = controllerFactory.forceDelete(usagerBusiness, request, FunctionalityEnum.DELETE_USAGER);
		log.info("end method /usager/forceDelete");
        return response;
    }

	@RequestMapping(value="/getByCriteria",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> getByCriteria(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /usager/getByCriteria");
        Response<UsagerDto> response = controllerFactory.getByCriteria(usagerBusiness, request, FunctionalityEnum.VIEW_USAGER);
		log.info("end method /usager/getByCriteria");
        return response;
    }

	@RequestMapping(value="/changePassword",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> changePassword(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /user/changePassword");
        Response<UsagerDto> response = controllerFactory.changePassword(usagerBusiness, request, FunctionalityEnum.VIEW_USAGER);
		log.info("end method /user/changePassword");
        return response;
    }
	
	@RequestMapping(value="/resetAccount",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> resetAccount(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /user/resetAccount");
        Response<UsagerDto> response = controllerFactory.resetAccount(usagerBusiness, request, FunctionalityEnum.VIEW_USAGER);
		log.info("end method /user/resetAccount");
        return response;
    }

	@RequestMapping(value="/login",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> login(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /user/login");
        Response<UsagerDto> response = controllerFactory.login(usagerBusiness, request, FunctionalityEnum.VIEW_USAGER);
		log.info("end method /user/login");
        return response;
    }

	@RequestMapping(value="/emprunterLivre",method=RequestMethod.POST,consumes = {"application/json"},produces={"application/json"})
    public Response<UsagerDto> emprunterLivre(@RequestBody Request<UsagerDto> request) {
    	log.info("start method /user/login");
        Response<UsagerDto> response = controllerFactory.emprunterLivre(usagerBusiness, request, FunctionalityEnum.VIEW_USAGER);
		log.info("end method /user/login");
        return response;
    }
}
