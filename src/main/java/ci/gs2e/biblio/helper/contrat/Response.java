
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.contrat;

import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Response
 * 
 * @author Smile Bakend generator
 *
 */
@Data
@ToString
@NoArgsConstructor
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Response<T> extends ResponseBase {

	protected List<T> 				items;
    protected T             		item;
	protected List<T> 				datas;
	protected Map<String, Object>	itemsAsMap;
}
