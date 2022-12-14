/*
 * Created on 2018-04-14 ( Time 21:52:32 )
 * Generator tool : Telosys Tools Generator ( version 3.0.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.enums;

/**
 * 
 * @author Smile Backend Generator
 *
 */
 public enum FunctionalityEnum {
 	DEFAULT("DEFAULT"),

	// AUTEUR
	VIEW_AUTEUR("VIEW_AUTEUR"),	
	CREATE_AUTEUR("CREATE_AUTEUR"),
	UPDATE_AUTEUR("UPDATE_AUTEUR"),
	DELETE_AUTEUR("DELETE_AUTEUR"),
	// CATEGORIE_LIVRE
	VIEW_CATEGORIE_LIVRE("VIEW_CATEGORIE_LIVRE"),	
	CREATE_CATEGORIE_LIVRE("CREATE_CATEGORIE_LIVRE"),
	UPDATE_CATEGORIE_LIVRE("UPDATE_CATEGORIE_LIVRE"),
	DELETE_CATEGORIE_LIVRE("DELETE_CATEGORIE_LIVRE"),
	// FONCTIONNALITE
	VIEW_FONCTIONNALITE("VIEW_FONCTIONNALITE"),	
	CREATE_FONCTIONNALITE("CREATE_FONCTIONNALITE"),
	UPDATE_FONCTIONNALITE("UPDATE_FONCTIONNALITE"),
	DELETE_FONCTIONNALITE("DELETE_FONCTIONNALITE"),
	// LIVRE
	VIEW_LIVRE("VIEW_LIVRE"),	
	CREATE_LIVRE("CREATE_LIVRE"),
	UPDATE_LIVRE("UPDATE_LIVRE"),
	DELETE_LIVRE("DELETE_LIVRE"),
	// LIVRE_AUTEUR
	VIEW_LIVRE_AUTEUR("VIEW_LIVRE_AUTEUR"),	
	CREATE_LIVRE_AUTEUR("CREATE_LIVRE_AUTEUR"),
	UPDATE_LIVRE_AUTEUR("UPDATE_LIVRE_AUTEUR"),
	DELETE_LIVRE_AUTEUR("DELETE_LIVRE_AUTEUR"),
	// ROLE
	VIEW_ROLE("VIEW_ROLE"),	
	CREATE_ROLE("CREATE_ROLE"),
	UPDATE_ROLE("UPDATE_ROLE"),
	DELETE_ROLE("DELETE_ROLE"),
	// ROLE_FONCTIONNALITE
	VIEW_ROLE_FONCTIONNALITE("VIEW_ROLE_FONCTIONNALITE"),	
	CREATE_ROLE_FONCTIONNALITE("CREATE_ROLE_FONCTIONNALITE"),
	UPDATE_ROLE_FONCTIONNALITE("UPDATE_ROLE_FONCTIONNALITE"),
	DELETE_ROLE_FONCTIONNALITE("DELETE_ROLE_FONCTIONNALITE"),
	// USAGER
	VIEW_USAGER("VIEW_USAGER"),	
	CREATE_USAGER("CREATE_USAGER"),
	UPDATE_USAGER("UPDATE_USAGER"),
	DELETE_USAGER("DELETE_USAGER"),
	// EMPRUNT
	VIEW_EMPRUNT("VIEW_EMPRUNT"),	
	CREATE_EMPRUNT("CREATE_EMPRUNT"),
	UPDATE_EMPRUNT("UPDATE_EMPRUNT"),
	DELETE_EMPRUNT("DELETE_EMPRUNT"),
	// USERS
	VIEW_USERS("VIEW_USERS"),	
	CREATE_USERS("CREATE_USERS"),
	UPDATE_USERS("UPDATE_USERS"),
	DELETE_USERS("DELETE_USERS");

	private final String value;
 	public String getValue() {
 		return value;
 	}
 	private FunctionalityEnum(String value) {
 		this.value = value;
 	}
}
