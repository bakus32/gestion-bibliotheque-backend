
/*
 * Java dto for entity table users 
 * Created on 2022-10-21 ( Time 23:35:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto.customize;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ci.gs2e.biblio.helper.contrat.*;

import lombok.Getter;
import lombok.Setter;

/**
 * DTO customize for table "users"
 * 
 * @author Smile Back-End generator
 *
 */
@Getter
@Setter
@JsonInclude(Include.NON_NULL)
public class _UsersDto {

	private String newPassword;
}
