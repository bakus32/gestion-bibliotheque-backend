                                                        												

/*
 * Java business for entity table fonctionnalite 
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
import ci.gs2e.biblio.dao.entity.*;
import ci.gs2e.biblio.dao.repository.*;

/**
BUSINESS for table "fonctionnalite"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class FonctionnaliteBusiness implements IBasicBusiness<Request<FonctionnaliteDto>, Response<FonctionnaliteDto>> {

	private Response<FonctionnaliteDto> response;
	@Autowired
	private FonctionnaliteRepository fonctionnaliteRepository;
	@Autowired
	private RoleFonctionnaliteRepository roleFonctionnaliteRepository;
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
    private RoleFonctionnaliteBusiness roleFonctionnaliteBusiness;
  
    
          @Autowired
    private UsersBusiness userBusiness;
    
	public FonctionnaliteBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FonctionnaliteDto> create(Request<FonctionnaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Fonctionnalite-----");

		Response<FonctionnaliteDto> response = new Response<FonctionnaliteDto>();
		List<Fonctionnalite>        items    = new ArrayList<Fonctionnalite>();
			
		for (FonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
//			fieldsToVerify.put("parentId", dto.getParentId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if fonctionnalite to insert do not exist
			Fonctionnalite existingEntity = null;

			// verif unique libelle in db
			existingEntity = fonctionnaliteRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("fonctionnalite libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

				Fonctionnalite entityToSave = FonctionnaliteTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setCode(String.format("FONC-%s", Utilities.generateCode()));
			
			setIdOfFonctionalite(entityToSave);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Fonctionnalite> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = fonctionnaliteRepository.saveAll((Iterable<Fonctionnalite>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("fonctionnalite", locale));
				response.setHasError(true);
				return response;
			}
			List<FonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : FonctionnaliteTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Fonctionnalite-----");
		return response;
	}
	
	private void setIdOfFonctionalite(Fonctionnalite entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<Fonctionnalite> fonctionaliteList = fonctionnaliteRepository.findAll(pageable);

		if (fonctionaliteList != null && Utilities.isNotEmpty(fonctionaliteList.toList())) {
			entityToSave.setId(fonctionaliteList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FonctionnaliteDto> update(Request<FonctionnaliteDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Fonctionnalite-----");

		Response<FonctionnaliteDto> response = new Response<FonctionnaliteDto>();
		List<Fonctionnalite>        items    = new ArrayList<Fonctionnalite>();
			
		for (FonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la fonctionnalite existe
			Fonctionnalite entityToSave = null;
			entityToSave = fonctionnaliteRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

			if (Utilities.isNotBlank(dto.getLibelle()) && !dto.getLibelle().equals(entityToSave.getLibelle())) {
			                 Fonctionnalite existingEntity = fonctionnaliteRepository.findByLibelle(dto.getLibelle(), false);
                if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
                  response.setStatus(functionalError.DATA_EXIST("fonctionnalite -> " + dto.getLibelle(), locale));
                  response.setHasError(true);
                  return response;
                }
                if (items.stream().anyMatch(a->a.getLibelle().equalsIgnoreCase(dto.getLibelle()) && !a.getId().equals(entityToSaveId))) {
                  response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du libelle '" + dto.getLibelle()+"' pour les fonctionnalites", locale));
                  response.setHasError(true);
                  return response;
                }
       						entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.isNotBlank(dto.getCode()) && !dto.getCode().equals(entityToSave.getCode())) {
			                 Fonctionnalite existingEntity = fonctionnaliteRepository.findByCode(dto.getCode(), false);
                if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
                  response.setStatus(functionalError.DATA_EXIST("fonctionnalite -> " + dto.getCode(), locale));
                  response.setHasError(true);
                  return response;
                }
                if (items.stream().anyMatch(a->a.getCode().equalsIgnoreCase(dto.getCode()) && !a.getId().equals(entityToSaveId))) {
                  response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du code '" + dto.getCode()+"' pour les fonctionnalites", locale));
                  response.setHasError(true);
                  return response;
                }
       						entityToSave.setCode(dto.getCode());
			}
			if (dto.getParentId() != null && dto.getParentId() > 0) {
				entityToSave.setParentId(dto.getParentId());
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
			List<Fonctionnalite> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = fonctionnaliteRepository.saveAll((Iterable<Fonctionnalite>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("fonctionnalite", locale));
				response.setHasError(true);
				return response;
			}
			List<FonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionnaliteTransformer.INSTANCE.toLiteDtos(itemsSaved) : FonctionnaliteTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Fonctionnalite-----");
		return response;
	}

	/**
	 * delete Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FonctionnaliteDto> delete(Request<FonctionnaliteDto> request, Locale locale)  {
		log.info("----begin delete Fonctionnalite-----");

		Response<FonctionnaliteDto> response = new Response<FonctionnaliteDto>();
		List<Fonctionnalite>        items    = new ArrayList<Fonctionnalite>();
			
		for (FonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la fonctionnalite existe
			Fonctionnalite existingEntity = null;
			existingEntity = fonctionnaliteRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// roleFonctionnalite
			List<RoleFonctionnalite> listOfRoleFonctionnalite = roleFonctionnaliteRepository.findByFonctionnaliteId(existingEntity.getId(), false);
			if (listOfRoleFonctionnalite != null && !listOfRoleFonctionnalite.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfRoleFonctionnalite.size() + ")", locale));
				response.setHasError(true);
				return response;
			}


			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			fonctionnaliteRepository.saveAll((Iterable<Fonctionnalite>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete Fonctionnalite-----");
		return response;
	}

	/**
	 * forceDelete Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FonctionnaliteDto> forceDelete(Request<FonctionnaliteDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete Fonctionnalite-----");

		Response<FonctionnaliteDto> response = new Response<FonctionnaliteDto>();
		List<Fonctionnalite>        items    = new ArrayList<Fonctionnalite>();
			
		for (FonctionnaliteDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la fonctionnalite existe
			Fonctionnalite existingEntity = null;
			existingEntity = fonctionnaliteRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("fonctionnalite -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

		// roleFonctionnalite
        List<RoleFonctionnalite> listOfRoleFonctionnalite = roleFonctionnaliteRepository.findByFonctionnaliteId(existingEntity.getId(), false);
        if (listOfRoleFonctionnalite != null && !listOfRoleFonctionnalite.isEmpty()){
          Request<RoleFonctionnaliteDto> deleteRequest = new Request<RoleFonctionnaliteDto>();
          deleteRequest.setDatas(RoleFonctionnaliteTransformer.INSTANCE.toDtos(listOfRoleFonctionnalite));
          deleteRequest.setUser(request.getUser());
          Response<RoleFonctionnaliteDto> deleteResponse = roleFonctionnaliteBusiness.delete(deleteRequest, locale);
          if(deleteResponse.isHasError()){
            response.setStatus(deleteResponse.getStatus());
            response.setHasError(true);
            return response;
          }
        }


			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			fonctionnaliteRepository.saveAll((Iterable<Fonctionnalite>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete Fonctionnalite-----");
		return response;
	}

	/**
	 * get Fonctionnalite by using FonctionnaliteDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<FonctionnaliteDto> getByCriteria(Request<FonctionnaliteDto> request, Locale locale)  throws Exception {
		log.info("----begin get Fonctionnalite-----");

		Response<FonctionnaliteDto> response = new Response<FonctionnaliteDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<Fonctionnalite> items 			 = fonctionnaliteRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("fonctionnalite", locale));
			response.setHasError(false);
			return response;
		}

		List<FonctionnaliteDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? FonctionnaliteTransformer.INSTANCE.toLiteDtos(items) : FonctionnaliteTransformer.INSTANCE.toDtos(items);

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
//			response.setCount(fonctionnaliteRepository.count(request, em, locale));
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get Fonctionnalite-----");
		return response;
	}

	/**
	 * get full FonctionnaliteDto by using Fonctionnalite as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private FonctionnaliteDto getFullInfos(FonctionnaliteDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
