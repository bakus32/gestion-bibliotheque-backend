
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.contrat;

import lombok.*;
import ci.gs2e.biblio.helper.Status;

/**
 * Response Base
 * 
 * @author Smile Bakend generator
 *
 */
@Data
@ToString
@NoArgsConstructor
public class ResponseBase {

	protected Status	status;
	protected boolean	hasError;
	protected String	sessionUser;
	protected Long		count;
    protected String  	filePath;
}
