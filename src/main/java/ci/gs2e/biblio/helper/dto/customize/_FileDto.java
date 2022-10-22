
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2019 Smile CI. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto.customize;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 *
 * 
 * @author Lazare Yao
 *
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
@Getter
@Setter
@ToString
public class _FileDto {

	private String fileBase64;
	private String extension;
	private String fileName;
	private String fileUrl;
	
	//Multipart file
	private MultipartFile 		multiPartFile;
	private Integer				user;
	
}
