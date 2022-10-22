
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.contrat;

import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.*;

/**
 * Request
 * 
 * @author Smile Bakend generator
 *
 */
@Data
@ToString
@NoArgsConstructor
@XmlRootElement
@JsonInclude(Include.NON_NULL)
public class Request<T> extends RequestBase {
	protected T			data;
	protected List<T>	datas;
}