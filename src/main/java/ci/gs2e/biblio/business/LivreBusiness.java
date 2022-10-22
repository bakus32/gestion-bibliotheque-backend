

/*
 * Java business for entity table livre 
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
BUSINESS for table "livre"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class LivreBusiness implements IBasicBusiness<Request<LivreDto>, Response<LivreDto>> {

	private Response<LivreDto> response;
	@Autowired
	private LivreRepository livreRepository;
	@Autowired
	private CategorieLivreRepository categorieLivreRepository;
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

	public LivreBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Livre by using LivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreDto> create(Request<LivreDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Livre-----");

		Response<LivreDto> response = new Response<LivreDto>();
		List<Livre>        items    = new ArrayList<Livre>();

		for (LivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("titre", dto.getTitre());
			fieldsToVerify.put("categorieId", dto.getCategorieId());
			fieldsToVerify.put("maisonEdition", dto.getMaisonEdition());
			fieldsToVerify.put("nombreExemplaires", dto.getNombreExemplaires());
			fieldsToVerify.put("nombrePages", dto.getNombrePages());
			fieldsToVerify.put("datePublication", dto.getDatePublication());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if categorieLivre exist
			CategorieLivre existingCategorieLivre = null;
			if (Utilities.isValidID(dto.getCategorieId())){
				existingCategorieLivre = categorieLivreRepository.findOne(dto.getCategorieId(), false);
				if (existingCategorieLivre == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("categorieLivre categorieId -> " + dto.getCategorieId(), locale));
					response.setHasError(true);
					return response;
				}
			}
			Livre entityToSave = LivreTransformer.INSTANCE.toEntity(dto, existingCategorieLivre);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);

			setIdOfLivre(entityToSave);

			Livre entitySaved = livreRepository.save(entityToSave);
			if (entitySaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.isNotEmpty(dto.getDatasAuteurs())) {
				List<LivreAuteurDto> datasAuteurs = new ArrayList<LivreAuteurDto>();
				dto.getDatasAuteurs().forEach(f -> {
					LivreAuteurDto auteurDto = new LivreAuteurDto();
					auteurDto.setLivreId(entitySaved.getId());
					auteurDto.setAuteurId(f.getId());
					datasAuteurs.add(auteurDto);
				});

				Request<LivreAuteurDto> subRequest = new Request<LivreAuteurDto>();
				subRequest.setDatas(datasAuteurs);
				subRequest.setUser(request.getUser());
				Response<LivreAuteurDto> subResponse = livreAuteurBusiness.create(subRequest, locale);
				if (subResponse.isHasError()) {
					response.setStatus(subResponse.getStatus());
					response.setHasError(true);
					return response;
				}
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Livre> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = livreRepository.saveAll((Iterable<Livre>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("livre", locale));
				response.setHasError(true);
				return response;
			}
			List<LivreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LivreTransformer.INSTANCE.toLiteDtos(itemsSaved) : LivreTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Livre-----");
		return response;
	}

	private void setIdOfLivre(Livre entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<Livre> livreList = livreRepository.findAll(pageable);

		if (livreList != null && Utilities.isNotEmpty(livreList.toList())) {
			entityToSave.setId(livreList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update Livre by using LivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreDto> update(Request<LivreDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Livre-----");

		Response<LivreDto> response = new Response<LivreDto>();
		List<Livre>        items    = new ArrayList<Livre>();

		for (LivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la livre existe
			Livre entityToSave = null;
			entityToSave = livreRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("livre id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

			// Verify if categorieLivre exist
			if (Utilities.isValidID(dto.getCategorieId()) && !entityToSave.getCategorieLivre().getId().equals(dto.getCategorieId())){
				CategorieLivre existingCategorieLivre = categorieLivreRepository.findOne(dto.getCategorieId(), false);
				if (existingCategorieLivre == null) {
					response.setStatus(functionalError.DATA_NOT_EXIST("categorieLivre categorieId -> " + dto.getCategorieId(), locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setCategorieLivre(existingCategorieLivre);
			}
			if (Utilities.isNotBlank(dto.getTitre()) && !dto.getTitre().equals(entityToSave.getTitre())) {
				entityToSave.setTitre(dto.getTitre());
			}
			if (Utilities.isNotBlank(dto.getMaisonEdition()) && !dto.getMaisonEdition().equals(entityToSave.getMaisonEdition())) {
				entityToSave.setMaisonEdition(dto.getMaisonEdition());
			}
			if (dto.getNombreExemplaires() != null && dto.getNombreExemplaires() > 0) {
				entityToSave.setNombreExemplaires(dto.getNombreExemplaires());
			}
			if (dto.getNombrePages() != null && dto.getNombrePages() > 0) {
				entityToSave.setNombrePages(dto.getNombrePages());
			}
			if (Utilities.notBlank(dto.getDatePublication())) {
				entityToSave.setDatePublication(dateFormat.parse(dto.getDatePublication()));
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
			List<Livre> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = livreRepository.saveAll((Iterable<Livre>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("livre", locale));
				response.setHasError(true);
				return response;
			}
			List<LivreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LivreTransformer.INSTANCE.toLiteDtos(itemsSaved) : LivreTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Livre-----");
		return response;
	}

	/**
	 * delete Livre by using LivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreDto> delete(Request<LivreDto> request, Locale locale)  {
		log.info("----begin delete Livre-----");

		Response<LivreDto> response = new Response<LivreDto>();
		List<Livre>        items    = new ArrayList<Livre>();

		for (LivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la livre existe
			Livre existingEntity = null;
			existingEntity = livreRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("livre -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// livreAuteur
			List<LivreAuteur> listOfLivreAuteur = livreAuteurRepository.findByLivreId(existingEntity.getId(), false);
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
			livreRepository.saveAll((Iterable<Livre>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete Livre-----");
		return response;
	}

	/**
	 * forceDelete Livre by using LivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreDto> forceDelete(Request<LivreDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete Livre-----");

		Response<LivreDto> response = new Response<LivreDto>();
		List<Livre>        items    = new ArrayList<Livre>();

		for (LivreDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la livre existe
			Livre existingEntity = null;
			existingEntity = livreRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("livre -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// livreAuteur
			List<LivreAuteur> listOfLivreAuteur = livreAuteurRepository.findByLivreId(existingEntity.getId(), false);
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
			livreRepository.saveAll((Iterable<Livre>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete Livre-----");
		return response;
	}

	/**
	 * get Livre by using LivreDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<LivreDto> getByCriteria(Request<LivreDto> request, Locale locale)  throws Exception {
		log.info("----begin get Livre-----");

		Response<LivreDto> response = new Response<LivreDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<Livre> items 			 = livreRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("livre", locale));
			response.setHasError(false);
			return response;
		}

		List<LivreDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? LivreTransformer.INSTANCE.toLiteDtos(items) : LivreTransformer.INSTANCE.toDtos(items);

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
		//			response.setCount(livreRepository.count(request, em, locale));
		response.setHasError(false);
		response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get Livre-----");
		return response;
	}

	/**
	 * get full LivreDto by using Livre as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private LivreDto getFullInfos(LivreDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		List<Map<String, String>> auteurs = new ArrayList<Map<String, String>>();
		List<LivreAuteur> list = livreAuteurRepository.findByLivreId(dto.getId(), false);
		if (Utilities.isNotEmpty(list)) {
			list.forEach(aut -> {
				Map<String, String> f = new HashMap<String, String>();
				f.put("nom", aut.getAuteur() != null ? aut.getAuteur().getNom() : null);
				f.put("prenoms", aut.getAuteur() != null ? aut.getAuteur().getPrenoms() : null);
				f.put("specialite", aut.getAuteur() != null ? aut.getAuteur().getSpecialite() : null);
				auteurs.add(f);
			});
		}
		dto.setAuteurs(auteurs);
		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}
