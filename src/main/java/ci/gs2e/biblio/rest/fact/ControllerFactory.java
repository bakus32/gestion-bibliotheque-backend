
/*
 * Created on 2022-10-21 ( Time 23:35:31 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017  Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.rest.fact;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.CannotCreateTransactionException;
import org.springframework.transaction.TransactionSystemException;

import ci.gs2e.biblio.business.fact.BusinessFactory;
import ci.gs2e.biblio.business.fact.IUsagerBusiness;
import ci.gs2e.biblio.business.fact.IUserBusiness;
import ci.gs2e.biblio.helper.ExceptionUtils;
import ci.gs2e.biblio.helper.FunctionalError;
import ci.gs2e.biblio.helper.StatusCode;
import ci.gs2e.biblio.helper.StatusMessage;
import ci.gs2e.biblio.helper.Validate;
import ci.gs2e.biblio.helper.contrat.IBasicBusiness;
import ci.gs2e.biblio.helper.contrat.IController;
import ci.gs2e.biblio.helper.contrat.Request;
import ci.gs2e.biblio.helper.contrat.Response;
import ci.gs2e.biblio.helper.enums.FunctionalityEnum;
import lombok.extern.java.Log;

@Log
@Component
public class ControllerFactory<DTO> implements IController<DTO> {
    @Autowired
    private BusinessFactory<DTO> businessFactory;
    @Autowired
    private FunctionalError      functionalError;
    @Autowired
    private ExceptionUtils       exceptionUtils;
    @Autowired
    private HttpServletRequest   requestBasic;

    @Override
    public Response<DTO> create(IBasicBusiness iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum) {
        Response<DTO> response   = new Response<DTO>();
        String        languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale        locale     = new Locale(languageID, "");
        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
        	}

            response = businessFactory.create(iBasicBusiness, request, functionalityEnum, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
            }

			//response.setStatus(functionalError.SUCCESS("", locale));
          	log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));

        } catch (CannotCreateTransactionException e) {
            exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
        } catch (TransactionSystemException e) {
            exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        }
        return response;
    }

    @Override
    public Response<DTO> update(IBasicBusiness iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum) {
        Response<DTO> response   = new Response<DTO>();
        String        languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale        locale     = new Locale(languageID, "");
        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
        	}

            response = businessFactory.update(iBasicBusiness, request, functionalityEnum, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
            }

			//response.setStatus(functionalError.SUCCESS("", locale));
          	log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));

        } catch (CannotCreateTransactionException e) {
            exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
        } catch (TransactionSystemException e) {
            exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        }
        return response;
    }

    @Override
    public Response<DTO> delete(IBasicBusiness iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum) {
        Response<DTO> response   = new Response<DTO>();
        String        languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale        locale     = new Locale(languageID, "");
        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
        	}

            response = businessFactory.delete(iBasicBusiness, request, functionalityEnum, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
            }

			//response.setStatus(functionalError.SUCCESS("", locale));
          	log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));

        } catch (CannotCreateTransactionException e) {
            exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
        } catch (TransactionSystemException e) {
            exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        }
        return response;
    }

    public Response<DTO> forceDelete(IBasicBusiness iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum) {
        Response<DTO> response   = new Response<DTO>();
        String        languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale        locale     = new Locale(languageID, "");
        try {

        	response = Validate.validateList(request, response, functionalError, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
        	}

            response = businessFactory.forceDelete(iBasicBusiness, request, functionalityEnum, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
            }

			//response.setStatus(functionalError.SUCCESS("", locale));
          	log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));

        } catch (CannotCreateTransactionException e) {
            exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
        } catch (TransactionSystemException e) {
            exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        }
        return response;
    }

    @Override
    public Response<DTO> getByCriteria(IBasicBusiness iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum) {
        Response<DTO> response   = new Response<DTO>();
        String        languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
        Locale        locale     = new Locale(languageID, "");
        try {

        	response = Validate.validateObject(request, response, functionalError, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
        	}

            response = businessFactory.getByCriteria(iBasicBusiness, request, functionalityEnum, locale);
        	if(response.isHasError()){
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
        	    return response;
            }

			//response.setStatus(functionalError.SUCCESS("", locale));
          	log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));

        } catch (CannotCreateTransactionException e) {
            exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
        } catch (TransactionSystemException e) {
            exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        }
        return response;
    }
    
    public Response<DTO> changePassword(IUserBusiness iBasicBusiness, Request<DTO> request,
			FunctionalityEnum functionalityEnum) {
		Response<DTO> response = new Response<DTO>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = businessFactory.changePassword(iBasicBusiness, request, functionalityEnum, locale);
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				return response;
			}
			if (!response.isHasError()) {
				log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		return response;
	}

	public Response<DTO> resetAccount(IUserBusiness iBasicBusiness, Request<DTO> request,
			FunctionalityEnum functionalityEnum) {
		Response<DTO> response = new Response<DTO>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = businessFactory.resetAccount(iBasicBusiness, request, functionalityEnum, locale);
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				return response;
			}
			if (!response.isHasError()) {
				log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		return response;
	}

	public Response<DTO> login(IUserBusiness iBasicBusiness, Request<DTO> request,
			FunctionalityEnum functionalityEnum) {
		Response<DTO> response = new Response<DTO>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = businessFactory.login(iBasicBusiness, request, functionalityEnum, locale);
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				return response;
			}
			if (!response.isHasError()) {
				log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		return response;
	}



	public Response<DTO> emprunterLivre(IUsagerBusiness iBasicBusiness, Request<DTO> request,
			FunctionalityEnum functionalityEnum) {
		Response<DTO> response = new Response<DTO>();
		String languageID = (String) requestBasic.getAttribute("CURRENT_LANGUAGE_IDENTIFIER");
		Locale locale = new Locale(languageID, "");
		try {
			response = Validate.validateObject(request, response, functionalError, locale);
			if (!response.isHasError()) {
				response = businessFactory.emprunterLivre(iBasicBusiness, request, functionalityEnum, locale);
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				return response;
			}
			if (!response.isHasError()) {
				log.info(String.format("code: {} -  message: {}", StatusCode.SUCCESS, StatusMessage.SUCCESS));
			} else {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
			}
		} catch (CannotCreateTransactionException e) {
			exceptionUtils.CANNOT_CREATE_TRANSACTION_EXCEPTION(response, locale, e);
		} catch (TransactionSystemException e) {
			exceptionUtils.TRANSACTION_SYSTEM_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		}
		return response;
	}
}
