

/*
 * Java transformer for entity table auteur 
 * Created on 2022-10-21 ( Time 23:35:48 )
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
 * TRANSFORMER for table "auteur"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface AuteurTransformer {

	AuteurTransformer INSTANCE = Mappers.getMapper(AuteurTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	AuteurDto toDto(Auteur entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<AuteurDto> toDtos(List<Auteur> entities) throws ParseException;

    public default AuteurDto toLiteDto(Auteur entity) {
		if (entity == null) {
			return null;
		}
		AuteurDto dto = new AuteurDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		dto.setPrenoms( entity.getPrenoms() );
		return dto;
    }

	public default List<AuteurDto> toLiteDtos(List<Auteur> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<AuteurDto> dtos = new ArrayList<AuteurDto>();
		for (Auteur entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.prenoms", target="prenoms"),
		@Mapping(source="dto.specialite", target="specialite"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Auteur toEntity(AuteurDto dto) throws ParseException;

    //List<Auteur> toEntities(List<AuteurDto> dtos) throws ParseException;

}
