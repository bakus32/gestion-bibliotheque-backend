/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.contrat;

import java.util.List;

import lombok.*;

import ci.gs2e.biblio.helper.dto.customize._AggregationDto;

/**
 * Request Base
 * 
 * @author Smile Bakend generator
 *
 */
@Data
@ToString
@NoArgsConstructor
public class RequestBase {
	protected String		sessionUser;
	protected Integer		size;
	protected Integer		index;
	protected String		lang;
	protected String		businessLineCode;
	protected String		caseEngine;
	protected Boolean		isAnd;
	protected Integer		user;
	protected Boolean 		isSimpleLoading;

	protected List<_AggregationDto> datasAggregation;
    protected String[] datasElasticSearchIndices;
}