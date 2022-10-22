
/*
 * Java dto for entity table livre 
 * Created on 2022-10-21 ( Time 23:35:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto.customize;

import java.util.List;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.gs2e.biblio.helper.dto.AuteurDto;
import lombok.Getter;
import lombok.Setter;

/**
 * DTO customize for table "livre"
 * 
 * @author Smile Back-End generator
 *
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class _LivreDto {

	private List<AuteurDto> datasAuteurs;
	private List<Map<String, String>> auteurs;
}
