

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
import ci.gs2e.biblio.dao.repository.customize._EmpruntRepository;

/**
 * Repository : Emprunt.
 *
 * @author Smile Backend Generator
 */
@Repository
public interface EmpruntRepository extends JpaRepository<Emprunt, Integer>, _EmpruntRepository {
	/**
	 * Finds Emprunt by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Emprunt whose id is equals to the given id. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.id = :id and e.isDeleted = :isDeleted")
	Emprunt findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Emprunt by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Emprunt whose createdAt is equals to the given createdAt. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Emprunt> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Emprunt whose updatedAt is equals to the given updatedAt. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Emprunt> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Emprunt whose deletedAt is equals to the given deletedAt. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Emprunt> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Emprunt whose isDeleted is equals to the given isDeleted. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.isDeleted = :isDeleted")
	List<Emprunt> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Emprunt whose createdBy is equals to the given createdBy. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Emprunt> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Emprunt whose updatedBy is equals to the given updatedBy. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Emprunt> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Emprunt whose deletedBy is equals to the given deletedBy. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Emprunt> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using dateEmprunt as a search criteria.
	 * 
	 * @param dateEmprunt
	 * @return An Object Emprunt whose dateEmprunt is equals to the given dateEmprunt. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.dateEmprunt = :dateEmprunt and e.isDeleted = :isDeleted")
	List<Emprunt> findByDateEmprunt(@Param("dateEmprunt")Date dateEmprunt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Emprunt by using dateRetour as a search criteria.
	 * 
	 * @param dateRetour
	 * @return An Object Emprunt whose dateRetour is equals to the given dateRetour. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.dateRetour = :dateRetour and e.isDeleted = :isDeleted")
	List<Emprunt> findByDateRetour(@Param("dateRetour")Date dateRetour, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Emprunt by using livreId as a search criteria.
	 * 
	 * @param livreId
	 * @return An Object Emprunt whose livreId is equals to the given livreId. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.livre.id = :livreId and e.isDeleted = :isDeleted")
	List<Emprunt> findByLivreId(@Param("livreId")Integer livreId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Emprunt by using livreId as a search criteria.
   *
   * @param livreId
   * @return An Object Emprunt whose livreId is equals to the given livreId. If
   *         no Emprunt is found, this method returns null.
   */
  @Query("select e from Emprunt e where e.livre.id = :livreId and e.isDeleted = :isDeleted")
  Emprunt findEmpruntByLivreId(@Param("livreId")Integer livreId, @Param("isDeleted")Boolean isDeleted);


	/**
	 * Finds Emprunt by using usagerId as a search criteria.
	 * 
	 * @param usagerId
	 * @return An Object Emprunt whose usagerId is equals to the given usagerId. If
	 *         no Emprunt is found, this method returns null.
	 */
	@Query("select e from Emprunt e where e.usager.id = :usagerId and e.isDeleted = :isDeleted")
	List<Emprunt> findByUsagerId(@Param("usagerId")Integer usagerId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Emprunt by using usagerId as a search criteria.
   *
   * @param usagerId
   * @return An Object Emprunt whose usagerId is equals to the given usagerId. If
   *         no Emprunt is found, this method returns null.
   */
  @Query("select e from Emprunt e where e.usager.id = :usagerId and e.isDeleted = :isDeleted")
  Emprunt findEmpruntByUsagerId(@Param("usagerId")Integer usagerId, @Param("isDeleted")Boolean isDeleted);




	/**
	 * Finds List of Emprunt by using empruntDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Emprunt
	 * @throws DataAccessException,ParseException
	 */
	public default List<Emprunt> getByCriteria(Request<EmpruntDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Emprunt e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				TypedQuery<Emprunt> query = em.createQuery(req, Emprunt.class);
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
	 * Finds count of Emprunt by using empruntDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Emprunt
	 * 
	 */
	public default Long count(Request<EmpruntDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Emprunt e where e IS NOT NULL";
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
	default String getWhereExpression(Request<EmpruntDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		EmpruntDto dto = request.getData() != null ? request.getData() : new EmpruntDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (EmpruntDto elt : request.getDatas()) {
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
	default String generateCriteria(EmpruntDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
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
			if (Utilities.isNotBlank(dto.getDateEmprunt()) || Utilities.searchParamIsNotEmpty(dto.getDateEmpruntParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateEmprunt", dto.getDateEmprunt(), "e.dateEmprunt", "Date", dto.getDateEmpruntParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getDateRetour()) || Utilities.searchParamIsNotEmpty(dto.getDateRetourParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("dateRetour", dto.getDateRetour(), "e.dateRetour", "Date", dto.getDateRetourParam(), param, index, locale));
			}
						if (dto.getLivreId() != null || Utilities.searchParamIsNotEmpty(dto.getLivreIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("livreId", dto.getLivreId(), "e.livre.id", "Integer", dto.getLivreIdParam(), param, index, locale));
			}
						if (dto.getUsagerId() != null || Utilities.searchParamIsNotEmpty(dto.getUsagerIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("usagerId", dto.getUsagerId(), "e.usager.id", "Integer", dto.getUsagerIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getUsagerNom()) || Utilities.searchParamIsNotEmpty(dto.getUsagerNomParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("usagerNom", dto.getUsagerNom(), "e.usager.nom", "String", dto.getUsagerNomParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getUsagerPrenoms()) || Utilities.searchParamIsNotEmpty(dto.getUsagerPrenomsParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("usagerPrenoms", dto.getUsagerPrenoms(), "e.usager.prenoms", "String", dto.getUsagerPrenomsParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getUsagerLogin()) || Utilities.searchParamIsNotEmpty(dto.getUsagerLoginParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("usagerLogin", dto.getUsagerLogin(), "e.usager.login", "String", dto.getUsagerLoginParam(), param, index, locale));
			}

			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
