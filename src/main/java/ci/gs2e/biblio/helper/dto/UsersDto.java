
/*
 * Java dto for entity table users 
 * Created on 2022-10-22 ( Time 12:04:10 )
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
import ci.gs2e.biblio.helper.dto.customize._UsersDto;

/**
 * DTO for table "users"
 *
 * @author Smile Back-End generator
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class UsersDto extends _UsersDto implements Cloneable{

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
	private String     lastConnectionDate   ;
    private Boolean    isConnected          ;
    private Boolean    isLocked             ;
    private Boolean    isDeleted            ;
    private Boolean    isDefaultPassword    ;
    private Boolean    isFirstConnection    ;
    private String     contact              ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//private Integer    role;
	private String roleLibelle;
	private String roleCode;

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
	private SearchParam<String>   lastConnectionDateParam;                     
	private SearchParam<Boolean>  isConnectedParam      ;                     
	private SearchParam<Boolean>  isLockedParam         ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Boolean>  isDefaultPasswordParam;                     
	private SearchParam<Boolean>  isFirstConnectionParam;                     
	private SearchParam<String>   contactParam          ;                     
	private SearchParam<Integer>  roleParam             ;                     
	private SearchParam<String>   roleLibelleParam      ;                     
	private SearchParam<String>   roleCodeParam         ;                     

	// order param
	private String orderField;
	private String orderDirection;



    /**
     * Default constructor
     */
    public UsersDto()
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
