
/*
 * Java dto for entity table livre_auteur 
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
import ci.gs2e.biblio.helper.dto.customize._LivreAuteurDto;

/**
 * DTO for table "livre_auteur"
 *
 * @author Smile Back-End generator
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class LivreAuteurDto extends _LivreAuteurDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private Integer    livreId              ;
    private Integer    auteurId             ;
    private String     code                 ;
	private String     createdAt            ;
	private String     updatedAt            ;
	private String     deletedAt            ;
    private Integer    createdBy            ;
    private Integer    updatedBy            ;
    private Integer    deletedBy            ;
    private Boolean    isDeleted            ;

    //----------------------------------------------------------------------
    // ENTITY LINKS FIELD ( RELATIONSHIP )
    //----------------------------------------------------------------------
	//private Integer    auteur;
	private String auteurNom;
	private String auteurPrenoms;
	//private Integer    livre;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<Integer>  livreIdParam          ;                     
	private SearchParam<Integer>  auteurIdParam         ;                     
	private SearchParam<String>   codeParam             ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Integer>  auteurParam           ;                     
	private SearchParam<String>   auteurNomParam        ;                     
	private SearchParam<String>   auteurPrenomsParam    ;                     
	private SearchParam<Integer>  livreParam            ;                     

	// order param
	private String orderField;
	private String orderDirection;



    /**
     * Default constructor
     */
    public LivreAuteurDto()
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
