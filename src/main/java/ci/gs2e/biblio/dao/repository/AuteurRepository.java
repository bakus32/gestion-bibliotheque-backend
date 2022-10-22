

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
import ci.gs2e.biblio.dao.repository.customize._AuteurRepository;

/**
 * Repository : Auteur.
 *
 * @author Smile Backend Generator
 */
@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Integer>, _AuteurRepository {
	/**
	 * Finds Auteur by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Auteur whose id is equals to the given id. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.id = :id and e.isDeleted = :isDeleted")
	Auteur findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Auteur by using nom as a search criteria.
	 * 
	 * @param nom
	 * @return An Object Auteur whose nom is equals to the given nom. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.nom = :nom and e.isDeleted = :isDeleted")
	List<Auteur> findByNom(@Param("nom")String nom, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using prenoms as a search criteria.
	 * 
	 * @param prenoms
	 * @return An Object Auteur whose prenoms is equals to the given prenoms. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.prenoms = :prenoms and e.isDeleted = :isDeleted")
	List<Auteur> findByPrenoms(@Param("prenoms")String prenoms, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using specialite as a search criteria.
	 * 
	 * @param specialite
	 * @return An Object Auteur whose specialite is equals to the given specialite. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.specialite = :specialite and e.isDeleted = :isDeleted")
	List<Auteur> findBySpecialite(@Param("specialite")String specialite, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Auteur whose createdAt is equals to the given createdAt. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Auteur> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Auteur whose updatedAt is equals to the given updatedAt. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Auteur> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Auteur whose deletedAt is equals to the given deletedAt. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Auteur> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Auteur whose createdBy is equals to the given createdBy. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Auteur> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Auteur whose updatedBy is equals to the given updatedBy. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Auteur> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Auteur whose deletedBy is equals to the given deletedBy. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Auteur> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Auteur by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Auteur whose isDeleted is equals to the given isDeleted. If
	 *         no Auteur is found, this method returns null.
	 */
	@Query("select e from Auteur e where e.isDeleted = :isDeleted")
	List<Auteur> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);



	/**
	 * Finds List of Auteur by using auteurDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Auteur
	 * @throws DataAccessException,ParseException
	 */
	public default List<Auteur> getByCriteria(Request<AuteurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Auteur e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				TypedQuery<Auteur> query = em.createQuery(req, Auteur.class);
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
	 * Finds count of Auteur by using auteurDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Auteur
	 * 
	 */
	public default Long count(Request<AuteurDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Auteur e where e IS NOT NULL";
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
	default String getWhereExpression(Request<AuteurDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		AuteurDto dto = request.getData() != null ? request.getData() : new AuteurDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (AuteurDto elt : request.getDatas()) {
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
	default String generateCriteria(AuteurDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
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
			if (Utilities.isNotBlank(dto.getSpecialite()) || Utilities.searchParamIsNotEmpty(dto.getSpecialiteParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("specialite", dto.getSpecialite(), "e.specialite", "String", dto.getSpecialiteParam(), param, index, locale));
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

			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
