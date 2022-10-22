
/*
 * Java dto for entity table role_fonctionnalite 
 * Created on 2022-10-21 ( Time 23:35:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto;

import java.util.Date;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.gs2e.biblio.helper.contrat.*;
import ci.gs2e.biblio.helper.dto.customize._RoleFonctionnaliteDto;

/**
 * DTO for table "role_fonctionnalite"
 *
 * @author Smile Back-End generator
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class RoleFonctionnaliteDto extends _RoleFonctionnaliteDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    roleId               ;
    private Integer    fonctionnaliteId     ;
    private String     code                 ;
    private String     libelle              ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Boolean    isDeleted            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//private Integer    fonctionnalite;
	private String fonctionnaliteLibelle;
	private String fonctionnaliteCode;
	//private Integer    role;
	private String roleLibelle;
	private String roleCode;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  roleIdParam           ;                     
	private SearchParam<Integer>  fonctionnaliteIdParam ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   libelleParam          ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Integer>  fonctionnaliteParam   ;                     
	private SearchParam<String>   fonctionnaliteLibelleParam;                     
	private SearchParam<String>   fonctionnaliteCodeParam;                     
	private SearchParam<Integer>  roleParam             ;                     
	private SearchParam<String>   roleLibelleParam      ;                     
	private SearchParam<String>   roleCodeParam         ;                     

	// order param
	private String orderField;
	private String orderDirection;



    /**
     * Default constructor
     */
    public RoleFonctionnaliteDto()
    {
        super();
    }
    
	//----------------------------------------------------------------------
    // clone METHOD
    //----------------------------------------------------------------------
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
}
