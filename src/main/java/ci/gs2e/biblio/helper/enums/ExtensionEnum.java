/*
 * Created on 2022-10-21 ( Time 23:35:15 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2019 Smile Backend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.enums;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * List of contentType / MediaType commonly used
 *
 * @author Smile Backend generator
 *
 */
public enum ExtensionEnum { 

	pdf("application/pdf"),
	docx("application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
	doc("application/msword"),
	xlsx("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
	xls("application/vnd.ms-excel"),
	odt("application/vnd.oasis.opendocument.text"),
	txt("text/plain"),
	ppt("application/vnd.ms-powerpoint"),
	jpeg("image/jpeg"),
	jpg("image/jpeg"),
	gif("image/gif"),
	png("image/png"),
	mp4("video/mp4"),
	avi("video/x-msvideo"),
	flv("video/x-flv"),
	mpeg("video/mpeg"),
	gp("video/3gpp"),
	g2("video/3gpp2"),
	ogv("video/ogg"),;

	private final String value;

	ExtensionEnum(String v){
		value = v; 	
	} 		

	public String getValue () {
		return value ; 	
	}


 	public static List<String> getValues(){
 		return Arrays.asList(values()).stream().map(a->a.getValue()).collect(Collectors.toList());
 	}

 	public static boolean isValid(String value) {
 		return getValues().stream().anyMatch(e->e.equals(value));
 	}
}