

/*
 * Java transformer for entity table fonctionnalite 
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
 * TRANSFORMER for table "fonctionnalite"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface FonctionnaliteTransformer {

	FonctionnaliteTransformer INSTANCE = Mappers.getMapper(FonctionnaliteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	FonctionnaliteDto toDto(Fonctionnalite entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<FonctionnaliteDto> toDtos(List<Fonctionnalite> entities) throws ParseException;

    public default FonctionnaliteDto toLiteDto(Fonctionnalite entity) {
		if (entity == null) {
			return null;
		}
		FonctionnaliteDto dto = new FonctionnaliteDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	public default List<FonctionnaliteDto> toLiteDtos(List<Fonctionnalite> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<FonctionnaliteDto> dtos = new ArrayList<FonctionnaliteDto>();
		for (Fonctionnalite entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.parentId", target="parentId"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    Fonctionnalite toEntity(FonctionnaliteDto dto) throws ParseException;

    //List<Fonctionnalite> toEntities(List<FonctionnaliteDto> dtos) throws ParseException;

}
