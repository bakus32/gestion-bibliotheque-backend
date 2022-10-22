

/*
 * Java transformer for entity table livre 
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
 * TRANSFORMER for table "livre"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface LivreTransformer {

	LivreTransformer INSTANCE = Mappers.getMapper(LivreTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.datePublication", dateFormat="dd/MM/yyyy",target="datePublication"),
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		//@Mapping(ignore=true, target="categorieLivre"),
		@Mapping(source="entity.categorieLivre.id", target="categorieId"),
		@Mapping(source="entity.categorieLivre.libelle", target="categorieLivreLibelle"),
		@Mapping(source="entity.categorieLivre.code", target="categorieLivreCode"),
	})
	LivreDto toDto(Livre entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<LivreDto> toDtos(List<Livre> entities) throws ParseException;

    public default LivreDto toLiteDto(Livre entity) {
		if (entity == null) {
			return null;
		}
		LivreDto dto = new LivreDto();
		dto.setId( entity.getId() );
		return dto;
    }

	public default List<LivreDto> toLiteDtos(List<Livre> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<LivreDto> dtos = new ArrayList<LivreDto>();
		for (Livre entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.titre", target="titre"),
		@Mapping(source="dto.maisonEdition", target="maisonEdition"),
		@Mapping(source="dto.nombreExemplaires", target="nombreExemplaires"),
		@Mapping(source="dto.nombrePages", target="nombrePages"),
		@Mapping(source="dto.datePublication", dateFormat="dd/MM/yyyy",target="datePublication"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="categorieLivre", target="categorieLivre"),
	})
    Livre toEntity(LivreDto dto, CategorieLivre categorieLivre) throws ParseException;

    //List<Livre> toEntities(List<LivreDto> dtos) throws ParseException;

}
