
/*
 * Java dto for entity table livre 
 * Created on 2022-10-21 ( Time 23:35:49 )
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
import ci.gs2e.biblio.helper.dto.customize._LivreDto;

/**
 * DTO for table "livre"
 *
 * @author Smile Back-End generator
 */
@Data
@ToString
@JsonInclude(Include.NON_NULL)
@JsonPropertyOrder(alphabetic = true)
public class LivreDto extends _LivreDto implements Cloneable{

    private Integer    id                   ; // Primary Key

    private String     titre                ;
    private Integer    categorieId          ;
    private String     maisonEdition        ;
    private Integer    nombreExemplaires    ;
    private Integer    nombrePages          ;
	private String     datePublication      ;
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
	//private Integer    categorieLivre;
	private String categorieLivreLibelle;
	private String categorieLivreCode;

	// Search param
	private SearchParam<Integer>  idParam               ;                     
	private SearchParam<String>   titreParam            ;                     
	private SearchParam<Integer>  categorieIdParam      ;                     
	private SearchParam<String>   maisonEditionParam    ;                     
	private SearchParam<Integer>  nombreExemplairesParam;                     
	private SearchParam<Integer>  nombrePagesParam      ;                     
	private SearchParam<String>   datePublicationParam  ;                     
	private SearchParam<String>   createdAtParam        ;                     
	private SearchParam<String>   updatedAtParam        ;                     
	private SearchParam<String>   deletedAtParam        ;                     
	private SearchParam<Integer>  createdByParam        ;                     
	private SearchParam<Integer>  updatedByParam        ;                     
	private SearchParam<Integer>  deletedByParam        ;                     
	private SearchParam<Boolean>  isDeletedParam        ;                     
	private SearchParam<Integer>  categorieLivreParam   ;                     
	private SearchParam<String>   categorieLivreLibelleParam;                     
	private SearchParam<String>   categorieLivreCodeParam;                     

	// order param
	private String orderField;
	private String orderDirection;



    /**
     * Default constructor
     */
    public LivreDto()
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
