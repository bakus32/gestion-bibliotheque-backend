
/*
 * Java dto for entity table emprunt 
 * Created on 2022-10-22 ( Time 19:10:56 )
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
import ci.gs2e.biblio.helper.dto.customize._EmpruntDto;

/**
 * DTO for table "emprunt"
 *
 * @author Smile Back-End generator
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class EmpruntDto extends _EmpruntDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    livreId              ;
    private Integer    usagerId             ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Boolean    isDeleted            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
	private String     dateEmprunt          ;
	private String     dateRetour           ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//private Integer    livre;
	//private Integer    usager;
	private String usagerNom;
	private String usagerPrenoms;
	private String usagerLogin;
	private String usagerEmail;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  livreIdParam          ;                     
	private SearchParam<Integer>  usagerIdParam         ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<String>   dateEmpruntParam      ;                     
	private SearchParam<String>   dateRetourParam       ;                     
	private SearchParam<Integer>  livreParam            ;                     
	private SearchParam<Integer>  usagerParam           ;                     
	private SearchParam<String>   usagerNomParam        ;                     
	private SearchParam<String>   usagerPrenomsParam    ;                     
	private SearchParam<String>   usagerLoginParam      ;                     
	private SearchParam<String>   usagerEmailParam      ;                     

	// order param
	private String orderField;
	private String orderDirection;



    /**
     * Default constructor
     */
    public EmpruntDto()
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
