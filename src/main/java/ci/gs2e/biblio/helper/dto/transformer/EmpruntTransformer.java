

/*
 * Java transformer for entity table emprunt 
 * Created on 2022-10-22 ( Time 19:10:56 )
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
 * TRANSFORMER for table "emprunt"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface EmpruntTransformer {

	EmpruntTransformer INSTANCE = Mappers.getMapper(EmpruntTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.dateEmprunt", dateFormat="dd/MM/yyyy",target="dateEmprunt"),
		@Mapping(source="entity.dateRetour", dateFormat="dd/MM/yyyy",target="dateRetour"),
		//@Mapping(ignore=true, target="livre"),
		@Mapping(source="entity.livre.id", target="livreId"),
		//@Mapping(ignore=true, target="usager"),
		@Mapping(source="entity.usager.id", target="usagerId"),
		@Mapping(source="entity.usager.nom", target="usagerNom"),
		@Mapping(source="entity.usager.prenoms", target="usagerPrenoms"),
		@Mapping(source="entity.usager.login", target="usagerLogin"),
		@Mapping(source="entity.usager.email", target="usagerEmail"),
	})
	EmpruntDto toDto(Emprunt entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<EmpruntDto> toDtos(List<Emprunt> entities) throws ParseException;

    public default EmpruntDto toLiteDto(Emprunt entity) {
		if (entity == null) {
			return null;
		}
		EmpruntDto dto = new EmpruntDto();
		dto.setId( entity.getId() );
		return dto;
    }

	public default List<EmpruntDto> toLiteDtos(List<Emprunt> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<EmpruntDto> dtos = new ArrayList<EmpruntDto>();
		for (Emprunt entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.dateEmprunt", dateFormat="dd/MM/yyyy",target="dateEmprunt"),
		@Mapping(source="dto.dateRetour", dateFormat="dd/MM/yyyy",target="dateRetour"),
		@Mapping(source="livre", target="livre"),
		@Mapping(source="usager", target="usager"),
	})
    Emprunt toEntity(EmpruntDto dto, Livre livre, Usager usager) throws ParseException;

    //List<Emprunt> toEntities(List<EmpruntDto> dtos) throws ParseException;

}
