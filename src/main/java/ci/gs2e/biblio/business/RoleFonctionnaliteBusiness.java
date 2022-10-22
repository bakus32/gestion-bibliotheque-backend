                                                            													

/*
 * Java business for entity table role_fonctionnalite 
 * Created on 2022-10-21 ( Time 23:35:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.business;

import lombok.extern.java.Log;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import ci.gs2e.biblio.helper.*;
import ci.gs2e.biblio.helper.dto.*;
import ci.gs2e.biblio.helper.enums.*;
import ci.gs2e.biblio.helper.contrat.*;
import ci.gs2e.biblio.helper.contrat.IBasicBusiness;
import ci.gs2e.biblio.helper.contrat.Request;
import ci.gs2e.biblio.helper.contrat.Response;
import ci.gs2e.biblio.helper.dto.transformer.*;
import ci.gs2e.biblio.dao.entity.RoleFonctionnalite;
import ci.gs2e.biblio.dao.entity.Fonctionnalite;
import ci.gs2e.biblio.dao.entity.Role;
import ci.gs2e.biblio.dao.entity.*;
import ci.gs2e.biblio.dao.repository.*;

/**
BUSINESS for table "role_fonctionnalite"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class RoleFonctionnaliteBusiness implements IBasicBusiness<Request<RoleFonctionnaliteDto>, Response<RoleFonctionnaliteDto>> {

	private Response<RoleFonctionnaliteDto> response;
	@Autowired
	private RoleFonctionnaliteRepository roleFonctionnaliteRepository;
	@Autowired
	private FonctionnaliteRepository fonctionnaliteRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private FunctionalError functionalError;
	@Autowired
	private TechnicalError technicalError;
	@Autowired
	private ExceptionUtils exceptionUtils;
	@PersistenceContext
	private EntityManager em;

	private SimpleDateFormat dateFormat;
	private SimpleDateFormat dateTimeFormat;

          
          @Autowired
    private UsersBusiness userBusiness;
    
	public RoleFonctionnaliteBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create RoleFonctionnalite by using RoleFonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleFonctionnaliteDto> create(Request<RoleFonctionnaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin create RoleFonctionnalite-----");

		Response<RoleFonctionnaliteDto> response = new Response<RoleFonctionnaliteDto>();
		List<RoleFonctionnalite>        items    = new ArrayList<RoleFonctionnalite>();
			
		for (RoleFonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("roleId", dto.getRoleId());
			fieldsToVerify.put("fonctionnaliteId", dto.getFonctionnaliteId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if fonctionnalite exist
			Fonctionnalite existingFonctionnalite = null;
			if (Utilities.isValidID(dto.getFonctionnaliteId())){
				existingFonctionnalite = fonctionnaliteRepository.findOne(dto.getFonctionnaliteId(), false);
				if (existingFonctionnalite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite fonctionnaliteId -> " + dto.getFonctionnaliteId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if role exist
			Role existingRole = null;
			if (Utilities.isValidID(dto.getRoleId())){
				existingRole = roleRepository.findOne(dto.getRoleId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				RoleFonctionnalite entityToSave = RoleFonctionnaliteTransformer.INSTANCE.toEntity(dto, existingFonctionnalite, existingRole);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setIsDeleted(false);
			entityToSave.setCreatedBy(request.getUser());
			
			setIdOfRoleFonctionnalite(entityToSave);
			
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<RoleFonctionnalite> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = roleFonctionnaliteRepository.saveAll((Iterable<RoleFonctionnalite>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("roleFonctionnalite", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleFonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleFonctionnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleFonctionnaliteTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}

			response.setItems(itemsDto);
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end create RoleFonctionnalite-----");
		return response;
	}
	
	private void setIdOfRoleFonctionnalite(RoleFonctionnalite entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<RoleFonctionnalite> roleFonctionnaliteList = roleFonctionnaliteRepository.findAll(pageable);

		if (roleFonctionnaliteList != null && Utilities.isNotEmpty(roleFonctionnaliteList.toList())) {
			entityToSave.setId(roleFonctionnaliteList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update RoleFonctionnalite by using RoleFonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleFonctionnaliteDto> update(Request<RoleFonctionnaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin update RoleFonctionnalite-----");

		Response<RoleFonctionnaliteDto> response = new Response<RoleFonctionnaliteDto>();
		List<RoleFonctionnalite>        items    = new ArrayList<RoleFonctionnalite>();
			
		for (RoleFonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la roleFonctionnalite existe
			RoleFonctionnalite entityToSave = null;
			entityToSave = roleFonctionnaliteRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("roleFonctionnalite id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

			// Verify if fonctionnalite exist
			if (Utilities.isValidID(dto.getFonctionnaliteId()) && !entityToSave.getFonctionnalite().getId().equals(dto.getFonctionnaliteId())){
				Fonctionnalite existingFonctionnalite = fonctionnaliteRepository.findOne(dto.getFonctionnaliteId(), false);
				if (existingFonctionnalite == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite fonctionnaliteId -> " + dto.getFonctionnaliteId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setFonctionnalite(existingFonctionnalite);
			}
			// Verify if role exist
			if (Utilities.isValidID(dto.getRoleId()) && !entityToSave.getRole().getId().equals(dto.getRoleId())){
				Role existingRole = roleRepository.findOne(dto.getRoleId(), false);
				if (existingRole == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("role roleId -> " + dto.getRoleId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setRole(existingRole);
			}
			if (Utilities.isNotBlank(dto.getCode()) && !dto.getCode().equals(entityToSave.getCode())) {
			                 RoleFonctionnalite existingEntity = roleFonctionnaliteRepository.findByCode(dto.getCode(), false);
                if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
                  response.setStatus(functionalError.DATA_EXIST("roleFonctionnalite -> " + dto.getCode(), locale));
                  response.setHasError(true);
                  return response;
                }
                if (items.stream().anyMatch(a->a.getCode().equalsIgnoreCase(dto.getCode()) && !a.getId().equals(entityToSaveId))) {
                  response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du code '" + dto.getCode()+"' pour les roleFonctionnalites", locale));
                  response.setHasError(true);
                  return response;
                }
       						entityToSave.setCode(dto.getCode());
			}
			if (Utilities.isNotBlank(dto.getLibelle()) && !dto.getLibelle().equals(entityToSave.getLibelle())) {
			                 RoleFonctionnalite existingEntity = roleFonctionnaliteRepository.findByLibelle(dto.getLibelle(), false);
                if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
                  response.setStatus(functionalError.DATA_EXIST("roleFonctionnalite -> " + dto.getLibelle(), locale));
                  response.setHasError(true);
                  return response;
                }
                if (items.stream().anyMatch(a->a.getLibelle().equalsIgnoreCase(dto.getLibelle()) && !a.getId().equals(entityToSaveId))) {
                  response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du libelle '" + dto.getLibelle()+"' pour les roleFonctionnalites", locale));
                  response.setHasError(true);
                  return response;
                }
       						entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.notBlank(dto.getDeletedAt())) {
				entityToSave.setDeletedAt(dateFormat.parse(dto.getDeletedAt()));
			}
			if (dto.getCreatedBy() != null && dto.getCreatedBy() > 0) {
				entityToSave.setCreatedBy(dto.getCreatedBy());
			}
			if (dto.getUpdatedBy() != null && dto.getUpdatedBy() > 0) {
				entityToSave.setUpdatedBy(dto.getUpdatedBy());
			}
			if (dto.getDeletedBy() != null && dto.getDeletedBy() > 0) {
				entityToSave.setDeletedBy(dto.getDeletedBy());
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<RoleFonctionnalite> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = roleFonctionnaliteRepository.saveAll((Iterable<RoleFonctionnalite>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("roleFonctionnalite", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleFonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleFonctionnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleFonctionnaliteTransformer.INSTANCE.toDtos(itemsSaved);

			final int size = itemsSaved.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}

			response.setItems(itemsDto);
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end update RoleFonctionnalite-----");
		return response;
	}

	/**
	 * delete RoleFonctionnalite by using RoleFonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleFonctionnaliteDto> delete(Request<RoleFonctionnaliteDto> request, Locale locale)  {
		log.info("----begin delete RoleFonctionnalite-----");

		Response<RoleFonctionnaliteDto> response = new Response<RoleFonctionnaliteDto>();
		List<RoleFonctionnalite>        items    = new ArrayList<RoleFonctionnalite>();
			
		for (RoleFonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la roleFonctionnalite existe
			RoleFonctionnalite existingEntity = null;
			existingEntity = roleFonctionnaliteRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("roleFonctionnalite -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			roleFonctionnaliteRepository.saveAll((Iterable<RoleFonctionnalite>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete RoleFonctionnalite-----");
		return response;
	}

	/**
	 * forceDelete RoleFonctionnalite by using RoleFonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleFonctionnaliteDto> forceDelete(Request<RoleFonctionnaliteDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete RoleFonctionnalite-----");

		Response<RoleFonctionnaliteDto> response = new Response<RoleFonctionnaliteDto>();
		List<RoleFonctionnalite>        items    = new ArrayList<RoleFonctionnalite>();
			
		for (RoleFonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la roleFonctionnalite existe
			RoleFonctionnalite existingEntity = null;
			existingEntity = roleFonctionnaliteRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("roleFonctionnalite -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setIsDeleted(true);
			existingEntity.setDeletedBy(request.getUser());
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			roleFonctionnaliteRepository.saveAll((Iterable<RoleFonctionnalite>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete RoleFonctionnalite-----");
		return response;
	}

	/**
	 * get RoleFonctionnalite by using RoleFonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleFonctionnaliteDto> getByCriteria(Request<RoleFonctionnaliteDto> request, Locale locale)  throws Exception {
		log.info("----begin get RoleFonctionnalite-----");

		Response<RoleFonctionnaliteDto> response = new Response<RoleFonctionnaliteDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<RoleFonctionnalite> items 			 = roleFonctionnaliteRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("roleFonctionnalite", locale));
			response.setHasError(false);
			return response;
		}

		List<RoleFonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleFonctionnaliteTransformer.INSTANCE.toLiteDtos(items) : RoleFonctionnaliteTransformer.INSTANCE.toDtos(items);

			final int size = items.size();
			List<String>  listOfError      = Collections.synchronizedList(new ArrayList<String>());
			itemsDto.parallelStream().forEach(dto -> {
				try {
					dto = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
				} catch (Exception e) {
					listOfError.add(e.getMessage());
					e.printStackTrace();
				}
			});
			if (Utilities.isNotEmpty(listOfError)) {
				Object[] objArray = listOfError.stream().distinct().toArray();
				throw new RuntimeException(StringUtils.join(objArray, ", "));
			}

			response.setItems(itemsDto);
//			response.setCount(roleFonctionnaliteRepository.count(request, em, locale));
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get RoleFonctionnalite-----");
		return response;
	}

	/**
	 * get full RoleFonctionnaliteDto by using RoleFonctionnalite as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RoleFonctionnaliteDto getFullInfos(RoleFonctionnaliteDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}
