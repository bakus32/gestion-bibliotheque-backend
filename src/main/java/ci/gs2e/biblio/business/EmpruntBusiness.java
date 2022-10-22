                                                    											

/*
 * Java business for entity table emprunt 
 * Created on 2022-10-22 ( Time 19:05:13 )
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
BUSINESS for table "emprunt"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class EmpruntBusiness implements IBasicBusiness<Request<EmpruntDto>, Response<EmpruntDto>> {

	private Response<EmpruntDto> response;
	@Autowired
	private EmpruntRepository empruntRepository;
	@Autowired
	private LivreRepository livreRepository;
	@Autowired
	private UsagerRepository usagerRepository;
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
    
	public EmpruntBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Emprunt by using EmpruntDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<EmpruntDto> create(Request<EmpruntDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Emprunt-----");

		Response<EmpruntDto> response = new Response<EmpruntDto>();
		List<Emprunt>        items    = new ArrayList<Emprunt>();
			
		for (EmpruntDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("livreId", dto.getLivreId());
			fieldsToVerify.put("usagerId", dto.getUsagerId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
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
			// Verify if usager exist
			Usager existingUsager = null;
			if (Utilities.isValidID(dto.getUsagerId())){
				existingUsager = usagerRepository.findOne(dto.getUsagerId(), false);
				if (existingUsager == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("usager usagerId -> " + dto.getUsagerId(), locale));
					response.setHasError(true);
					return response;
				}
			}
				Emprunt entityToSave = EmpruntTransformer.INSTANCE.toEntity(dto, existingLivre, existingUsager);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setIsDeleted(false);
			entityToSave.setCreatedBy(request.getUser());
			setIdOfEmprunt(entityToSave);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Emprunt> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = empruntRepository.saveAll((Iterable<Emprunt>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("emprunt", locale));
				response.setHasError(true);
				return response;
			}
			List<EmpruntDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? EmpruntTransformer.INSTANCE.toLiteDtos(itemsSaved) : EmpruntTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Emprunt-----");
		return response;
	}
	
	private void setIdOfEmprunt(Emprunt entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<Emprunt> empruntLit = empruntRepository.findAll(pageable);

		if (empruntLit != null && Utilities.isNotEmpty(empruntLit.toList())) {
			entityToSave.setId(empruntLit.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update Emprunt by using EmpruntDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<EmpruntDto> update(Request<EmpruntDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Emprunt-----");

		Response<EmpruntDto> response = new Response<EmpruntDto>();
		List<Emprunt>        items    = new ArrayList<Emprunt>();
			
		for (EmpruntDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la emprunt existe
			Emprunt entityToSave = null;
			entityToSave = empruntRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("emprunt id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

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
			// Verify if usager exist
			if (Utilities.isValidID(dto.getUsagerId()) && !entityToSave.getUsager().getId().equals(dto.getUsagerId())){
				Usager existingUsager = usagerRepository.findOne(dto.getUsagerId(), false);
				if (existingUsager == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("usager usagerId -> " + dto.getUsagerId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setUsager(existingUsager);
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
			List<Emprunt> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = empruntRepository.saveAll((Iterable<Emprunt>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("emprunt", locale));
				response.setHasError(true);
				return response;
			}
			List<EmpruntDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? EmpruntTransformer.INSTANCE.toLiteDtos(itemsSaved) : EmpruntTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Emprunt-----");
		return response;
	}

	/**
	 * delete Emprunt by using EmpruntDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<EmpruntDto> delete(Request<EmpruntDto> request, Locale locale)  {
		log.info("----begin delete Emprunt-----");

		Response<EmpruntDto> response = new Response<EmpruntDto>();
		List<Emprunt>        items    = new ArrayList<Emprunt>();
			
		for (EmpruntDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la emprunt existe
			Emprunt existingEntity = null;
			existingEntity = empruntRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("emprunt -> " + dto.getId(), locale));
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
			empruntRepository.saveAll((Iterable<Emprunt>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete Emprunt-----");
		return response;
	}

	/**
	 * forceDelete Emprunt by using EmpruntDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<EmpruntDto> forceDelete(Request<EmpruntDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete Emprunt-----");

		Response<EmpruntDto> response = new Response<EmpruntDto>();
		List<Emprunt>        items    = new ArrayList<Emprunt>();
			
		for (EmpruntDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la emprunt existe
			Emprunt existingEntity = null;
			existingEntity = empruntRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("emprunt -> " + dto.getId(), locale));
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
			empruntRepository.saveAll((Iterable<Emprunt>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete Emprunt-----");
		return response;
	}

	/**
	 * get Emprunt by using EmpruntDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<EmpruntDto> getByCriteria(Request<EmpruntDto> request, Locale locale)  throws Exception {
		log.info("----begin get Emprunt-----");

		Response<EmpruntDto> response = new Response<EmpruntDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<Emprunt> items 			 = empruntRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("emprunt", locale));
			response.setHasError(false);
			return response;
		}

		List<EmpruntDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? EmpruntTransformer.INSTANCE.toLiteDtos(items) : EmpruntTransformer.INSTANCE.toDtos(items);

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
//			response.setCount(empruntRepository.count(request, em, locale));
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get Emprunt-----");
		return response;
	}

	/**
	 * get full EmpruntDto by using Emprunt as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private EmpruntDto getFullInfos(EmpruntDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
