                                                    											

/*
 * Java business for entity table role 
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
BUSINESS for table "role"
 * 
 * @author Smile Back-End generator
 *
 */
@Log
@Component
public class RoleBusiness implements IBasicBusiness<Request<RoleDto>, Response<RoleDto>> {

	private Response<RoleDto> response;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private UsersRepository usersRepository;
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
    private UsersBusiness usersBusiness;
  
                  
        @Autowired
    private RoleFonctionnaliteBusiness roleFonctionnaliteBusiness;
  
    
          @Autowired
    private UsersBusiness userBusiness;
    
	public RoleBusiness() {
		dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateTimeFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	}
	
	/**
	 * create Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> create(Request<RoleDto> request, Locale locale)  throws ParseException {
		log.info("----begin create Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role>        items    = new ArrayList<Role>();
			
		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("libelle", dto.getLibelle());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verify if role to insert do not exist
			Role existingEntity = null;
			// verif unique libelle in db
			existingEntity = roleRepository.findByLibelle(dto.getLibelle(), false);
			if (existingEntity != null) {
				response.setStatus(functionalError.DATA_EXIST("role libelle -> " + dto.getLibelle(), locale));
				response.setHasError(true);
				return response;
			}
			// verif unique libelle in items to save
			if (items.stream().anyMatch(a -> a.getLibelle().equalsIgnoreCase(dto.getLibelle()))) {
				response.setStatus(functionalError.DATA_DUPLICATE(" libelle ", locale));
				response.setHasError(true);
				return response;
			}

				Role entityToSave = RoleTransformer.INSTANCE.toEntity(dto);
			entityToSave.setCreatedAt(Utilities.getCurrentDate());
			entityToSave.setCreatedBy(request.getUser());
			entityToSave.setIsDeleted(false);
			entityToSave.setCode(String.format("ROLE-%s", Utilities.generateCode()));
			
			setIdOfRole(entityToSave);
			
			Role entitySaved = roleRepository.save(entityToSave);
			if (entitySaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}

			if (Utilities.isNotEmpty(dto.getDatasFonctionnalite())) {
				List<RoleFonctionnaliteDto> datasProfilFunctionality = new ArrayList<RoleFonctionnaliteDto>();
				dto.getDatasFonctionnalite().forEach(f -> {
					RoleFonctionnaliteDto roleFunctionalityDto = new RoleFonctionnaliteDto();
					roleFunctionalityDto.setRoleId(entitySaved.getId());
					roleFunctionalityDto.setFonctionnaliteId(f.getId());
					datasProfilFunctionality.add(roleFunctionalityDto);
				});

				Request<RoleFonctionnaliteDto> subRequest = new Request<RoleFonctionnaliteDto>();
				subRequest.setDatas(datasProfilFunctionality);
				subRequest.setUser(request.getUser());
				Response<RoleFonctionnaliteDto> subResponse = roleFonctionnaliteBusiness.create(subRequest, locale);
				if (subResponse.isHasError()) {
					response.setStatus(subResponse.getStatus());
					response.setHasError(true);
					return response;
				}
			}
			
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Role> itemsSaved = null;
			// inserer les donnees en base de donnees
			itemsSaved = roleRepository.saveAll((Iterable<Role>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end create Role-----");
		return response;
	}
	
	private void setIdOfRole(Role entityToSave) {
		Pageable pageable = PageRequest.of(0, 1, Sort.by("createdAt").descending());
		Page<Role> roleList = roleRepository.findAll(pageable);

		if (roleList != null && Utilities.isNotEmpty(roleList.toList())) {
			entityToSave.setId(roleList.toList().get(0).getId() + 1);
		}else {
			entityToSave.setId(1);
		}
	}

	/**
	 * update Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> update(Request<RoleDto> request, Locale locale)  throws ParseException {
		log.info("----begin update Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role>        items    = new ArrayList<Role>();
			
		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la role existe
			Role entityToSave = null;
			entityToSave = roleRepository.findOne(dto.getId(), false);
			if (entityToSave == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("role id -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			Integer entityToSaveId = entityToSave.getId();

			if (Utilities.isNotBlank(dto.getLibelle()) && !dto.getLibelle().equals(entityToSave.getLibelle())) {
			                 Role existingEntity = roleRepository.findByLibelle(dto.getLibelle(), false);
                if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
                  response.setStatus(functionalError.DATA_EXIST("role -> " + dto.getLibelle(), locale));
                  response.setHasError(true);
                  return response;
                }
                if (items.stream().anyMatch(a->a.getLibelle().equalsIgnoreCase(dto.getLibelle()) && !a.getId().equals(entityToSaveId))) {
                  response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du libelle '" + dto.getLibelle()+"' pour les roles", locale));
                  response.setHasError(true);
                  return response;
                }
       						entityToSave.setLibelle(dto.getLibelle());
			}
			if (Utilities.isNotBlank(dto.getCode()) && !dto.getCode().equals(entityToSave.getCode())) {
			                 Role existingEntity = roleRepository.findByCode(dto.getCode(), false);
                if (existingEntity != null && !existingEntity.getId().equals(entityToSave.getId())) {
                  response.setStatus(functionalError.DATA_EXIST("role -> " + dto.getCode(), locale));
                  response.setHasError(true);
                  return response;
                }
                if (items.stream().anyMatch(a->a.getCode().equalsIgnoreCase(dto.getCode()) && !a.getId().equals(entityToSaveId))) {
                  response.setStatus(functionalError.DATA_DUPLICATE("Tentative de duplication du code '" + dto.getCode()+"' pour les roles", locale));
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


			Role itemSaved = roleRepository.save(entityToSave);
			if (itemSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}
			if (Utilities.isNotEmpty(dto.getDatasFonctionnalite())) {
				List<RoleFonctionnalite> list = roleFonctionnaliteRepository
						.findByRoleId(itemSaved.getId(), false);
				for (RoleFonctionnalite rf : list) {
					RoleFonctionnaliteDto itemsData = RoleFonctionnaliteTransformer.INSTANCE.toDto(rf);
					Request<RoleFonctionnaliteDto> subRequest = new Request<RoleFonctionnaliteDto>();
					subRequest.setDatas(Arrays.asList(itemsData));
					subRequest.setUser(request.getUser());
					Response<RoleFonctionnaliteDto> subResponse = roleFonctionnaliteBusiness.delete(subRequest, locale);
					if (subResponse.isHasError()) {
						response.setStatus(subResponse.getStatus());
						response.setHasError(true);
						return response;
					}
				}
				for (FonctionnaliteDto f : dto.getDatasFonctionnalite()) {
					RoleFonctionnaliteDto roleFunctionalityDto = new RoleFonctionnaliteDto();
					roleFunctionalityDto.setRoleId(itemSaved.getId());
					roleFunctionalityDto.setFonctionnaliteId(f.getId());

					Request<RoleFonctionnaliteDto> subRequests = new Request<RoleFonctionnaliteDto>();
					subRequests.setDatas(Arrays.asList(roleFunctionalityDto));
					subRequests.setUser(request.getUser());
					Response<RoleFonctionnaliteDto> subResponses = roleFonctionnaliteBusiness.create(subRequests,
							locale);
					if (subResponses.isHasError()) {
						response.setStatus(subResponses.getStatus());
						response.setHasError(true);
						return response;
					}
				}

			}
			items.add(entityToSave);
		}

		if (!items.isEmpty()) {
			List<Role> itemsSaved = null;
			// maj les donnees en base
			itemsSaved = roleRepository.saveAll((Iterable<Role>) items);
			if (itemsSaved == null) {
				response.setStatus(functionalError.SAVE_FAIL("role", locale));
				response.setHasError(true);
				return response;
			}
			List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleTransformer.INSTANCE.toLiteDtos(itemsSaved) : RoleTransformer.INSTANCE.toDtos(itemsSaved);

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

		log.info("----end update Role-----");
		return response;
	}

	/**
	 * delete Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> delete(Request<RoleDto> request, Locale locale)  {
		log.info("----begin delete Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role>        items    = new ArrayList<Role>();
			
		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la role existe
			Role existingEntity = null;
			existingEntity = roleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("role -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

			// users
			List<Users> listOfUsers = usersRepository.findByRoleId(existingEntity.getId(), false);
			if (listOfUsers != null && !listOfUsers.isEmpty()){
				response.setStatus(functionalError.DATA_NOT_DELETABLE("(" + listOfUsers.size() + ")", locale));
				response.setHasError(true);
				return response;
			}
			// roleFonctionnalite
			List<RoleFonctionnalite> listOfRoleFonctionnalite = roleFonctionnaliteRepository.findByRoleId(existingEntity.getId(), false);
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
			roleRepository.saveAll((Iterable<Role>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end delete Role-----");
		return response;
	}

	/**
	 * forceDelete Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> forceDelete(Request<RoleDto> request, Locale locale) throws ParseException {
		log.info("----begin forceDelete Role-----");

		Response<RoleDto> response = new Response<RoleDto>();
		List<Role>        items    = new ArrayList<Role>();
			
		for (RoleDto dto : request.getDatas()) {
			// Definir les parametres obligatoires
			Map<String, java.lang.Object> fieldsToVerify = new HashMap<String, java.lang.Object>();
			fieldsToVerify.put("id", dto.getId());
			if (!Validate.RequiredValue(fieldsToVerify).isGood()) {
				response.setStatus(functionalError.FIELD_EMPTY(Validate.getValidate().getField(), locale));
				response.setHasError(true);
				return response;
			}

			// Verifier si la role existe
			Role existingEntity = null;
			existingEntity = roleRepository.findOne(dto.getId(), false);
			if (existingEntity == null) {
				response.setStatus(functionalError.DATA_NOT_EXIST("role -> " + dto.getId(), locale));
				response.setHasError(true);
				return response;
			}

			// -----------------------------------------------------------------------
			// ----------- CHECK IF DATA IS USED
			// -----------------------------------------------------------------------

		// users
        List<Users> listOfUsers = usersRepository.findByRoleId(existingEntity.getId(), false);
        if (listOfUsers != null && !listOfUsers.isEmpty()){
          Request<UsersDto> deleteRequest = new Request<UsersDto>();
          deleteRequest.setDatas(UsersTransformer.INSTANCE.toDtos(listOfUsers));
          deleteRequest.setUser(request.getUser());
          Response<UsersDto> deleteResponse = usersBusiness.delete(deleteRequest, locale);
          if(deleteResponse.isHasError()){
            response.setStatus(deleteResponse.getStatus());
            response.setHasError(true);
            return response;
          }
        }
		// roleFonctionnalite
        List<RoleFonctionnalite> listOfRoleFonctionnalite = roleFonctionnaliteRepository.findByRoleId(existingEntity.getId(), false);
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
			roleRepository.saveAll((Iterable<Role>) items);

			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));
		}

		log.info("----end forceDelete Role-----");
		return response;
	}

	/**
	 * get Role by using RoleDto as object.
	 * 
	 * @param request
	 * @return response
	 * 
	 */
	@Override
	public Response<RoleDto> getByCriteria(Request<RoleDto> request, Locale locale)  throws Exception {
		log.info("----begin get Role-----");

		Response<RoleDto> response = new Response<RoleDto>();

		if(Utilities.blank(request.getData().getOrderField())) {
			request.getData().setOrderField("");
		}
		if(Utilities.blank(request.getData().getOrderDirection())) {
			request.getData().setOrderDirection("asc");
		}

		List<Role> items 			 = roleRepository.getByCriteria(request, em, locale);

		if(Utilities.isEmpty(items)){
			response.setStatus(functionalError.DATA_EMPTY("role", locale));
			response.setHasError(false);
			return response;
		}

		List<RoleDto> itemsDto = (Utilities.isTrue(request.getIsSimpleLoading())) ? RoleTransformer.INSTANCE.toLiteDtos(items) : RoleTransformer.INSTANCE.toDtos(items);

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
//			response.setCount(roleRepository.count(request, em, locale));
			response.setHasError(false);
			response.setStatus(functionalError.SUCCESS("", locale));

		log.info("----end get Role-----");
		return response;
	}

	/**
	 * get full RoleDto by using Role as object.
	 * 
	 * @param dto
	 * @param size
	 * @param isSimpleLoading
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	private RoleDto getFullInfos(RoleDto dto, Integer size, Boolean isSimpleLoading, Locale locale) throws Exception {
		// put code here

		List<Map<String, String>> fonctionnalites = new ArrayList<Map<String, String>>();
		List<RoleFonctionnalite> list = roleFonctionnaliteRepository.findByRoleId(dto.getId(), false);
		List<RoleFonctionnaliteDto> itemsData;
		try {
			itemsData = RoleFonctionnaliteTransformer.INSTANCE.toDtos(list);
			if (Utilities.isNotEmpty(itemsData)) {
				itemsData.forEach(fonc -> {
					Map<String, String> f = new HashMap<String, String>();
					f.put("code", fonc.getFonctionnaliteCode());
					f.put("name", fonc.getFonctionnaliteLibelle());
					fonctionnalites.add(f);
				});
			}
			dto.setFonctionnalites(fonctionnalites);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Utilities.isTrue(isSimpleLoading)) {
			return dto;
		}
		if (size > 1) {
			return dto;
		}

		return dto;
	}
}
