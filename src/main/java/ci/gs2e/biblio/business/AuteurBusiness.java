                                                        												

/*
 * Java business for entity table auteur 
 * Created on 2022-10-21 ( Time 23:35:48 )
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
BUSINESS for table "auteur"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class AuteurBusiness implements IBasicBusiness<Request<AuteurDto>, Response<AuteurDto>> {

	private Response<AuteurDto> response;
	@Autowired
	private AuteurRepository auteurRepository;
	@Autowired
	private LivreAuteurRepository livreAuteurRepository;
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
    private LivreAuteurBusiness livreAuteurBusiness;
  
    
          @Autowired
    private UsersBusiness userBusiness;
    
	public AuteurBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Auteur by using AuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AuteurDto> create(Request<AuteurDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Auteur-----");

		Response<AuteurDto> response = new Response<AuteurDto>();
		List<Auteur>        items    = new ArrayList<Auteur>();
			
		for (AuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("nom", dto.getNom());
			fieldsToVerify.put("prenoms", dto.getPrenoms());
			fieldsToVerify.put("specialite", dto.getSpecialite());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

				Auteur entityToSave = AuteurTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			
			setIdOfAuteur(entityToSave);
			
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Auteur> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = auteurRepository.saveAll((Iterable<Auteur>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("auteur", locale));
				response.setHasError(true);
				return response;
			}
			List<AuteurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AuteurTransformer.INSTANCE.toLiteDtos(itemsSaved) : AuteurTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Auteur-----");
		return response;
	}
	
	private void setIdOfAuteur(Auteur entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<Auteur> auteurList = auteurRepository.findAll(pageable);

		if (auteurList != null && Utilities.isNotEmpty(auteurList.toList())) {
			entityToSave.setId(auteurList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update Auteur by using AuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AuteurDto> update(Request<AuteurDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Auteur-----");

		Response<AuteurDto> response = new Response<AuteurDto>();
		List<Auteur>        items    = new ArrayList<Auteur>();
			
		for (AuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la auteur existe
			Auteur entityToSave = null;
			entityToSave = auteurRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("auteur id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

			if (Utilities.isNotBlank(dto.getNom()) && !dto.getNom().equals(entityToSave.getNom())) {
			 				entityToSave.setNom(dto.getNom());
			}
			if (Utilities.isNotBlank(dto.getPrenoms()) && !dto.getPrenoms().equals(entityToSave.getPrenoms())) {
			 				entityToSave.setPrenoms(dto.getPrenoms());
			}
			if (Utilities.isNotBlank(dto.getSpecialite()) && !dto.getSpecialite().equals(entityToSave.getSpecialite())) {
			 				entityToSave.setSpecialite(dto.getSpecialite());
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
			List<Auteur> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = auteurRepository.saveAll((Iterable<Auteur>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("auteur", locale));
				response.setHasError(true);
				return response;
			}
			List<AuteurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AuteurTransformer.INSTANCE.toLiteDtos(itemsSaved) : AuteurTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Auteur-----");
		return response;
	}

	/**
	 * delete Auteur by using AuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AuteurDto> delete(Request<AuteurDto> request, Locale locale)  {
		log.info("----begin delete Auteur-----");

		Response<AuteurDto> response = new Response<AuteurDto>();
		List<Auteur>        items    = new ArrayList<Auteur>();
			
		for (AuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la auteur existe
			Auteur existingEntity = null;
			existingEntity = auteurRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("auteur -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// livreAuteur
			List<LivreAuteur> listOfLivreAuteur = livreAuteurRepository.findByAuteurId(existingEntity.getId(), false);
			if (listOfLivreAuteur != null && !listOfLivreAuteur.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfLivreAuteur.size() + ")", locale));
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
			auteurRepository.saveAll((Iterable<Auteur>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete Auteur-----");
		return response;
	}

	/**
	 * forceDelete Auteur by using AuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AuteurDto> forceDelete(Request<AuteurDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete Auteur-----");

		Response<AuteurDto> response = new Response<AuteurDto>();
		List<Auteur>        items    = new ArrayList<Auteur>();
			
		for (AuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la auteur existe
			Auteur existingEntity = null;
			existingEntity = auteurRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("auteur -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

		// livreAuteur
        List<LivreAuteur> listOfLivreAuteur = livreAuteurRepository.findByAuteurId(existingEntity.getId(), false);
        if (listOfLivreAuteur != null && !listOfLivreAuteur.isEmpty()){
          Request<LivreAuteurDto> deleteRequest = new Request<LivreAuteurDto>();
          deleteRequest.setDatas(LivreAuteurTransformer.INSTANCE.toDtos(listOfLivreAuteur));
          deleteRequest.setUser(request.getUser());
          Response<LivreAuteurDto> deleteResponse = livreAuteurBusiness.delete(deleteRequest, locale);
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
			auteurRepository.saveAll((Iterable<Auteur>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete Auteur-----");
		return response;
	}

	/**
	 * get Auteur by using AuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<AuteurDto> getByCriteria(Request<AuteurDto> request, Locale locale)  throws Exception {
		log.info("----begin get Auteur-----");

		Response<AuteurDto> response = new Response<AuteurDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<Auteur> items 			 = auteurRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("auteur", locale));
			response.setHasError(false);
			return response;
		}

		List<AuteurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? AuteurTransformer.INSTANCE.toLiteDtos(items) : AuteurTransformer.INSTANCE.toDtos(items);

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
//			response.setCount(auteurRepository.count(request, em, locale));
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get Auteur-----");
		return response;
	}

	/**
	 * get full AuteurDto by using Auteur as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private AuteurDto getFullInfos(AuteurDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
