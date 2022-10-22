
/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2017 Smile Backend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Math Utils
 * 
 * @author Smile Backend generator
 *
 */

@Component
@Getter
@NoArgsConstructor
@ToString
public class ParamsUtils {    	

	@Value("${smtp.mail.host}")
	private String	smtpHost;

	@Value("${smtp.mail.port}")
	private Integer	smtpPort;

	@Value("${smtp.mail.login}")
	private String	smtpLogin;

	@Value("${smtp.mail.password}")
	private String	smtpPassword;





	//Urls

	@Value("${url.root}")
	private String	rootFilesUrl;

	@Value("${path.root}")
	private String	rootFilesPath;

//	@Value("${files.directory.report}")
//	private String  filesDirectoryReport;





	//Files location


	@Value("${textfile.directory}")
	private String			textfileDirectory;

	@Value("${image.directory}")
	private String			imageDirectory;

	@Value("${video.directory}")
	private String			videoDirectory;

	@Value("${otherfile.directory}")
	private String			otherfileDirectory;
	

}
