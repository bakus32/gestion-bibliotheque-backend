
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.contrat;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Search Param
 * 
 * @author Smile Bakend generator
 *
 */
@Data
@ToString
@NoArgsConstructor
public class SearchParam<T> {

	String	operator;
	T		start;
	T		end;
	List<T> datas;




	//public SearchParam() {}
	
	public SearchParam(String operator, T start, T end, List<T> datas) {
		super();
		this.operator = operator;
		this.start = start;
		this.end = end;
		this.datas = datas;
	}
	
	public SearchParam(String operator) {
		this(operator, null, null, null);
	}
	
	public SearchParam(String operator, T start, T end) {
		this(operator, start, end, null);
	}
	
	public SearchParam(String operator, List<T> datas) {
		this(operator, null, null, datas);
	}


}
