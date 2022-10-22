

package ci.gs2e.biblio.dao.repository;

import java.util.Date;
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
import ci.gs2e.biblio.dao.repository.customize._UsersRepository;

/**
 * Repository : Users.
 *
 * @author Smile Backend Generator
 */
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer>, _UsersRepository {
	/**
	 * Finds Users by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Users whose id is equals to the given id. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.id = :id and e.isDeleted = :isDeleted")
	Users findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Users by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Users whose nom is equals to the given nom. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Users> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using prenoms as a search criteria.
	 * 
	 * @param prenoms
	 * @return An Object Users whose prenoms is equals to the given prenoms. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.prenoms = :prenoms and e.isDeleted = :isDeleted")
	List<Users> findByPrenoms(@Param("prenoms")String prenoms, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using login as a search criteria.
	 * 
	 * @param login
	 * @return An Object Users whose login is equals to the given login. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.login = :login and e.isDeleted = :isDeleted")
	Users findByLogin(@Param("login")String login, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using passwd as a search criteria.
	 * 
	 * @param passwd
	 * @return An Object Users whose passwd is equals to the given passwd. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.passwd = :passwd and e.isDeleted = :isDeleted")
	List<Users> findByPasswd(@Param("passwd")String passwd, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using email as a search criteria.
	 * 
	 * @param email
	 * @return An Object Users whose email is equals to the given email. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.email = :email and e.isDeleted = :isDeleted")
	Users findByEmail(@Param("email")String email, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using matricule as a search criteria.
	 * 
	 * @param matricule
	 * @return An Object Users whose matricule is equals to the given matricule. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.matricule = :matricule and e.isDeleted = :isDeleted")
	Users findByMatricule(@Param("matricule")String matricule, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Users whose createdAt is equals to the given createdAt. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Users> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Users whose updatedAt is equals to the given updatedAt. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Users> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Users whose deletedAt is equals to the given deletedAt. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Users> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Users whose createdBy is equals to the given createdBy. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Users> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Users whose updatedBy is equals to the given updatedBy. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Users> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Users whose deletedBy is equals to the given deletedBy. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Users> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using lastConnectionDate as a search criteria.
	 * 
	 * @param lastConnectionDate
	 * @return An Object Users whose lastConnectionDate is equals to the given lastConnectionDate. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.lastConnectionDate = :lastConnectionDate and e.isDeleted = :isDeleted")
	List<Users> findByLastConnectionDate(@Param("lastConnectionDate")Date lastConnectionDate, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using isConnected as a search criteria.
	 * 
	 * @param isConnected
	 * @return An Object Users whose isConnected is equals to the given isConnected. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.isConnected = :isConnected and e.isDeleted = :isDeleted")
	List<Users> findByIsConnected(@Param("isConnected")Boolean isConnected, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using isLocked as a search criteria.
	 * 
	 * @param isLocked
	 * @return An Object Users whose isLocked is equals to the given isLocked. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.isLocked = :isLocked and e.isDeleted = :isDeleted")
	List<Users> findByIsLocked(@Param("isLocked")Boolean isLocked, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Users whose isDeleted is equals to the given isDeleted. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.isDeleted = :isDeleted")
	List<Users> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using isDefaultPassword as a search criteria.
	 * 
	 * @param isDefaultPassword
	 * @return An Object Users whose isDefaultPassword is equals to the given isDefaultPassword. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.isDefaultPassword = :isDefaultPassword and e.isDeleted = :isDeleted")
	List<Users> findByIsDefaultPassword(@Param("isDefaultPassword")Boolean isDefaultPassword, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using isFirstConnection as a search criteria.
	 * 
	 * @param isFirstConnection
	 * @return An Object Users whose isFirstConnection is equals to the given isFirstConnection. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.isFirstConnection = :isFirstConnection and e.isDeleted = :isDeleted")
	List<Users> findByIsFirstConnection(@Param("isFirstConnection")Boolean isFirstConnection, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Users by using contact as a search criteria.
	 * 
	 * @param contact
	 * @return An Object Users whose contact is equals to the given contact. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.contact = :contact and e.isDeleted = :isDeleted")
	List<Users> findByContact(@Param("contact")String contact, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Users by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object Users whose roleId is equals to the given roleId. If
	 *         no Users is found, this method returns null.
	 */
	@Query("select e from Users e where e.role.id = :roleId and e.isDeleted = :isDeleted")
	List<Users> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Users by using roleId as a search criteria.
   *
   * @param roleId
   * @return An Object Users whose roleId is equals to the given roleId. If
   *         no Users is found, this method returns null.
   */
  @Query("select e from Users e where e.role.id = :roleId and e.isDeleted = :isDeleted")
  Users findUsersByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);




	/**
	 * Finds List of Users by using usersDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Users
	 * @throws DataAccessException,ParseException
	 */
	public default List<Users> getByCriteria(Request<UsersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Users e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				TypedQuery<Users> query = em.createQuery(req, Users.class);
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
	 * Finds count of Users by using usersDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Users
	 * 
	 */
	public default Long count(Request<UsersDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Users e where e IS NOT NULL";
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
	default String getWhereExpression(Request<UsersDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		UsersDto dto = request.getData() != null ? request.getData() : new UsersDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (UsersDto elt : request.getDatas()) {
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
	default String generateCriteria(UsersDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
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
			if (Utilities.isNotBlank(dto.getLastConnectionDate()) || Utilities.searchParamIsNotEmpty(dto.getLastConnectionDateParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("lastConnectionDate", dto.getLastConnectionDate(), "e.lastConnectionDate", "Date", dto.getLastConnectionDateParam(), param, index, locale));
			}
			if (dto.getIsConnected() != null || Utilities.searchParamIsNotEmpty(dto.getIsConnectedParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isConnected", dto.getIsConnected(), "e.isConnected", "Boolean", dto.getIsConnectedParam(), param, index, locale));
			}
			if (dto.getIsLocked() != null || Utilities.searchParamIsNotEmpty(dto.getIsLockedParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isLocked", dto.getIsLocked(), "e.isLocked", "Boolean", dto.getIsLockedParam(), param, index, locale));
			}
			if (dto.getIsDeleted() != null || Utilities.searchParamIsNotEmpty(dto.getIsDeletedParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
			}
			if (dto.getIsDefaultPassword() != null || Utilities.searchParamIsNotEmpty(dto.getIsDefaultPasswordParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDefaultPassword", dto.getIsDefaultPassword(), "e.isDefaultPassword", "Boolean", dto.getIsDefaultPasswordParam(), param, index, locale));
			}
			if (dto.getIsFirstConnection() != null || Utilities.searchParamIsNotEmpty(dto.getIsFirstConnectionParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isFirstConnection", dto.getIsFirstConnection(), "e.isFirstConnection", "Boolean", dto.getIsFirstConnectionParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getContact()) || Utilities.searchParamIsNotEmpty(dto.getContactParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("contact", dto.getContact(), "e.contact", "String", dto.getContactParam(), param, index, locale));
			}
						if (dto.getRoleId() != null || Utilities.searchParamIsNotEmpty(dto.getRoleIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.role.id", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getRoleLibelle()) || Utilities.searchParamIsNotEmpty(dto.getRoleLibelleParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleLibelle", dto.getRoleLibelle(), "e.role.libelle", "String", dto.getRoleLibelleParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getRoleCode()) || Utilities.searchParamIsNotEmpty(dto.getRoleCodeParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleCode", dto.getRoleCode(), "e.role.code", "String", dto.getRoleCodeParam(), param, index, locale));
			}

			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
