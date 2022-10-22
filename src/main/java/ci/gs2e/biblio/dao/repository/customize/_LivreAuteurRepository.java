package ci.gs2e.biblio.dao.repository.customize;

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

/**
 * Repository customize : LivreAuteur.
 *
 * @author Smile Backend Generator
 *
 */
@Repository
public interface _LivreAuteurRepository {
	default List<String> _generateCriteria(LivreAuteurDto dto, HashMap<String, Object> param, Integer index, Locale locale) throws Exception {
		List<String> listOfQuery = new ArrayList<String>();

		// PUT YOUR RIGHT CUSTOM CRITERIA HERE

		return listOfQuery;
	}
}
