

/*
 * Java business for entity table users 
 * Created on 2022-10-22 ( Time 00:17:45 )
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
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;

import ci.gs2e.biblio.business.fact.IUserBusiness;
import ci.gs2e.biblio.dao.entity.Role;
import ci.gs2e.biblio.dao.entity.Users;
import ci.gs2e.biblio.dao.repository.RoleRepository;
import ci.gs2e.biblio.dao.repository.UsersRepository;
import ci.gs2e.biblio.helper.ExceptionUtils;
import ci.gs2e.biblio.helper.FunctionalError;
import ci.gs2e.biblio.helper.ParamsUtils;
import ci.gs2e.biblio.helper.SmtpUtils;
import ci.gs2e.biblio.helper.TechnicalError;
import ci.gs2e.biblio.helper.Utilities;
import ci.gs2e.biblio.helper.Validate;
import ci.gs2e.biblio.helper.contrat.Request;
import ci.gs2e.biblio.helper.contrat.Response;
import ci.gs2e.biblio.helper.dto.UsersDto;
import ci.gs2e.biblio.helper.dto.transformer.UsersTransformer;
import lombok.extern.java.Log;

/**
BUSINESS for table "users"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class UsersBusiness implements IUserBusiness<Request<UsersDto>, Response<UsersDto>> {

	private Response<UsersDto> response;
	@Autowired
	private UsersRepository usersRepository;
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
	private Context context;
	@Autowired
	private SmtpUtils smtpUtils;
	@Autowired
	private ParamsUtils paramsUtils;

	private final String ENTETE = "BIBLIO";
	private final String TITRE = "Plateforme de gestion de bibliotheque";

	public UsersBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}

	/**
	 * create Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * @throws Exception 
	 * 
	 */
	@Override
	public Response<UsersDto> create(Request<UsersDto> request, Locale locale)  throws Exception {
		log.info("----begin create Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();

		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("nom", dto.getNom());
			fieldsToVerify.put("prenoms", dto.getPrenoms());
			fieldsToVerify.put("login", dto.getLogin());
//			fieldsToVerify.put("passwd", dto.getPasswd());
			//			fieldsToVerify.put("email", dto.getEmail());
			//			fieldsToVerify.put("matricule", dto.getMatricule());
			fieldsToVerify.put("roleId", dto.getRoleId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if users to insert do not exist
			Users existingEntity = null;

			// verif unique login in db
			existingEntity = usersRepository.findByLogin(dto.getLogin(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("users login -> " + dto.getLogin(), locale));
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
					response.setStatus(functionalError.INVALID_FORMAT("users email -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				// verif unique email in db
				existingEntity = usersRepository.findByEmail(dto.getEmail(), false);
				if (existingEntity != null) {
					response.setStatus(functionalError.DATA_EXIST("users email -> " + dto.getEmail(), locale));
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

			String password = Utilities.encrypt(Utilities.generateCode());
			Users entityToSave = UsersTransformer.INSTANCE.toEntity(dto, existingRole);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			log.info("password encrypted : " + password);
			entityToSave.setPasswd(password);
			entityToSave.setMatricule(String.format("USER-%s", Utilities.generateCode()));
			entityToSave.setIsDefaultPassword(true);
			
			setIdOfUser(entityToSave);

			Users entitysaved = usersRepository.save(entityToSave);
			if (entitysaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("user", locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.notBlank(dto.getEmail())) {

				List<Users> utilisateurs = Arrays.asList(entitysaved != null ? entitysaved : null);
				String subject = "Création d'un nouvel utilisateur";
				String contenuMail = "VOTRE COMPTE A ETE CREE AVEC SUCCES. CI-DESSOUS LES ACCES : <br/>";
				contenuMail += "<br/><br/>IDENTIFIANT : " + entitysaved.getLogin();
				//                contenu += "<br/><br/>LOGIN : " + Utilities.generateLogin();
				contenuMail += "<br/><br/>MOT DE PASSE : " + password;
				String contenuSending = contenuMail;
				sendingAsynchronous(locale, response, entityToSave, utilisateurs, subject, contenuSending);
			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Users> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("users", locale));
				response.setHasError(true);
				return response;
			}
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Users-----");
		return response;
	}
	
	private void setIdOfUser(Users entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<Users> userList = usersRepository.findAll(pageable);

		if (userList != null && Utilities.isNotEmpty(userList.toList())) {
			entityToSave.setId(userList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> update(Request<UsersDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();

		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la users existe
			Users entityToSave = null;
			entityToSave = usersRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("users id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

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
			if (Utilities.isNotBlank(dto.getNom()) && !dto.getNom().equals(entityToSave.getNom())) {
				entityToSave.setNom(dto.getNom());
			}
			if (Utilities.isNotBlank(dto.getPrenoms()) && !dto.getPrenoms().equals(entityToSave.getPrenoms())) {
				entityToSave.setPrenoms(dto.getPrenoms());
			}
			if (Utilities.isNotBlank(dto.getLogin()) && !dto.getLogin().equals(entityToSave.getLogin())) {
				Users existingEntity = usersRepository.findByLogin(dto.getLogin(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("users -> " + dto.getLogin(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getLogin().equalsIgnoreCase(dto.getLogin()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du login '" + dto.getLogin()+"' pour les userss", locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setLogin(dto.getLogin());
			}
			if (Utilities.isNotBlank(dto.getPasswd()) && !dto.getPasswd().equals(entityToSave.getPasswd())) {
				entityToSave.setPasswd(dto.getPasswd());
			}
			if (Utilities.isNotBlank(dto.getEmail()) && !dto.getEmail().equals(entityToSave.getEmail())) {
				Users existingEntity = usersRepository.findByEmail(dto.getEmail(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("users -> " + dto.getEmail(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getEmail().equalsIgnoreCase(dto.getEmail()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du email '" + dto.getEmail()+"' pour les userss", locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setEmail(dto.getEmail());
			}
			if (Utilities.isNotBlank(dto.getMatricule()) && !dto.getMatricule().equals(entityToSave.getMatricule())) {
				Users existingEntity = usersRepository.findByMatricule(dto.getMatricule(), false);
				if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
					response.setStatus(functionalError.DATA_EXIST("users -> " + dto.getMatricule(), locale));
					response.setHasError(true);
					return response;
				}
				if (items.stream().anyMatch(a->a.getMatricule().equalsIgnoreCase(dto.getMatricule()) && !a.getId().equals(entityToSaveId))) {
					response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du matricule '" + dto.getMatricule()+"' pour les userss", locale));
					response.setHasError(true);
					return response;
				}
				entityToSave.setMatricule(dto.getMatricule());
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
			if (dto.getIsConnected() != null) {
				entityToSave.setIsConnected(dto.getIsConnected());
			}
			if (dto.getIsLocked() != null) {
				entityToSave.setIsLocked(dto.getIsLocked());
			}
			if (Utilities.notBlank(dto.getLastConnectionDate())) {
				entityToSave.setLastConnectionDate(dateFormat.parse(dto.getLastConnectionDate()));
			}
			entityToSave.setUpdatedAt(Utilities.getCurrentDate());
			entityToSave.setUpdatedBy(request.getUser());
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Users> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("users", locale));
				response.setHasError(true);
				return response;
			}
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved) : UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Users-----");
		return response;
	}

	/**
	 * delete Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> delete(Request<UsersDto> request, Locale locale)  {
		log.info("----begin delete Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();

		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la users existe
			Users existingEntity = null;
			existingEntity = usersRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("users -> " + dto.getId(), locale));
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
			usersRepository.saveAll((Iterable<Users>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete Users-----");
		return response;
	}

	/**
	 * forceDelete Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> forceDelete(Request<UsersDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete Users-----");

		Response<UsersDto> response = new Response<UsersDto>();
		List<Users>        items    = new ArrayList<Users>();

		for (UsersDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la users existe
			Users existingEntity = null;
			existingEntity = usersRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("users -> " + dto.getId(), locale));
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
			usersRepository.saveAll((Iterable<Users>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete Users-----");
		return response;
	}

	/**
	 * get Users by using UsersDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<UsersDto> getByCriteria(Request<UsersDto> request, Locale locale)  throws Exception {
		log.info("----begin get Users-----");

		Response<UsersDto> response = new Response<UsersDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<Users> items 			 = usersRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("users", locale));
			response.setHasError(false);
			return response;
		}

		List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? UsersTransformer.INSTANCE.toLiteDtos(items) : UsersTransformer.INSTANCE.toDtos(items);

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
//		response.setCount(usersRepository.count(request, em, locale));
		response.setHasError(false);
		response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get Users-----");
		return response;
	}

	/**
	 * get full UsersDto by using Users as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private UsersDto getFullInfos(UsersDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
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

	public Response<UsersDto> login(Request<UsersDto> request, Locale locale) throws Exception {
		response = new Response<UsersDto>();
		UsersDto data = request.getData();
		// Definir les parametres obligatoires
		Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
		fieldsToVerify.put("login", data.getLogin());
		fieldsToVerify.put("passwd", data.getPasswd());
		if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
			response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
			response.setHasError(true);
			return response;
		}
		Users item = null;
		item = usersRepository.findByLoginAndPassword(data.getLogin(), Utilities.encrypt(data.getPasswd().trim()), false);
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

		List<Users> items = Arrays.asList(item);
		if (items != null && !items.isEmpty()) {
			List<Users> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
			if (itemsSaved != null) {
				List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
						? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved)
								: UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

	@SuppressWarnings("unused")
	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	public Response<UsersDto> changePassword(Request<UsersDto> request, Locale locale) {
		log.info("----begin changePassword-----");

		response = new Response<UsersDto>();

		try {

			UsersDto dto = request.getData();
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
			Users existingEntity = usersRepository.findByLoginAndPassword(dto.getLogin().trim(),
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
			existingEntity = usersRepository.save(existingEntity);
			List<Users> items = Arrays.asList(existingEntity);
			List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
					? UsersTransformer.INSTANCE.toLiteDtos(items)
							: UsersTransformer.INSTANCE.toDtos(items);
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

	@SuppressWarnings("unused")
	@Transactional(rollbackFor = {RuntimeException.class, Exception.class})
	// @Transactional(rollbackFor = { RuntimeException.class, Exception.class })
	public Response<UsersDto> resetAccount(Request<UsersDto> request, Locale locale) {
		log.info("----begin resetAccount User-----");

		response = new Response<UsersDto>();

		try {
			List<Users> items = new ArrayList<>();
			List<Users> existingEntity = null;

			UsersDto dto = request.getData();
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

			Users existingEmail = usersRepository.findByEmail(dto.getEmail(), false);
			if (existingEmail == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("user email -> " + dto.getEmail(), locale));
				response.setHasError(true);
				return response;
			}

			String newPassword = Utilities.generateCode();
			
			if(Utilities.notBlank(existingEmail.getEmail())) {
				List<Users> utilisateurs = Arrays.asList(existingEmail);
				// subject
				String subject = "Réinitialisation des paramètres d'accès";

				String contenuMail = "VOTRE MOT DE PASSE A ETE RE-INITIALISE AVEC SUCCES. CI-DESSOUS LES ACCES : </br>";
				contenuMail += "</br></br>IDENTIFIANT : " + existingEmail.getLogin() + "</br>";
				contenuMail += "</br>MOT DE PASSE : " + newPassword + "</br>";
				sendingAsynchronous(locale, response, existingEmail, utilisateurs, subject, contenuMail);
			}

			items.add(existingEmail);

			if (!items.isEmpty()) {
				List<Users> itemsSaved = null;
				// inserer les donnees en base de donnees
				itemsSaved = usersRepository.saveAll((Iterable<Users>) items);
				if (itemsSaved == null) {
					response.setStatus(functionalError.SAVE_FAIL("user", locale));
					response.setHasError(true);
					return response;
				}
				List<UsersDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading()))
						? UsersTransformer.INSTANCE.toLiteDtos(itemsSaved)
								: UsersTransformer.INSTANCE.toDtos(itemsSaved);

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

	private void sendingAsynchronous(Locale locale, Response<UsersDto> response, Users entityToSave, List<Users> utilisateurs, String subject, String contenuSending) {
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

	private void sendEmail(Locale locale, Users entity, List<Users> utilisateurs, String subject, String contenu) {
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
			Response<UsersDto> responseEnvoiEmail = smtpUtils.sendEmail(from, toRecipients, subject, body, null,
					context, template, locale);
			if (responseEnvoiEmail.isHasError()) {
				log.info("erreur lors de l'envoi du mail");
			} else {
				// inserer les données en base de données
				usersRepository.save(entity);
				log.info("un mail vous a ete envoye à l'adresse" + entity.getEmail());
			}
		}
	}
}
