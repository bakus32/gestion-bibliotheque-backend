

/*
 * Java transformer for entity table livre_auteur 
 * Created on 2022-10-21 ( Time 23:35:49 )
 * Generator tool : Telosys Tools Generator ( version 3.3.0 )
 * Copyright 2018 Smile Bakend generator. All Rights Reserved.
 */

package ci.gs2e.biblio.helper.dto.transformer;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import ci.gs2e.biblio.helper.contrat.*;
import ci.gs2e.biblio.helper.dto.*;
import ci.gs2e.biblio.dao.entity.*;


/**
 * TRANSFORMER for table "livre_auteur"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface LivreAuteurTransformer {

	LivreAuteurTransformer INSTANCE = Mappers.getMapper(LivreAuteurTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		//@Mapping(ignore=true, target="auteur"),
		@Mapping(source="entity.auteur.id", target="auteurId"),
		@Mapping(source="entity.auteur.nom", target="auteurNom"),
		@Mapping(source="entity.auteur.prenoms", target="auteurPrenoms"),
		//@Mapping(ignore=true, target="livre"),
		@Mapping(source="entity.livre.id", target="livreId"),
	})
	LivreAuteurDto toDto(LivreAuteur entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<LivreAuteurDto> toDtos(List<LivreAuteur> entities) throws ParseException;

    public default LivreAuteurDto toLiteDto(LivreAuteur entity) {
		if (entity == null) {
			return null;
		}
		LivreAuteurDto dto = new LivreAuteurDto();
		dto.setId( entity.getId() );
		return dto;
    }

	public default List<LivreAuteurDto> toLiteDtos(List<LivreAuteur> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<LivreAuteurDto> dtos = new ArrayList<LivreAuteurDto>();
		for (LivreAuteur entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="auteur", target="auteur"),
		@Mapping(source="livre", target="livre"),
	})
    LivreAuteur toEntity(LivreAuteurDto dto, Auteur auteur, Livre livre) throws ParseException;

    //List<LivreAuteur> toEntities(List<LivreAuteurDto> dtos) throws ParseException;

}
