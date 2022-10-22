

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
import ci.gs2e.biblio.dao.repository.customize._RoleFonctionnaliteRepository;

/**
 * Repository : RoleFonctionnalite.
 *
 * @author Smile Backend Generator
 */
@Repository
public interface RoleFonctionnaliteRepository extends JpaRepository<RoleFonctionnalite, Integer>, _RoleFonctionnaliteRepository {
	/**
	 * Finds RoleFonctionnalite by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object RoleFonctionnalite whose id is equals to the given id. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.id = :id and e.isDeleted = :isDeleted")
	RoleFonctionnalite findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleFonctionnalite by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object RoleFonctionnalite whose code is equals to the given code. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.code = :code and e.isDeleted = :isDeleted")
	RoleFonctionnalite findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using libelle as a search criteria.
	 * 
	 * @param libelle
	 * @return An Object RoleFonctionnalite whose libelle is equals to the given libelle. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.libelle = :libelle and e.isDeleted = :isDeleted")
	RoleFonctionnalite findByLibelle(@Param("libelle")String libelle, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object RoleFonctionnalite whose createdAt is equals to the given createdAt. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object RoleFonctionnalite whose updatedAt is equals to the given updatedAt. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object RoleFonctionnalite whose deletedAt is equals to the given deletedAt. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object RoleFonctionnalite whose isDeleted is equals to the given isDeleted. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object RoleFonctionnalite whose createdBy is equals to the given createdBy. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object RoleFonctionnalite whose updatedBy is equals to the given updatedBy. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds RoleFonctionnalite by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object RoleFonctionnalite whose deletedBy is equals to the given deletedBy. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds RoleFonctionnalite by using fonctionnaliteId as a search criteria.
	 * 
	 * @param fonctionnaliteId
	 * @return An Object RoleFonctionnalite whose fonctionnaliteId is equals to the given fonctionnaliteId. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.fonctionnalite.id = :fonctionnaliteId and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByFonctionnaliteId(@Param("fonctionnaliteId")Integer fonctionnaliteId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one RoleFonctionnalite by using fonctionnaliteId as a search criteria.
   *
   * @param fonctionnaliteId
   * @return An Object RoleFonctionnalite whose fonctionnaliteId is equals to the given fonctionnaliteId. If
   *         no RoleFonctionnalite is found, this method returns null.
   */
  @Query("select e from RoleFonctionnalite e where e.fonctionnalite.id = :fonctionnaliteId and e.isDeleted = :isDeleted")
  RoleFonctionnalite findRoleFonctionnaliteByFonctionnaliteId(@Param("fonctionnaliteId")Integer fonctionnaliteId, @Param("isDeleted")Boolean isDeleted);


	/**
	 * Finds RoleFonctionnalite by using roleId as a search criteria.
	 * 
	 * @param roleId
	 * @return An Object RoleFonctionnalite whose roleId is equals to the given roleId. If
	 *         no RoleFonctionnalite is found, this method returns null.
	 */
	@Query("select e from RoleFonctionnalite e where e.role.id = :roleId and e.isDeleted = :isDeleted")
	List<RoleFonctionnalite> findByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one RoleFonctionnalite by using roleId as a search criteria.
   *
   * @param roleId
   * @return An Object RoleFonctionnalite whose roleId is equals to the given roleId. If
   *         no RoleFonctionnalite is found, this method returns null.
   */
  @Query("select e from RoleFonctionnalite e where e.role.id = :roleId and e.isDeleted = :isDeleted")
  RoleFonctionnalite findRoleFonctionnaliteByRoleId(@Param("roleId")Integer roleId, @Param("isDeleted")Boolean isDeleted);




	/**
	 * Finds List of RoleFonctionnalite by using roleFonctionnaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of RoleFonctionnalite
	 * @throws DataAccessException,ParseException
	 */
	public default List<RoleFonctionnalite> getByCriteria(Request<RoleFonctionnaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from RoleFonctionnalite e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				TypedQuery<RoleFonctionnalite> query = em.createQuery(req, RoleFonctionnalite.class);
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
	 * Finds count of RoleFonctionnalite by using roleFonctionnaliteDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of RoleFonctionnalite
	 * 
	 */
	public default Long count(Request<RoleFonctionnaliteDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from RoleFonctionnalite e where e IS NOT NULL";
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
	default String getWhereExpression(Request<RoleFonctionnaliteDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		RoleFonctionnaliteDto dto = request.getData() != null ? request.getData() : new RoleFonctionnaliteDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (RoleFonctionnaliteDto elt : request.getDatas()) {
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
	default String generateCriteria(RoleFonctionnaliteDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getCode()) || Utilities.searchParamIsNotEmpty(dto.getCodeParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getLibelle()) || Utilities.searchParamIsNotEmpty(dto.getLibelleParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("libelle", dto.getLibelle(), "e.libelle", "String", dto.getLibelleParam(), param, index, locale));
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
			if (dto.getIsDeleted() != null || Utilities.searchParamIsNotEmpty(dto.getIsDeletedParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("isDeleted", dto.getIsDeleted(), "e.isDeleted", "Boolean", dto.getIsDeletedParam(), param, index, locale));
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
						if (dto.getFonctionnaliteId() != null || Utilities.searchParamIsNotEmpty(dto.getFonctionnaliteIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionnaliteId", dto.getFonctionnaliteId(), "e.fonctionnalite.id", "Integer", dto.getFonctionnaliteIdParam(), param, index, locale));
			}
						if (dto.getRoleId() != null || Utilities.searchParamIsNotEmpty(dto.getRoleIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("roleId", dto.getRoleId(), "e.role.id", "Integer", dto.getRoleIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getFonctionnaliteLibelle()) || Utilities.searchParamIsNotEmpty(dto.getFonctionnaliteLibelleParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionnaliteLibelle", dto.getFonctionnaliteLibelle(), "e.fonctionnalite.libelle", "String", dto.getFonctionnaliteLibelleParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getFonctionnaliteCode()) || Utilities.searchParamIsNotEmpty(dto.getFonctionnaliteCodeParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("fonctionnaliteCode", dto.getFonctionnaliteCode(), "e.fonctionnalite.code", "String", dto.getFonctionnaliteCodeParam(), param, index, locale));
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
