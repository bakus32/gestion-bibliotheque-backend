                                                        												

/*
 * Java business for entity table livre_auteur 
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
BUSINESS for table "livre_auteur"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class LivreAuteurBusiness implements IBasicBusiness<Request<LivreAuteurDto>, Response<LivreAuteurDto>> {

	private Response<LivreAuteurDto> response;
	@Autowired
	private LivreAuteurRepository livreAuteurRepository;
	@Autowired
	private AuteurRepository auteurRepository;
	@Autowired
	private LivreRepository livreRepository;
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
    
	public LivreAuteurBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create LivreAuteur by using LivreAuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreAuteurDto> create(Request<LivreAuteurDto> request, Locale locale)  throws ParseException {
		log.info("----begin create LivreAuteur-----");

		Response<LivreAuteurDto> response = new Response<LivreAuteurDto>();
		List<LivreAuteur>        items    = new ArrayList<LivreAuteur>();
			
		for (LivreAuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("livreId", dto.getLivreId());
			fieldsToVerify.put("auteurId", dto.getAuteurId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if auteur exist
			Auteur existingAuteur = null;
			if (Utilities.isValidID(dto.getAuteurId())){
				existingAuteur = auteurRepository.findOne(dto.getAuteurId(), false);
				if (existingAuteur == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("auteur auteurId -> " + dto.getAuteurId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			// Verify if livre exist
			Livre existingLivre = null;
			if (Utilities.isValidID(dto.getLivreId())){
				existingLivre = livreRepository.findOne(dto.getLivreId(), false);
				if (existingLivre == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("livre livreId -> " + dto.getLivreId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				LivreAuteur entityToSave = LivreAuteurTransformer.INSTANCE.toEntity(dto, existingAuteur, existingLivre);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setCode(String.format("LIV-AUT-%s", Utilities.generateCode()));
			
			setIdOfLivreAuteur(entityToSave);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<LivreAuteur> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = livreAuteurRepository.saveAll((Iterable<LivreAuteur>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("livreAuteur", locale));
				response.setHasError(true);
				return response;
			}
			List<LivreAuteurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LivreAuteurTransformer.INSTANCE.toLiteDtos(itemsSaved) : LivreAuteurTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create LivreAuteur-----");
		return response;
	}
	
	private void setIdOfLivreAuteur(LivreAuteur entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<LivreAuteur> livreAuteurList = livreAuteurRepository.findAll(pageable);

		if (livreAuteurList != null && Utilities.isNotEmpty(livreAuteurList.toList())) {
			entityToSave.setId(livreAuteurList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update LivreAuteur by using LivreAuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreAuteurDto> update(Request<LivreAuteurDto> request, Locale locale)  throws ParseException {
		log.info("----begin update LivreAuteur-----");

		Response<LivreAuteurDto> response = new Response<LivreAuteurDto>();
		List<LivreAuteur>        items    = new ArrayList<LivreAuteur>();
			
		for (LivreAuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la livreAuteur existe
			LivreAuteur entityToSave = null;
			entityToSave = livreAuteurRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("livreAuteur id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

			// Verify if auteur exist
			if (Utilities.isValidID(dto.getAuteurId()) && !entityToSave.getAuteur().getId().equals(dto.getAuteurId())){
				Auteur existingAuteur = auteurRepository.findOne(dto.getAuteurId(), false);
				if (existingAuteur == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("auteur auteurId -> " + dto.getAuteurId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setAuteur(existingAuteur);
			}
			// Verify if livre exist
			if (Utilities.isValidID(dto.getLivreId()) && !entityToSave.getLivre().getId().equals(dto.getLivreId())){
				Livre existingLivre = livreRepository.findOne(dto.getLivreId(), false);
				if (existingLivre == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("livre livreId -> " + dto.getLivreId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLivre(existingLivre);
			}
			if (Utilities.isNotBlank(dto.getCode()) && !dto.getCode().equals(entityToSave.getCode())) {
			                 LivreAuteur existingEntity = livreAuteurRepository.findByCode(dto.getCode(), false);
                if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
                  response.setStatus(functionalError.DATA_EXIST("livreAuteur -> " + dto.getCode(), locale));
                  response.setHasError(true);
                  return response;
                }
                if (items.stream().anyMatch(a->a.getCode().equalsIgnoreCase(dto.getCode()) && !a.getId().equals(entityToSaveId))) {
                  response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du code '" + dto.getCode()+"' pour les livreAuteurs", locale));
                  response.setHasError(true);
                  return response;
                }
       						entityToSave.setCode(dto.getCode());
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
			List<LivreAuteur> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = livreAuteurRepository.saveAll((Iterable<LivreAuteur>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("livreAuteur", locale));
				response.setHasError(true);
				return response;
			}
			List<LivreAuteurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LivreAuteurTransformer.INSTANCE.toLiteDtos(itemsSaved) : LivreAuteurTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update LivreAuteur-----");
		return response;
	}

	/**
	 * delete LivreAuteur by using LivreAuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreAuteurDto> delete(Request<LivreAuteurDto> request, Locale locale)  {
		log.info("----begin delete LivreAuteur-----");

		Response<LivreAuteurDto> response = new Response<LivreAuteurDto>();
		List<LivreAuteur>        items    = new ArrayList<LivreAuteur>();
			
		for (LivreAuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la livreAuteur existe
			LivreAuteur existingEntity = null;
			existingEntity = livreAuteurRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("livreAuteur -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			livreAuteurRepository.saveAll((Iterable<LivreAuteur>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete LivreAuteur-----");
		return response;
	}

	/**
	 * forceDelete LivreAuteur by using LivreAuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreAuteurDto> forceDelete(Request<LivreAuteurDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete LivreAuteur-----");

		Response<LivreAuteurDto> response = new Response<LivreAuteurDto>();
		List<LivreAuteur>        items    = new ArrayList<LivreAuteur>();
			
		for (LivreAuteurDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la livreAuteur existe
			LivreAuteur existingEntity = null;
			existingEntity = livreAuteurRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("livreAuteur -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------



			existingEntity.setDeletedAt(Utilities.getCurrentDate());
			existingEntity.setDeletedBy(request.getUser());
			existingEntity.setIsDeleted(true);
			items.add(existingEntity);
		}

		if (!items.isEmpty()) {
			// supprimer les donnees en base
			livreAuteurRepository.saveAll((Iterable<LivreAuteur>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete LivreAuteur-----");
		return response;
	}

	/**
	 * get LivreAuteur by using LivreAuteurDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreAuteurDto> getByCriteria(Request<LivreAuteurDto> request, Locale locale)  throws Exception {
		log.info("----begin get LivreAuteur-----");

		Response<LivreAuteurDto> response = new Response<LivreAuteurDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<LivreAuteur> items 			 = livreAuteurRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("livreAuteur", locale));
			response.setHasError(false);
			return response;
		}

		List<LivreAuteurDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LivreAuteurTransformer.INSTANCE.toLiteDtos(items) : LivreAuteurTransformer.INSTANCE.toDtos(items);

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
//			response.setCount(livreAuteurRepository.count(request, em, locale));
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get LivreAuteur-----");
		return response;
	}

	/**
	 * get full LivreAuteurDto by using LivreAuteur as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private LivreAuteurDto getFullInfos(LivreAuteurDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
