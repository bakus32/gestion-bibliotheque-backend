/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2019 Smile CI. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto.customize;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class _AggregationDto implements Cloneable {

	private String 					field;
	private Integer 				size;
	private List<_AggregationDto> 	datasSubAggregation;
	
	//Sort
	private String 					orderDirection;
	private String 					orderField;
	private Boolean 				manualSorting;
	
	

	
	public _AggregationDto(String field, Integer size, String orderDirection, String orderField, Boolean manualSorting, List<_AggregationDto> datasSubAggregation) {
		this.field = field;
		this.size = size;
		this.datasSubAggregation = datasSubAggregation;
		this.orderDirection = orderDirection;
		this.orderField = orderField;
		this.manualSorting = manualSorting;
	}
	
	public _AggregationDto(String field, Integer size, String orderDirection, String orderField, List<_AggregationDto> datasSubAggregation) {
		this(field, size, orderDirection, orderField, null, datasSubAggregation);
	}
	
	public _AggregationDto(String field, Integer size, String orderDirection, String orderField) {
		this(field, size, orderDirection, orderField, null);
	}
	
	public _AggregationDto(String field, Integer size, List<_AggregationDto> datasSubAggregation) {
		this(field, size, null, null, datasSubAggregation);
	}
	
	@Override
	public _AggregationDto clone() throws CloneNotSupportedException {
		return (_AggregationDto) super.clone();
	}

}