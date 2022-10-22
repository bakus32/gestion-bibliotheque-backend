
/*
 * Java dto for entity table usager 
 * Created on 2022-10-22 ( Time 21:21:57 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto;

import java.util.Date;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.*;

import ci.gs2e.biblio.helper.contrat.*;
import ci.gs2e.biblio.helper.dto.customize._UsagerDto;

/**
 * DTO for table "usager"
 *
 * @author Smile Back-End generator
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class UsagerDto extends _UsagerDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     nom                  ;
    private String     prenoms              ;
    private String     login                ;
    private String     passwd               ;
    private String     email                ;
    private String     matricule            ;
    private Integer    roleId               ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;
    private String     contact              ;
    private Boolean    isConnected          ;
    private Boolean    isLocked             ;
    private Boolean    isDefaultPassword    ;
	private String     lastConnectionDate   ;
    private Boolean    isFirstConnection    ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   nomParam              ;                     
	private SearchParam<String>   prenomsParam          ;                     
	private SearchParam<String>   loginParam            ;                     
	private SearchParam<String>   passwdParam           ;                     
	private SearchParam<String>   emailParam            ;                     
	private SearchParam<String>   matriculeParam        ;                     
	private SearchParam<Integer>  roleIdParam           ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<String>   contactParam          ;                     
	private SearchParam<Boolean>  isConnectedParam      ;                     
	private SearchParam<Boolean>  isLockedParam         ;                     
	private SearchParam<Boolean>  isDefaultPasswordParam;                     
	private SearchParam<String>   lastConnectionDateParam;                     
	private SearchParam<Boolean>  isFirstConnectionParam;                     

	// order param
	private String orderField;
	private String orderDirection;



    /**
     * Default constructor
     */
    public UsagerDto()
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
