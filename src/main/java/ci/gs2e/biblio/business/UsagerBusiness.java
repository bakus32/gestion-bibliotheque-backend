

/*
 * Java business for entity table usager 
 * Created on 2022-10-21 ( Time 23:35:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;

import ci.gs2e.biblio.business.fact.IUsagerBusiness;
import ci.gs2e.biblio.dao.entity.Emprunt;
import ci.gs2e.biblio.dao.entity.Livre;
import ci.gs2e.biblio.dao.entity.Usager;
import ci.gs2e.biblio.dao.repository.LivreRepository;
import ci.gs2e.biblio.dao.repository.UsagerRepository;
import ci.gs2e.biblio.helper.ExceptionUtils;
import ci.gs2e.biblio.helper.FunctionalError;
import ci.gs2e.biblio.helper.ParamsUtils;
import ci.gs2e.biblio.helper.SmtpUtils;
import ci.gs2e.biblio.helper.TechnicalError;
import ci.gs2e.biblio.helper.Utilities;
import ci.gs2e.biblio.helper.Validate;
import ci.gs2e.biblio.helper.contrat.Request;
import ci.gs2e.biblio.helper.contrat.Response;
import ci.gs2e.biblio.helper.dto.EmpruntDto;
import ci.gs2e.biblio.helper.dto.UsagerDto;
import ci.gs2e.biblio.helper.dto.transformer.UsagerTransformer;
import lombok.extern.java.Log;

/**
BUSINESS for table "usager"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class UsagerBusiness implements IUsagerBusiness<Request<UsagerDto>, Response<UsagerDto>> {

	private Response<UsagerDto> response;
	@Autowired
	private UsagerRepository usagerRepository;
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


	private Context context;
	@Autowired
	private SmtpUtils smtpUtils;
	@Autowired
	private ParamsUtils paramsUtils;

	private final String ENTETE = "BIBLIO";
	private final String TITRE = "Plateforme de gestion de bibliotheque";
	@Autowired
	private EmpruntBusiness empruntBusiness;

	@Autowired
	private UsagerBusiness userBusiness;

	public UsagerBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Usager by using UsagerDto as object.
	 * 
	 * @param request
	 * @return response
	 * @throws Exception 
	 * 
	 */
	@Override
	public Response<UsagerDto> create(Request<UsagerDto> request, Locale locale)  throws Exception {
		log.info("----begin create Usager-----");

		Response<UsagerDto> response = new Response<UsagerDto>();
		List<Usager>        items    = new ArrayList<Usager>();

		for (UsagerDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("nom", dto.getNom());
			fieldsToVerify.put("prenoms", dto.getPrenoms());
			fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("passwd", dto.getPasswd());
			//			fieldsToVerify.put("email", dto.getEmail());
			//			fieldsToVerify.put("matricule", dto.getMatricule());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if usager to insert do not exist
			Usager existingEntity = null;

			// verif unique login in db
			existingEntity = usagerRepository.findByLogin(dto.getLogin(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("usager login -> " + dto.getLogin(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique login in items to save
			if (items.stream().anyMatch(a -> a.getLogin().equalsIgnoreCase(dto.getLogin()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" login ", locale));
				response.setHasError(true);
				return response;
			}


			if(Utilities.notBlank(dto.getEmail())) {

				if(!Utilities.isValidEmail(dto.getEmail())) {
					response.setStatus(functionalError.INVALID_FORMAT("usagers email -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique email in db
				existingEntity = usagerRepository.findByEmail(dto.getEmail(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("usager email -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique email in items to save
				if (items.stream().anyMatch(a -> a.getEmail().equalsIgnoreCase(dto.getEmail()))) {
					response.setStatus(functionalError.DATA_DUPLICATE(" email ", locale));
					response.setHasError(true);
					return response;
				}
			}

			String password = Utilities.encrypt(dto.getPasswd());
			Usager entityToSave = UsagerTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setPasswd(password);
			entityToSave.setIsDefaultPassword(true);

			setIdOfUsager(entityToSave);

			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Usager> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = usagerRepository.saveAll((Iterable<Usager>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("usager", locale));
				response.setHasError(true);
				return response;
			}
			List<UsagerDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsagerTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsagerTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Usager-----");
		return response;
	}

	private void setIdOfUsager(Usager entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<Usager> usagerList = usagerRepository.findAll(pageable);

		if (usagerList != null && Utilities.isNotEmpty(usagerList.toList())) {
			entityToSave.setId(usagerList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update Usager by using UsagerDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsagerDto> update(Request<UsagerDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Usager-----");

		Response<UsagerDto> response = new Response<UsagerDto>();
		List<Usager>        items    = new ArrayList<Usager>();

		for (UsagerDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la usager existe
			Usager entityToSave = null;
			entityToSave = usagerRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("usager id -> " + dto.getId(), locale));
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
			if (Utilities.isNotBlank(dto.getLogin()) && !dto.getLogin().equals(entityToSave.getLogin())) {
				Usager existingEntity = usagerRepository.findByLogin(dto.getLogin(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("usager -> " + dto.getLogin(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getLogin().equalsIgnoreCase(dto.getLogin()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du login '" + dto.getLogin()+"' pour les usagers", locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLogin(dto.getLogin());
			}
			if (Utilities.isNotBlank(dto.getPasswd()) && !dto.getPasswd().equals(entityToSave.getPasswd())) {
				entityToSave.setPasswd(dto.getPasswd());
			}
			if (Utilities.isNotBlank(dto.getEmail()) && !dto.getEmail().equals(entityToSave.getEmail())) {
				Usager existingEntity = usagerRepository.findByEmail(dto.getEmail(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("usager -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getEmail().equalsIgnoreCase(dto.getEmail()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du email '" + dto.getEmail()+"' pour les usagers", locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setEmail(dto.getEmail());
			}
			if (Utilities.isNotBlank(dto.getMatricule()) && !dto.getMatricule().equals(entityToSave.getMatricule())) {
				Usager existingEntity = usagerRepository.findByMatricule(dto.getMatricule(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("usager -> " + dto.getMatricule(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getMatricule().equalsIgnoreCase(dto.getMatricule()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du matricule '" + dto.getMatricule()+"' pour les usagers", locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setMatricule(dto.getMatricule());
			}
			if (dto.getRoleId() != null && dto.getRoleId() > 0) {
				entityToSave.setRoleId(dto.getRoleId());
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
			List<Usager> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = usagerRepository.saveAll((Iterable<Usager>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("usager", locale));
				response.setHasError(true);
				return response;
			}
			List<UsagerDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsagerTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsagerTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Usager-----");
		return response;
	}

	/**
	 * delete Usager by using UsagerDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsagerDto> delete(Request<UsagerDto> request, Locale locale)  {
		log.info("----begin delete Usager-----");

		Response<UsagerDto> response = new Response<UsagerDto>();
		List<Usager>        items    = new ArrayList<Usager>();

		for (UsagerDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la usager existe
			Usager existingEntity = null;
			existingEntity = usagerRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("usager -> " + dto.getId(), locale));
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
			usagerRepository.saveAll((Iterable<Usager>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete Usager-----");
		return response;
	}

	/**
	 * forceDelete Usager by using UsagerDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsagerDto> forceDelete(Request<UsagerDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete Usager-----");

		Response<UsagerDto> response = new Response<UsagerDto>();
		List<Usager>        items    = new ArrayList<Usager>();

		for (UsagerDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la usager existe
			Usager existingEntity = null;
			existingEntity = usagerRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("usager -> " + dto.getId(), locale));
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
			usagerRepository.saveAll((Iterable<Usager>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete Usager-----");
		return response;
	}

	/**
	 * get Usager by using UsagerDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsagerDto> getByCriteria(Request<UsagerDto> request, Locale locale)  throws Exception {
		log.info("----begin get Usager-----");

		Response<UsagerDto> response = new Response<UsagerDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<Usager> items 			 = usagerRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("usager", locale));
			response.setHasError(false);
			return response;
		}

		List<UsagerDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsagerTransformer.INSTANCE.toLiteDtos(items) : UsagerTransformer.INSTANCE.toDtos(items);

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
		//			response.setCount(usagerRepository.count(request, em, locale));
		response.setHasError(false);
		response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get Usager-----");
		return response;
	}

	/**
	 * get full UsagerDto by using Usager as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private UsagerDto getFullInfos(UsagerDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		dto.setPasswd(null);
		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}

	@Override
	public Response<UsagerDto> resetAccount(Request<UsagerDto> request, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		log.info("----begin resetAccount User-----");

		response = new Response<UsagerDto>();

		try {
			List<Usager> items = new ArrayList<>();

			UsagerDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("email", dto.getEmail());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			if(!Utilities.isValidEmail(dto.getEmail())) {
				response.setStatus(functionalError.INVALID_FORMAT("email -> " + dto.getEmail(), locale));
				response.setHasError(true);
				return response;
			}

			Usager existingEmail = usagerRepository.findByEmail(dto.getEmail(), false);
			if (existingEmail == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user email -> " + dto.getEmail(), locale));
				response.setHasError(true);
				return response;
			}

			String newPassword = Utilities.generateCode();
			
			log.info("password id " + newPassword);
			
			existingEmail.setPasswd(Utilities.encrypt(newPassword));
			
			if(Utilities.notBlank(existingEmail.getEmail())) {
				List<Usager> utilisateurs = Arrays.asList(existingEmail);
				// subject
				String subject = "Réinitialisation des paramètres d'accès";

				String contenuMail = "VOTRE MOT DE PASSE A ETE REINITIALISE AVEC SUCCES. CI-DESSOUS LES ACCES : </br>";
				contenuMail += "</br></br> IDENTIFIANT : " + existingEmail.getLogin() + "</br>";
				contenuMail += "</br> MOT DE PASSE : " + newPassword + "</br>";
				sendingAsynchronous(locale, response, existingEmail, utilisateurs, subject, contenuMail);

			}

			items.add(existingEmail);

			if (!items.isEmpty()) {
				List<Usager> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = usagerRepository.saveAll((Iterable<Usager>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("user", locale));
					response.setHasError(true);
					return response;
				}
				List<UsagerDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
						? UsagerTransformer.INSTANCE.toLiteDtos(itemsSaved)
								: UsagerTransformer.INSTANCE.toDtos(itemsSaved);

						final int size = itemsSaved.size();
						List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
						itemsDto.parallelStream().forEach(dto1 -> {
							try {
								dto1 = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
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
						response.setStatus(functionalError.SUCCESS("mot de passe reinitialisé", locale));
						response.setHasError(false);
			}
			log.info("----end resetAccount User-----");
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
				log.info("Erreur| code: {} -  message: {}");
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	@Override
	public Response<UsagerDto> changePassword(Request<UsagerDto> request, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		log.info("----begin changePassword-----");

		response = new Response<UsagerDto>();

		try {

			UsagerDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("login", dto.getLogin());
			fieldsToVerify.put("passwd", dto.getPasswd());
			fieldsToVerify.put("newPassword", dto.getNewPassword());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}


			// Verify if user to authenc do not exist
			Usager existingEntity = usagerRepository.findByLoginAndPassword(dto.getLogin().trim(),
					Utilities.encrypt(dto.getPasswd().trim()), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.LOGIN_FAIL(locale));
				response.setHasError(true);
				return response;
			}

			if (existingEntity.getIsDefaultPassword() != null && existingEntity.getIsDefaultPassword()) {
				if (Utilities.encrypt(dto.getNewPassword().trim()).equals(existingEntity.getPasswd())) {
					response.setStatus(functionalError.DISALLOWED_OPERATION(
							"Vous avez renvoyé le mot de passe par défaut. Veuillez le changer pour plus de sécurité s'il vous plait.",
							locale));
					response.setHasError(true);
					return response;
				}
				existingEntity.setIsDefaultPassword(false);
			}

			existingEntity.setPasswd(Utilities.encrypt(dto.getNewPassword()));
			existingEntity.setIsConnected(false);
			existingEntity.setIsDefaultPassword(false);
			existingEntity = usagerRepository.save(existingEntity);
			List<Usager> items = Arrays.asList(existingEntity);
			List<UsagerDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? UsagerTransformer.INSTANCE.toLiteDtos(items)
							: UsagerTransformer.INSTANCE.toDtos(items);
					final int size = items.size();
					itemsDto.parallelStream().forEach(elt -> {
						try {
							elt = getFullInfos(elt, size, request.getIsSimpleLoading(), locale);
						} catch (Exception e) {
							e.printStackTrace();
						}
					});
					//
					response.setItems(itemsDto);
					response.setStatus(functionalError.SUCCESS("", locale));
					response.setHasError(false);

					log.info("----end changerMotDePasse-----");
		} catch (PermissionDeniedDataAccessException e) {
			e.printStackTrace();
			exceptionUtils.PERMISSION_DENIED_DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (DataAccessResourceFailureException e) {
			e.printStackTrace();
			exceptionUtils.DATA_ACCESS_RESOURCE_FAILURE_EXCEPTION(response, locale, e);
		} catch (DataAccessException e) {
			e.printStackTrace();
			exceptionUtils.DATA_ACCESS_EXCEPTION(response, locale, e);
		} catch (RuntimeException e) {
			e.printStackTrace();
			exceptionUtils.RUNTIME_EXCEPTION(response, locale, e);
		} catch (Exception e) {
			e.printStackTrace();
			exceptionUtils.EXCEPTION(response, locale, e);
		} finally {
			if (response.isHasError() && response.getStatus() != null) {
				log.info("Erreur| code: {} -  message: {}");
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}

	@Override
	public Response<UsagerDto> login(Request<UsagerDto> request, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		response = new Response<UsagerDto>();
		UsagerDto data = request.getData();
		// Definir les parametres obligatoires
		Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
		fieldsToVerify.put("login", data.getLogin());
		fieldsToVerify.put("passwd", data.getPasswd());
		if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
			response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
			response.setHasError(true);
			return response;
		}
		Usager item = null;
		item = usagerRepository.findByLoginAndPassword(data.getLogin(), Utilities.encrypt(data.getPasswd().trim()), false);
		if (item == null) {
			response.setStatus(functionalError.LOGIN_FAIL(locale));
			response.setHasError(true);
			return response;
		}
		if (Utilities.isTrue(item.getIsLocked())) {
			response.setStatus(functionalError
					.USER_IS_LOCKED(String.format("l'utilisateur est vérouillé(e)", item.getLogin()), locale));
			response.setHasError(true);
			return response;
		}

		item.setIsConnected(true);
		item.setLastConnectionDate(Utilities.getCurrentDate());

		List<Usager> items = Arrays.asList(item);
		if (items != null && !items.isEmpty()) {
			List<Usager> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = usagerRepository.saveAll((Iterable<Usager>) items);
			if (itemsSaved != null) {
				List<UsagerDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
						? UsagerTransformer.INSTANCE.toLiteDtos(itemsSaved)
								: UsagerTransformer.INSTANCE.toDtos(itemsSaved);

						final int size = itemsSaved.size();
						List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
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
						response.setStatus(functionalError.SUCCESS("", locale));
						response.setHasError(false);
			}
		}

		return response;
	}

	@Override
	public Response<UsagerDto> emprunterLivre(Request<UsagerDto> request, Locale locale) throws Exception {
		// TODO Auto-generated method stub
		log.info("----begin emprunterLivre Usager-----");

		response = new Response<UsagerDto>();

		try {
			List<Usager> items = new ArrayList<>();

			UsagerDto dto = request.getData();
			// Definir les parametres obligatoires
			Map<String, Object> fieldsToVerify = new HashMap<String, Object>();
			fieldsToVerify.put("id", dto.getId());
			fieldsToVerify.put("livreId", dto.getLivreId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			Usager usager = usagerRepository.findOne(dto.getId(), Boolean.FALSE);
			if(usager == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("usager id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}
			
			Livre ecistingLivre = livreRepository.findOne(dto.getLivreId(), false);
			if (ecistingLivre == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("livre id -> " + dto.getLivreId(), locale));
				response.setHasError(true);
				return response;
			}
			
			EmpruntDto empruntDto = new EmpruntDto();
			empruntDto.setUsagerId(dto.getId());
			empruntDto.setLivreId(dto.getLivreId());
			
			Request<EmpruntDto> requestEmprunt = new Request<>();
			requestEmprunt.setUser(request.getUser() != null ? request.getUser() : null);
			requestEmprunt.setDatas(Arrays.asList(empruntDto));
			
			Response<EmpruntDto> responseEmprunt = empruntBusiness.create(requestEmprunt, locale);
			if(responseEmprunt.isHasError()) {
				response.setStatus(responseEmprunt.getStatus());
				response.setHasError(true);
				return response;
			}
			items.add(usager);

			if (!items.isEmpty()) {
				List<Usager> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = usagerRepository.saveAll((Iterable<Usager>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("user", locale));
					response.setHasError(true);
					return response;
				}
				List<UsagerDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
						? UsagerTransformer.INSTANCE.toLiteDtos(itemsSaved)
								: UsagerTransformer.INSTANCE.toDtos(itemsSaved);

						final int size = itemsSaved.size();
						List<String> listOfError = Collections.synchronizedList(new ArrayList<String>());
						itemsDto.parallelStream().forEach(dto1 -> {
							try {
								dto1 = getFullInfos(dto, size, request.getIsSimpleLoading(), locale);
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
						response.setStatus(functionalError.SUCCESS("mot de passe reinitialisé", locale));
						response.setHasError(false);
			}
			log.info("----end emprunterLivre Usager-----");
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
				log.info("Erreur| code: {} -  message: {}");
				throw new RuntimeException(response.getStatus().getCode() + ";" + response.getStatus().getMessage());
			}
		}
		return response;
	}
	
	private void sendingAsynchronous(Locale locale, Response<UsagerDto> response, Usager entityToSave, List<Usager> utilisateurs, String subject, String contenuSending) {
		Thread t = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					sendEmail(locale, entityToSave, utilisateurs, subject, contenuSending);
				} catch (Exception e) {
					response.setStatus(functionalError.SAVE_FAIL("message non transmis", locale));
					response.setHasError(Boolean.TRUE);
					e.printStackTrace();
				}
			}
		});
		t.start();
	}

	private void sendEmail(Locale locale, Usager entity, List<Usager> utilisateurs, String subject, String contenu) {
		if (Utilities.isNotEmpty(utilisateurs)) {
			// set mail to user
			Map<String, String> from = new HashMap<>();
			from.put("email", paramsUtils.getSmtpLogin());
			from.put("user", ENTETE);
			// recipients
			List<Map<String, String>> toRecipients = new ArrayList<Map<String, String>>();
			utilisateurs.stream().forEach(user -> {
				Map<String, String> recipient = new HashMap<String, String>();
				recipient = new HashMap<String, String>();
				recipient.put("email", user.getEmail());
				// recipient.put("user", user.getLogin());
				toRecipients.add(recipient);
			});
			// choisir la vraie url
			String appLink = paramsUtils.getRootFilesUrl();

			String body = "";
			context = new Context();
			String template = "blank";

			context.setVariable("contenu", contenu);
			context.setVariable("entete", ENTETE);
			context.setVariable("titre", TITRE);

			context.setVariable("appLink", appLink);
			Response<UsagerDto> responseEnvoiEmail = smtpUtils.sendEmail(from, toRecipients, subject, body, null,
					context, template, locale);
			if (responseEnvoiEmail.isHasError()) {
				log.info("erreur lors de l'envoi du mail");
			} else {
				// inserer les données en base de données
				usagerRepository.save(entity);
				log.info("un mail vous a ete envoye à l'adresse" + entity.getEmail());
			}
		}
	}
}
