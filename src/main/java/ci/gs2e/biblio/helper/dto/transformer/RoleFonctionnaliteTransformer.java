

/*
 * Java transformer for entity table role_fonctionnalite 
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
 * TRANSFORMER for table "role_fonctionnalite"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface RoleFonctionnaliteTransformer {

	RoleFonctionnaliteTransformer INSTANCE = Mappers.getMapper(RoleFonctionnaliteTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		//@Mapping(ignore=true, target="fonctionnalite"),
		@Mapping(source="entity.fonctionnalite.id", target="fonctionnaliteId"),
		@Mapping(source="entity.fonctionnalite.libelle", target="fonctionnaliteLibelle"),
		@Mapping(source="entity.fonctionnalite.code", target="fonctionnaliteCode"),
		//@Mapping(ignore=true, target="role"),
		@Mapping(source="entity.role.id", target="roleId"),
		@Mapping(source="entity.role.libelle", target="roleLibelle"),
		@Mapping(source="entity.role.code", target="roleCode"),
	})
	RoleFonctionnaliteDto toDto(RoleFonctionnalite entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<RoleFonctionnaliteDto> toDtos(List<RoleFonctionnalite> entities) throws ParseException;

    public default RoleFonctionnaliteDto toLiteDto(RoleFonctionnalite entity) {
		if (entity == null) {
			return null;
		}
		RoleFonctionnaliteDto dto = new RoleFonctionnaliteDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	public default List<RoleFonctionnaliteDto> toLiteDtos(List<RoleFonctionnalite> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<RoleFonctionnaliteDto> dtos = new ArrayList<RoleFonctionnaliteDto>();
		for (RoleFonctionnalite entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="fonctionnalite", target="fonctionnalite"),
		@Mapping(source="role", target="role"),
	})
    RoleFonctionnalite toEntity(RoleFonctionnaliteDto dto, Fonctionnalite fonctionnalite, Role role) throws ParseException;

    //List<RoleFonctionnalite> toEntities(List<RoleFonctionnaliteDto> dtos) throws ParseException;

}
