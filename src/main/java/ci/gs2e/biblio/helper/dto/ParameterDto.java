/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2019 Smile CI. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto;

import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.gs2e.biblio.helper.contrat.SearchParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * request object
 * 
 * @author Lazare yao, Smile Back-End developper
 *
 */
@XmlRootElement
@JsonInclude(Include.NON_NULL)
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ParameterDto implements Cloneable {


	private Map<String, Object> params = new HashMap<String, Object>();

	private String startDate;
	private String endDate;
	private String date;

	
	private String indexPattern;
	private String parameters;
	private Integer pos;
	private String className;

	private SearchParam<String> dateParam;
	private SearchParam<String> startDateParam;
	private SearchParam<String> endDateparam;

	
}