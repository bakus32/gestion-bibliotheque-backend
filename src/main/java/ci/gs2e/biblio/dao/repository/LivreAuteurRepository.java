

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
import ci.gs2e.biblio.dao.repository.customize._LivreAuteurRepository;

/**
 * Repository : LivreAuteur.
 *
 * @author Smile Backend Generator
 */
@Repository
public interface LivreAuteurRepository extends JpaRepository<LivreAuteur, Integer>, _LivreAuteurRepository {
	/**
	 * Finds LivreAuteur by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object LivreAuteur whose id is equals to the given id. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.id = :id and e.isDeleted = :isDeleted")
	LivreAuteur findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds LivreAuteur by using code as a search criteria.
	 * 
	 * @param code
	 * @return An Object LivreAuteur whose code is equals to the given code. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.code = :code and e.isDeleted = :isDeleted")
	LivreAuteur findByCode(@Param("code")String code, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds LivreAuteur by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object LivreAuteur whose createdAt is equals to the given createdAt. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds LivreAuteur by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object LivreAuteur whose updatedAt is equals to the given updatedAt. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds LivreAuteur by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object LivreAuteur whose deletedAt is equals to the given deletedAt. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds LivreAuteur by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object LivreAuteur whose createdBy is equals to the given createdBy. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds LivreAuteur by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object LivreAuteur whose updatedBy is equals to the given updatedBy. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds LivreAuteur by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object LivreAuteur whose deletedBy is equals to the given deletedBy. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds LivreAuteur by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object LivreAuteur whose isDeleted is equals to the given isDeleted. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.isDeleted = :isDeleted")
	List<LivreAuteur> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds LivreAuteur by using auteurId as a search criteria.
	 * 
	 * @param auteurId
	 * @return An Object LivreAuteur whose auteurId is equals to the given auteurId. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.auteur.id = :auteurId and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByAuteurId(@Param("auteurId")Integer auteurId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one LivreAuteur by using auteurId as a search criteria.
   *
   * @param auteurId
   * @return An Object LivreAuteur whose auteurId is equals to the given auteurId. If
   *         no LivreAuteur is found, this method returns null.
   */
  @Query("select e from LivreAuteur e where e.auteur.id = :auteurId and e.isDeleted = :isDeleted")
  LivreAuteur findLivreAuteurByAuteurId(@Param("auteurId")Integer auteurId, @Param("isDeleted")Boolean isDeleted);


	/**
	 * Finds LivreAuteur by using livreId as a search criteria.
	 * 
	 * @param livreId
	 * @return An Object LivreAuteur whose livreId is equals to the given livreId. If
	 *         no LivreAuteur is found, this method returns null.
	 */
	@Query("select e from LivreAuteur e where e.livre.id = :livreId and e.isDeleted = :isDeleted")
	List<LivreAuteur> findByLivreId(@Param("livreId")Integer livreId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one LivreAuteur by using livreId as a search criteria.
   *
   * @param livreId
   * @return An Object LivreAuteur whose livreId is equals to the given livreId. If
   *         no LivreAuteur is found, this method returns null.
   */
  @Query("select e from LivreAuteur e where e.livre.id = :livreId and e.isDeleted = :isDeleted")
  LivreAuteur findLivreAuteurByLivreId(@Param("livreId")Integer livreId, @Param("isDeleted")Boolean isDeleted);




	/**
	 * Finds List of LivreAuteur by using livreAuteurDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of LivreAuteur
	 * @throws DataAccessException,ParseException
	 */
	public default List<LivreAuteur> getByCriteria(Request<LivreAuteurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from LivreAuteur e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				TypedQuery<LivreAuteur> query = em.createQuery(req, LivreAuteur.class);
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
	 * Finds count of LivreAuteur by using livreAuteurDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of LivreAuteur
	 * 
	 */
	public default Long count(Request<LivreAuteurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from LivreAuteur e where e IS NOT NULL";
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
	default String getWhereExpression(Request<LivreAuteurDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		LivreAuteurDto dto = request.getData() != null ? request.getData() : new LivreAuteurDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (LivreAuteurDto elt : request.getDatas()) {
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
	default String generateCriteria(LivreAuteurDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getCode()) || Utilities.searchParamIsNotEmpty(dto.getCodeParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("code", dto.getCode(), "e.code", "String", dto.getCodeParam(), param, index, locale));
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
						if (dto.getAuteurId() != null || Utilities.searchParamIsNotEmpty(dto.getAuteurIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("auteurId", dto.getAuteurId(), "e.auteur.id", "Integer", dto.getAuteurIdParam(), param, index, locale));
			}
						if (dto.getLivreId() != null || Utilities.searchParamIsNotEmpty(dto.getLivreIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("livreId", dto.getLivreId(), "e.livre.id", "Integer", dto.getLivreIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getAuteurNom()) || Utilities.searchParamIsNotEmpty(dto.getAuteurNomParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("auteurNom", dto.getAuteurNom(), "e.auteur.nom", "String", dto.getAuteurNomParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getAuteurPrenoms()) || Utilities.searchParamIsNotEmpty(dto.getAuteurPrenomsParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("auteurPrenoms", dto.getAuteurPrenoms(), "e.auteur.prenoms", "String", dto.getAuteurPrenomsParam(), param, index, locale));
			}

			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
