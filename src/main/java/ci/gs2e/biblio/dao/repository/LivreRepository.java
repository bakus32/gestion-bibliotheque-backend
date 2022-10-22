

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
import ci.gs2e.biblio.dao.repository.customize._LivreRepository;

/**
 * Repository : Livre.
 *
 * @author Smile Backend Generator
 */
@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer>, _LivreRepository {
	/**
	 * Finds Livre by using id as a search criteria.
	 * 
	 * @param id
	 * @return An Object Livre whose id is equals to the given id. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.id = :id and e.isDeleted = :isDeleted")
	Livre findOne(@Param("id")Integer id, @Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Livre by using titre as a search criteria.
	 * 
	 * @param titre
	 * @return An Object Livre whose titre is equals to the given titre. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.titre = :titre and e.isDeleted = :isDeleted")
	List<Livre> findByTitre(@Param("titre")String titre, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using maisonEdition as a search criteria.
	 * 
	 * @param maisonEdition
	 * @return An Object Livre whose maisonEdition is equals to the given maisonEdition. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.maisonEdition = :maisonEdition and e.isDeleted = :isDeleted")
	List<Livre> findByMaisonEdition(@Param("maisonEdition")String maisonEdition, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using nombreExemplaires as a search criteria.
	 * 
	 * @param nombreExemplaires
	 * @return An Object Livre whose nombreExemplaires is equals to the given nombreExemplaires. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.nombreExemplaires = :nombreExemplaires and e.isDeleted = :isDeleted")
	List<Livre> findByNombreExemplaires(@Param("nombreExemplaires")Integer nombreExemplaires, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using nombrePages as a search criteria.
	 * 
	 * @param nombrePages
	 * @return An Object Livre whose nombrePages is equals to the given nombrePages. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.nombrePages = :nombrePages and e.isDeleted = :isDeleted")
	List<Livre> findByNombrePages(@Param("nombrePages")Integer nombrePages, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using datePublication as a search criteria.
	 * 
	 * @param datePublication
	 * @return An Object Livre whose datePublication is equals to the given datePublication. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.datePublication = :datePublication and e.isDeleted = :isDeleted")
	List<Livre> findByDatePublication(@Param("datePublication")Date datePublication, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using createdAt as a search criteria.
	 * 
	 * @param createdAt
	 * @return An Object Livre whose createdAt is equals to the given createdAt. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.createdAt = :createdAt and e.isDeleted = :isDeleted")
	List<Livre> findByCreatedAt(@Param("createdAt")Date createdAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using updatedAt as a search criteria.
	 * 
	 * @param updatedAt
	 * @return An Object Livre whose updatedAt is equals to the given updatedAt. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.updatedAt = :updatedAt and e.isDeleted = :isDeleted")
	List<Livre> findByUpdatedAt(@Param("updatedAt")Date updatedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using deletedAt as a search criteria.
	 * 
	 * @param deletedAt
	 * @return An Object Livre whose deletedAt is equals to the given deletedAt. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.deletedAt = :deletedAt and e.isDeleted = :isDeleted")
	List<Livre> findByDeletedAt(@Param("deletedAt")Date deletedAt, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using createdBy as a search criteria.
	 * 
	 * @param createdBy
	 * @return An Object Livre whose createdBy is equals to the given createdBy. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.createdBy = :createdBy and e.isDeleted = :isDeleted")
	List<Livre> findByCreatedBy(@Param("createdBy")Integer createdBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using updatedBy as a search criteria.
	 * 
	 * @param updatedBy
	 * @return An Object Livre whose updatedBy is equals to the given updatedBy. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.updatedBy = :updatedBy and e.isDeleted = :isDeleted")
	List<Livre> findByUpdatedBy(@Param("updatedBy")Integer updatedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using deletedBy as a search criteria.
	 * 
	 * @param deletedBy
	 * @return An Object Livre whose deletedBy is equals to the given deletedBy. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.deletedBy = :deletedBy and e.isDeleted = :isDeleted")
	List<Livre> findByDeletedBy(@Param("deletedBy")Integer deletedBy, @Param("isDeleted")Boolean isDeleted);
	/**
	 * Finds Livre by using isDeleted as a search criteria.
	 * 
	 * @param isDeleted
	 * @return An Object Livre whose isDeleted is equals to the given isDeleted. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.isDeleted = :isDeleted")
	List<Livre> findByIsDeleted(@Param("isDeleted")Boolean isDeleted);

	/**
	 * Finds Livre by using categorieId as a search criteria.
	 * 
	 * @param categorieId
	 * @return An Object Livre whose categorieId is equals to the given categorieId. If
	 *         no Livre is found, this method returns null.
	 */
	@Query("select e from Livre e where e.categorieLivre.id = :categorieId and e.isDeleted = :isDeleted")
	List<Livre> findByCategorieId(@Param("categorieId")Integer categorieId, @Param("isDeleted")Boolean isDeleted);

  /**
   * Finds one Livre by using categorieId as a search criteria.
   *
   * @param categorieId
   * @return An Object Livre whose categorieId is equals to the given categorieId. If
   *         no Livre is found, this method returns null.
   */
  @Query("select e from Livre e where e.categorieLivre.id = :categorieId and e.isDeleted = :isDeleted")
  Livre findLivreByCategorieId(@Param("categorieId")Integer categorieId, @Param("isDeleted")Boolean isDeleted);




	/**
	 * Finds List of Livre by using livreDto as a search criteria.
	 * 
	 * @param request, em
	 * @return A List of Livre
	 * @throws DataAccessException,ParseException
	 */
	public default List<Livre> getByCriteria(Request<LivreDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception {
		String req = "select e from Livre e where e IS NOT NULL";
		HashMap<String, Object> param = new HashMap<String, Object>();
		req += getWhereExpression(request, param, locale);
				TypedQuery<Livre> query = em.createQuery(req, Livre.class);
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
	 * Finds count of Livre by using livreDto as a search criteria.
	 * 
	 * @param request, em
	 * @return Number of Livre
	 * 
	 */
	public default Long count(Request<LivreDto> request, EntityManager em, Locale locale) throws DataAccessException, Exception  {
		String req = "select count(e.id) from Livre e where e IS NOT NULL";
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
	default String getWhereExpression(Request<LivreDto> request, HashMap<String, java.lang.Object> param, Locale locale) throws Exception {
		// main query
		LivreDto dto = request.getData() != null ? request.getData() : new LivreDto();
		dto.setIsDeleted(false);
		String mainReq = generateCriteria(dto, param, 0, locale);
		// others query
		String othersReq = "";
		if (request.getDatas() != null && !request.getDatas().isEmpty()) {
			Integer index = 1;
			for (LivreDto elt : request.getDatas()) {
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
	default String generateCriteria(LivreDto dto, HashMap<String, Object> param, Integer index,  Locale locale) throws Exception{
		List<String> listOfQuery = new ArrayList<String>();
		if (dto != null) {
			if (dto.getId() != null || Utilities.searchParamIsNotEmpty(dto.getIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("id", dto.getId(), "e.id", "Integer", dto.getIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getTitre()) || Utilities.searchParamIsNotEmpty(dto.getTitreParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("titre", dto.getTitre(), "e.titre", "String", dto.getTitreParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getMaisonEdition()) || Utilities.searchParamIsNotEmpty(dto.getMaisonEditionParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("maisonEdition", dto.getMaisonEdition(), "e.maisonEdition", "String", dto.getMaisonEditionParam(), param, index, locale));
			}
			if (dto.getNombreExemplaires() != null || Utilities.searchParamIsNotEmpty(dto.getNombreExemplairesParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombreExemplaires", dto.getNombreExemplaires(), "e.nombreExemplaires", "Integer", dto.getNombreExemplairesParam(), param, index, locale));
			}
			if (dto.getNombrePages() != null || Utilities.searchParamIsNotEmpty(dto.getNombrePagesParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("nombrePages", dto.getNombrePages(), "e.nombrePages", "Integer", dto.getNombrePagesParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getDatePublication()) || Utilities.searchParamIsNotEmpty(dto.getDatePublicationParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("datePublication", dto.getDatePublication(), "e.datePublication", "Date", dto.getDatePublicationParam(), param, index, locale));
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
						if (dto.getCategorieId() != null || Utilities.searchParamIsNotEmpty(dto.getCategorieIdParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("categorieId", dto.getCategorieId(), "e.categorieLivre.id", "Integer", dto.getCategorieIdParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getCategorieLivreLibelle()) || Utilities.searchParamIsNotEmpty(dto.getCategorieLivreLibelleParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("categorieLivreLibelle", dto.getCategorieLivreLibelle(), "e.categorieLivre.libelle", "String", dto.getCategorieLivreLibelleParam(), param, index, locale));
			}
			if (Utilities.isNotBlank(dto.getCategorieLivreCode()) || Utilities.searchParamIsNotEmpty(dto.getCategorieLivreCodeParam())) {
				listOfQuery.add(CriteriaUtils.generateCriteria("categorieLivreCode", dto.getCategorieLivreCode(), "e.categorieLivre.code", "String", dto.getCategorieLivreCodeParam(), param, index, locale));
			}

			List<String> listOfCustomQuery = _generateCriteria(dto, param, index, locale);
			if (Utilities.isNotEmpty(listOfCustomQuery)) {
				listOfQuery.addAll(listOfCustomQuery);
			}
		}
		return CriteriaUtils.getCriteriaByListOfQuery(listOfQuery);
	}
}
