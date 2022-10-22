

/*
 * Java business for entity table categorie_livre 
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
import ci.gs2e.biblio.helper.Validate;
import ci.gs2e.biblio.dao.entity.CategorieLivre;
import ci.gs2e.biblio.dao.entity.*;
import ci.gs2e.biblio.dao.repository.*;

/**
BUSINESS for table "categorie_livre"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class CategorieLivreBusiness implements IBasicBusiness<Request<CategorieLivreDto>, Response<CategorieLivreDto>> {

	private Response<CategorieLivreDto> response;
	@Autowired
	private CategorieLivreRepository categorieLivreRepository;
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
	private LivreBusiness livreBusiness;


	@Autowired
	private UsersBusiness userBusiness;

	public CategorieLivreBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create CategorieLivre by using CategorieLivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategorieLivreDto> create(Request<CategorieLivreDto> request, Locale locale)  throws ParseException {
		log.info("----begin create CategorieLivre-----");

		Response<CategorieLivreDto> response = new Response<CategorieLivreDto>();
		List<CategorieLivre>        items    = new ArrayList<CategorieLivre>();

		for (CategorieLivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			//			fieldsToVerify.put("code", dto.getCode());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if categorieLivre to insert do not exist
			CategorieLivre existingEntity = null;
			// verif unique libelle in db
			existingEntity = categorieLivreRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("categorieLivre libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

			CategorieLivre entityToSave = CategorieLivreTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setCode(String.format("CAT-%s", Utilities.generateCode()));

			setIdOfCategorieLivre(entityToSave);
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<CategorieLivre> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = categorieLivreRepository.saveAll((Iterable<CategorieLivre>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("categorieLivre", locale));
				response.setHasError(true);
				return response;
			}
			List<CategorieLivreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CategorieLivreTransformer.INSTANCE.toLiteDtos(itemsSaved) : CategorieLivreTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create CategorieLivre-----");
		return response;
	}

	private void setIdOfCategorieLivre(CategorieLivre entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<CategorieLivre> categoriesLit = categorieLivreRepository.findAll(pageable);

		if (categoriesLit != null && Utilities.isNotEmpty(categoriesLit.toList())) {
			entityToSave.setId(categoriesLit.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update CategorieLivre by using CategorieLivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategorieLivreDto> update(Request<CategorieLivreDto> request, Locale locale)  throws ParseException {
		log.info("----begin update CategorieLivre-----");

		Response<CategorieLivreDto> response = new Response<CategorieLivreDto>();
		List<CategorieLivre>        items    = new ArrayList<CategorieLivre>();

		for (CategorieLivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la categorieLivre existe
			CategorieLivre entityToSave = null;
			entityToSave = categorieLivreRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("categorieLivre id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

			if (Utilities.isNotBlank(dto.getLibelle()) && !dto.getLibelle().equals(entityToSave.getLibelle())) {
				CategorieLivre existingEntity = categorieLivreRepository.findByLibelle(dto.getLibelle(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("categorieLivre -> " + dto.getLibelle(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getLibelle().equalsIgnoreCase(dto.getLibelle()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du libelle '" + dto.getLibelle()+"' pour les categorieLivres", locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.isNotBlank(dto.getCode()) && !dto.getCode().equals(entityToSave.getCode())) {
				CategorieLivre existingEntity = categorieLivreRepository.findByCode(dto.getCode(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("categorieLivre -> " + dto.getCode(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getCode().equalsIgnoreCase(dto.getCode()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du code '" + dto.getCode()+"' pour les categorieLivres", locale));
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
			List<CategorieLivre> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = categorieLivreRepository.saveAll((Iterable<CategorieLivre>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("categorieLivre", locale));
				response.setHasError(true);
				return response;
			}
			List<CategorieLivreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CategorieLivreTransformer.INSTANCE.toLiteDtos(itemsSaved) : CategorieLivreTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update CategorieLivre-----");
		return response;
	}

	/**
	 * delete CategorieLivre by using CategorieLivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategorieLivreDto> delete(Request<CategorieLivreDto> request, Locale locale)  {
		log.info("----begin delete CategorieLivre-----");

		Response<CategorieLivreDto> response = new Response<CategorieLivreDto>();
		List<CategorieLivre>        items    = new ArrayList<CategorieLivre>();

		for (CategorieLivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la categorieLivre existe
			CategorieLivre existingEntity = null;
			existingEntity = categorieLivreRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("categorieLivre -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// livre
			List<Livre> listOfLivre = livreRepository.findByCategorieId(existingEntity.getId(), false);
			if (listOfLivre != null && !listOfLivre.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfLivre.size() + ")", locale));
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
			categorieLivreRepository.saveAll((Iterable<CategorieLivre>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete CategorieLivre-----");
		return response;
	}

	/**
	 * forceDelete CategorieLivre by using CategorieLivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategorieLivreDto> forceDelete(Request<CategorieLivreDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete CategorieLivre-----");

		Response<CategorieLivreDto> response = new Response<CategorieLivreDto>();
		List<CategorieLivre>        items    = new ArrayList<CategorieLivre>();

		for (CategorieLivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la categorieLivre existe
			CategorieLivre existingEntity = null;
			existingEntity = categorieLivreRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("categorieLivre -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// livre
			List<Livre> listOfLivre = livreRepository.findByCategorieId(existingEntity.getId(), false);
			if (listOfLivre != null && !listOfLivre.isEmpty()){
				Request<LivreDto> deleteRequest = new Request<LivreDto>();
				deleteRequest.setDatas(LivreTransformer.INSTANCE.toDtos(listOfLivre));
				deleteRequest.setUser(request.getUser());
				Response<LivreDto> deleteResponse = livreBusiness.delete(deleteRequest, locale);
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
			categorieLivreRepository.saveAll((Iterable<CategorieLivre>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete CategorieLivre-----");
		return response;
	}

	/**
	 * get CategorieLivre by using CategorieLivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<CategorieLivreDto> getByCriteria(Request<CategorieLivreDto> request, Locale locale)  throws Exception {
		log.info("----begin get CategorieLivre-----");

		Response<CategorieLivreDto> response = new Response<CategorieLivreDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<CategorieLivre> items 			 = categorieLivreRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("categorieLivre", locale));
			response.setHasError(false);
			return response;
		}

		List<CategorieLivreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? CategorieLivreTransformer.INSTANCE.toLiteDtos(items) : CategorieLivreTransformer.INSTANCE.toDtos(items);

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
//		response.setCount(categorieLivreRepository.count(request, em, locale));
		response.setHasError(false);
		response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get CategorieLivre-----");
		return response;
	}

	/**
	 * get full CategorieLivreDto by using CategorieLivre as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private CategorieLivreDto getFullInfos(CategorieLivreDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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
