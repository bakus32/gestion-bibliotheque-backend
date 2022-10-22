

package ci.gs2e.biblio.dao.repository;

import java.util.Date;
import java.util.List;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Locale;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ci.gs2e.biblio.helper.*;
import ci.gs2e.biblio.helper.dto.*;
import ci.gs2e.biblio.helper.contrat.*;
import ci.gs2e.biblio.helper.contrat.Request;
import ci.gs2e.biblio.helper.contrat.Response;
import ci.gs2e.biblio.dao.entity.*;
import ci.gs2e.biblio.dao.repository.customize._UsagerRepository;

/**
 * Repository : Usager.
 *
 * @author Smile Backend Generator
 */
@Repository
public interface UsagerRepository extends JpaRepository<Usager, Integer>, _UsagerRepository {
	/**
	 * Finds Usager by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Usager whose id is equals to the given id. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.id = :id and e.isDeleted = :isDeleted")
	Usager findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Usager by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Usager whose nom is equals to the given nom. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Usager> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using prenoms as a search criteria.
	 * 
	 * @param prenoms
	 * @return An Object Usager whose prenoms is equals to the given prenoms. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.prenoms = :prenoms and e.isDeleted = :isDeleted")
	List<Usager> findByPrenoms(@Param("prenoms")String prenoms, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using login as a search criteria.
	 * 
	 * @param login
	 * @return An Object Usager whose login is equals to the given login. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.login = :login and e.isDeleted = :isDeleted")
	Usager findByLogin(@Param("login")String login, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using passwd as a search criteria.
	 * 
	 * @param passwd
	 * @return An Object Usager whose passwd is equals to the given passwd. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.passwd = :passwd and e.isDeleted = :isDeleted")
	List<Usager> findByPasswd(@Param("passwd")String passwd, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using email as a search criteria.
	 * 
	 * @param email
	 * @return An Object Usager whose email is equals to the given email. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.email = :email and e.isDeleted = :isDeleted")
	Usager findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using matricule as a search criteria.
	 * 
	 * @param matricule
	 * @return An Object Usager whose matricule is equals to the given matricule. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.matricule = :matricule and e.isDeleted = :isDeleted")
	Usager findByMatricule(@Param("matricule")String matricule, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object Usager whose roleId is equals to the given roleId. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.roleId = :roleId and e.isDeleted = :isDeleted")
	List<Usager> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Usager whose createdAt is equals to the given createdAt. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Usager> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Usager whose updatedAt is equals to the given updatedAt. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Usager> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Usager whose deletedAt is equals to the given deletedAt. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Usager> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Usager whose createdBy is equals to the given createdBy. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Usager> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Usager whose updatedBy is equals to the given updatedBy. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Usager> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Usager whose deletedBy is equals to the given deletedBy. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Usager> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Usager whose isDeleted is equals to the given isDeleted. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.isDeleted = :isDeleted")
	List<Usager> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using contact as a search criteria.
	 * 
	 * @param contact
	 * @return An Object Usager whose contact is equals to the given contact. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.contact = :contact and e.isDeleted = :isDeleted")
	List<Usager> findByContact(@Param("contact")String contact, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using isConnected as a search criteria.
	 * 
	 * @param isConnected
	 * @return An Object Usager whose isConnected is equals to the given isConnected. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.isConnected = :isConnected and e.isDeleted = :isDeleted")
	List<Usager> findByIsConnected(@Param("isConnected")Boolean isConnected, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using isLocked as a search criteria.
	 * 
	 * @param isLocked
	 * @return An Object Usager whose isLocked is equals to the given isLocked. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.isLocked = :isLocked and e.isDeleted = :isDeleted")
	List<Usager> findByIsLocked(@Param("isLocked")Boolean isLocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using isDefaultPassword as a search criteria.
	 * 
	 * @param isDefaultPassword
	 * @return An Object Usager whose isDefaultPassword is equals to the given isDefaultPassword. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.isDefaultPassword = :isDefaultPassword and e.isDeleted = :isDeleted")
	List<Usager> findByIsDefaultPassword(@Param("isDefaultPassword")Boolean isDefaultPassword, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using lastConnectionDate as a search criteria.
	 * 
	 * @param lastConnectionDate
	 * @return An Object Usager whose lastConnectionDate is equals to the given lastConnectionDate. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.lastConnectionDate = :lastConnectionDate and e.isDeleted = :isDeleted")
	List<Usager> findByLastConnectionDate(@Param("lastConnectionDate")Date lastConnectionDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Usager by using isFirstConnection as a search criteria.
	 * 
	 * @param isFirstConnection
	 * @return An Object Usager whose isFirstConnection is equals to the given isFirstConnection. If
	 *         no Usager is found, this method returns null.
	 */
	@Query("select e from Usager e where e.isFirstConnection = :isFirstConnection and e.isDeleted = :isDeleted")
	List<Usager> findByIsFirstConnection(@Param("isFirstConnection")Boolean isFirstConnection, @Param("isDeleted")Boolean isDeleted);



	/**
	 * Finds List of Usager by using usagerDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Usager
	 * @throws DataAccessException,ParseException
	 */
	public default List<Usager> getByCriteria(Request<UsagerDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Usager e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				TypedQuery<Usager> query = em.createQuery(req, Usager.class);
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		if (request.getIndex() != null && request.getSize() != null) {
			query.setFirstResult(request.getIndex() * request.getSize());
			query.setMaxResults(request.getSize());
		}
		return query.getResultList();
	}

	/**
	 * Finds count of Usager by using usagerDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Usager
	 * 
	 */
	public default Long count(Request<UsagerDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Usager e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				javax.persistence.Query query = em.createQuery(req);
		for (Map.Entry<String, Object> entry : param.entrySet()) {
			query.setParameter(entry.getKey(), entry.getValue());
		}
		Long count = (Long) query.getResultList().get(0);
		return count;
	}

	/**
	 * get where expression
	 * @param request
	 * @param param
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String getWhereExpression(Request<UsagerDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		UsagerDto dto = request.getData() != null ? request.getData() : new UsagerDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (UsagerDto elt : request.getDatas()) {
				elt.setIsDeleted(false);
				String eltReq = generateCriteria(elt, param, index, locale);
				if (request.getIsAnd() != null && request.getIsAnd()) {
					othersReq += "and (" + eltReq + ") ";
				} else {
					othersReq += "or (" + eltReq + ") ";
				}
				index++;
			}
		}
		String req = "";
		if (!mainReq.isEmpty()) {
			req += " and (" + mainReq + ") ";
		}
		req += othersReq;
		
		//order
		if(Direction.fromOptionalString(dto.getOrderDirection()).orElse(null) != null && Utilities.notBlank(dto.getOrderField())) {
			req += " order by e."+dto.getOrderField()+" "+dto.getOrderDirection();
		}
		else {
			req += " order by  e.id desc";
		}
		return req;
	}

	/**
	 * generate sql query for dto
	 * @param dto
	 * @param param
	 * @param index
	 * @param locale
	 * @return
	 * @throws Exception
	 */
	default String generateCriteria(UsagerDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getNom()) || Utilities.searchParamIsNotEmpty(dto.getNomParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nom", dto.getNom(), "e.nom", "String", dto.getNomParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getPrenoms()) || Utilities.searchParamIsNotEmpty(dto.getPrenomsParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("prenoms", dto.getPrenoms(), "e.prenoms", "String", dto.getPrenomsParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getLogin()) || Utilities.searchParamIsNotEmpty(dto.getLoginParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("login", dto.getLogin(), "e.login", "String", dto.getLoginParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getPasswd()) || Utilities.searchParamIsNotEmpty(dto.getPasswdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("passwd", dto.getPasswd(), "e.passwd", "String", dto.getPasswdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getEmail()) || Utilities.searchParamIsNotEmpty(dto.getEmailParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("email", dto.getEmail(), "e.email", "String", dto.getEmailParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getMatricule()) || Utilities.searchParamIsNotEmpty(dto.getMatriculeParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("matricule", dto.getMatricule(), "e.matricule", "String", dto.getMatriculeParam(), param, index, locale));
			}
			if (dto.getRoleId() != null || Utilities.searchParamIsNotEmpty(dto.getRoleIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.roleId", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getCreatedAt()) || Utilities.searchParamIsNotEmpty(dto.getCreatedAtParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdAt", dto.getCreatedAt(), "e.createdAt", "Date", dto.getCreatedAtParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getUpdatedAt()) || Utilities.searchParamIsNotEmpty(dto.getUpdatedAtParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedAt", dto.getUpdatedAt(), "e.updatedAt", "Date", dto.getUpdatedAtParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getDeletedAt()) || Utilities.searchParamIsNotEmpty(dto.getDeletedAtParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedAt", dto.getDeletedAt(), "e.deletedAt", "Date", dto.getDeletedAtParam(), param, index, locale));
			}
			if (dto.getCreatedBy() != null || Utilities.searchParamIsNotEmpty(dto.getCreatedByParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("createdBy", dto.getCreatedBy(), "e.createdBy", "Integer", dto.getCreatedByParam(), param, index, locale));
			}
			if (dto.getUpdatedBy() != null || Utilities.searchParamIsNotEmpty(dto.getUpdatedByParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("updatedBy", dto.getUpdatedBy(), "e.updatedBy", "Integer", dto.getUpdatedByParam(), param, index, locale));
			}
			if (dto.getDeletedBy() != null || Utilities.searchParamIsNotEmpty(dto.getDeletedByParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("deletedBy", dto.getDeletedBy(), "e.deletedBy", "Integer", dto.getDeletedByParam(), param, index, locale));
			}
			if (dto.getIsDeleted() != null || Utilities.searchParamIsNotEmpty(dto.getIsDeletedParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getContact()) || Utilities.searchParamIsNotEmpty(dto.getContactParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("contact", dto.getContact(), "e.contact", "String", dto.getContactParam(), param, index, locale));
			}
			if (dto.getIsConnected() != null || Utilities.searchParamIsNotEmpty(dto.getIsConnectedParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isConnected", dto.getIsConnected(), "e.isConnected", "Boolean", dto.getIsConnectedParam(), param, index, locale));
			}
			if (dto.getIsLocked() != null || Utilities.searchParamIsNotEmpty(dto.getIsLockedParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isLocked", dto.getIsLocked(), "e.isLocked", "Boolean", dto.getIsLockedParam(), param, index, locale));
			}
			if (dto.getIsDefaultPassword() != null || Utilities.searchParamIsNotEmpty(dto.getIsDefaultPasswordParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDefaultPassword", dto.getIsDefaultPassword(), "e.isDefaultPassword", "Boolean", dto.getIsDefaultPasswordParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getLastConnectionDate()) || Utilities.searchParamIsNotEmpty(dto.getLastConnectionDateParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastConnectionDate", dto.getLastConnectionDate(), "e.lastConnectionDate", "Date", dto.getLastConnectionDateParam(), param, index, locale));
			}
			if (dto.getIsFirstConnection() != null || Utilities.searchParamIsNotEmpty(dto.getIsFirstConnectionParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isFirstConnection", dto.getIsFirstConnection(), "e.isFirstConnection", "Boolean", dto.getIsFirstConnectionParam(), param, index, locale));
			}

			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
