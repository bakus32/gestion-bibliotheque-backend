

/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017  Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.business.fact;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import ci.gs2e.biblio.dao.entity.Users;
import ci.gs2e.biblio.dao.repository.UsersRepository;
import ci.gs2e.biblio.helper.ExceptionUtils;
import ci.gs2e.biblio.helper.FunctionalError;
import ci.gs2e.biblio.helper.Utilities;
import ci.gs2e.biblio.helper.Validate;
import ci.gs2e.biblio.helper.contrat.IBasicBusiness;
import ci.gs2e.biblio.helper.contrat.Request;
import ci.gs2e.biblio.helper.contrat.Response;
import ci.gs2e.biblio.helper.dto.UsersDto;
import ci.gs2e.biblio.helper.enums.FunctionalityEnum;
import lombok.extern.java.Log;


/**
 * BUSINESS factory
 *
 * @author Smile Backend generator
 */
@Log
@Component
public class BusinessFactory<DTO> {

	@Autowired
	private UsersRepository userRepository;
    @Autowired
    private FunctionalError functionalError;
    @Autowired
    private ExceptionUtils  exceptionUtils;

    /**
     * create entity by using dto as object.
     *
     * @param request
     * @return response
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Response<DTO> create(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
        Response<DTO> response = new Response<DTO>();
        try {
            checkUserAccess(request, functionalityEnum, locale);
            response = iBasicBusiness.create(request, locale);
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        return response;
    }

    /**
     * update entity by using dto as object.
     *
     * @param request
     * @return response
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Response<DTO> update(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
        Response<DTO> response = new Response<DTO>();
        try {
            checkUserAccess(request, functionalityEnum, locale);
            response = iBasicBusiness.update(request, locale);
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        return response;
    }

    /**
     * delete entity by using dto as object.
     *
     * @param request
     * @return response
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Response<DTO> delete(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
        Response<DTO> response = new Response<DTO>();
        try {
            checkUserAccess(request, functionalityEnum, locale);
            response = iBasicBusiness.delete(request, locale);
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        return response;
    }

    /**
     * delete entity by using dto as object.
     *
     * @param request
     * @return response
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Response<DTO> forceDelete(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
        Response<DTO> response = new Response<DTO>();
        try {
            checkUserAccess(request, functionalityEnum, locale);
            response = iBasicBusiness.forceDelete(request, locale);
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        return response;
    }

    /**
     * search entities by Ccriteria using dto as search object.
     *
     * @param request
     * @return response
     */
    @Transactional(rollbackFor = {RuntimeException.class, Exception.class})
    public Response<DTO> getByCriteria(IBasicBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
        Response<DTO> response = new Response<DTO>();
        try {
            checkUserAccess(request, functionalityEnum, locale);
            response = iBasicBusiness.getByCriteria(request, locale);
        } catch (PermissionDeniedDataAccessException e) {
            exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (DataAccessResourceFailureException e) {
            exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
        } catch (DataAccessException e) {
            exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
        } catch (RuntimeException e) {
            exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
        } catch (Exception e) {
            exceptionUtils.EXCEPTION(response, locale, e);
        } finally {
            if (response.isHasError() && response.getStatus() != null) {
                log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
                throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
            }
        }
        return response;
    }
    
    @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<DTO> resetAccount(IUserBusiness<Request<DTO>, Response<DTO>> iBasicBusiness,
			Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
		Response<DTO> response = new Response<DTO>();
		try {
			// checkUserAccess(request, functionalityEnum, locale);
			response = iBasicBusiness.resetAccount(request, locale);
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<DTO> changePassword(IUserBusiness<Request<DTO>, Response<DTO>> iBasicBusiness,
			Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
		Response<DTO> response = new Response<DTO>();
		try {
			// checkUserAccess(request, functionalityEnum, locale);
			response = iBasicBusiness.changePassword(request, locale);
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<DTO> login(IUserBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request,
			FunctionalityEnum functionalityEnum, Locale locale) {
		Response<DTO> response = new Response<DTO>();
		try {
			// checkUserAccess(request, functionalityEnum, locale);
			response = iBasicBusiness.login(request, locale);
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}
	
	@Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<DTO> emprunterLivre(IUsagerBusiness<Request<DTO>, Response<DTO>> iBasicBusiness, Request<DTO> request,
			FunctionalityEnum functionalityEnum, Locale locale) {
		Response<DTO> response = new Response<DTO>();
		try {
			response = iBasicBusiness.emprunterLivre(request, locale);
		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(),
						response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}


    private void checkUserAccess(Request<DTO> request, FunctionalityEnum functionalityEnum, Locale locale) {
        Map<String, java.lang.Object> fieldsToVerifyUser = new HashMap<String, java.lang.Object>();
        fieldsToVerifyUser.put("user", request.getUser());
        if (!Validate.RequiredValue(fieldsToVerifyUser).isGood()) {
            throw new RuntimeException(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale).getMessage());
        }

        Response<UsersDto> userResponse = isGranted(request.getUser(), functionalityEnum.getValue(), locale);
        if (userResponse.isHasError()) {
            throw new RuntimeException(userResponse.getStatus().getMessage());
        }
    }

	/**
	 * 
	 * @param userId
	 * @param functionalityCode
	 * @param locale
	 * @return
	 */
    private Response<UsersDto> isGranted(Integer userId, String functionalityCode, Locale locale){
		log.info("----begin get isGranted-----");

		Response<UsersDto> response = new Response<UsersDto>();

		try {
			Users currentUser = userRepository.findOne(userId, false);
			if (currentUser == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("Utilisateur -> " + userId, locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.isTrue(currentUser.getIsLocked())) {
				response.setStatus(functionalError.REQUEST_FAIL("L'utilisateur "+currentUser.getLogin()+" est verouille(e)" , locale));
				response.setHasError(true);
				return response;
 			}

    		if (Utilities.isTrue(currentUser.getIsConnected())) {
    			response.setStatus(functionalError.USER_NOT_CONNECTED("L'utilisateur "+currentUser.getPrenoms()+" "+currentUser.getNom()+" n'est pas connceté" , locale));
    			response.setHasError(true);
    			return response;
    		}

//   			if(Utilities.isNotBlank(functionalityCode) && Utilities.areNotEquals(FunctionalityEnum.DEFAULT.getValue(), functionalityCode)) {
//   				Functionality functionality = roleFunctionalityRepository.isGranted(utilisateur.getRole().getId(), functionalityCode ,false);
//   				if (functionality == null) {
//   					response.setHasError(true);
//    				response.setStatus(functionalError.USER_NOT_GRANTED("", locale));
//    				return response;
//    			}
//    		}
    		
//			if (Utilities.isFalse(currentUser.getIsSuperAdmin()) && Utilities.areNotEquals(FunctionalityEnum.DEFAULT.getValue(), functionalityCode)) {
//				if (Utilities.notBlank(functionalityCode)) {
//					Functionality functionality = roleFunctionalityRepository.isGranted(currentUser.getRole().getId(), functionalityCode ,false);
//					if (functionality == null) {
//						response.setHasError(true);
//						response.setStatus(functionalError.USER_NOT_GRANTED("", locale));
//						return response;
//					}
//				}
//			}

			response.setHasError(false);
			log.info("----end get isGranted-----");

		} catch (PermissionDeniedDataAccessException e) {
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info(String.format("Erreur| code: {} -  message: {}", response.getStatus().getCode(), response.getStatus().getMessage()));
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}

		return response;
	}

  }
