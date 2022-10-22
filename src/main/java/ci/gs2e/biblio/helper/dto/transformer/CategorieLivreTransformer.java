

/*
 * Java transformer for entity table categorie_livre 
 * Created on 2022-10-22 ( Time 01:31:18 )
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
 * TRANSFORMER for table "categorie_livre"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface CategorieLivreTransformer {

	CategorieLivreTransformer INSTANCE = Mappers.getMapper(CategorieLivreTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
	})
	CategorieLivreDto toDto(CategorieLivre entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<CategorieLivreDto> toDtos(List<CategorieLivre> entities) throws ParseException;

    public default CategorieLivreDto toLiteDto(CategorieLivre entity) {
		if (entity == null) {
			return null;
		}
		CategorieLivreDto dto = new CategorieLivreDto();
		dto.setId( entity.getId() );
		dto.setLibelle( entity.getLibelle() );
		return dto;
    }

	public default List<CategorieLivreDto> toLiteDtos(List<CategorieLivre> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<CategorieLivreDto> dtos = new ArrayList<CategorieLivreDto>();
		for (CategorieLivre entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.libelle", target="libelle"),
		@Mapping(source="dto.code", target="code"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
	})
    CategorieLivre toEntity(CategorieLivreDto dto) throws ParseException;

    //List<CategorieLivre> toEntities(List<CategorieLivreDto> dtos) throws ParseException;

}
