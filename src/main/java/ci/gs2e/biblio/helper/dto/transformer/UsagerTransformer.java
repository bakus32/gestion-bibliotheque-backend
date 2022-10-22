

/*
 * Java transformer for entity table usager 
 * Created on 2022-10-22 ( Time 21:21:57 )
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
 * TRANSFORMER for table "usager"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface UsagerTransformer {

	UsagerTransformer INSTANCE = Mappers.getMapper(UsagerTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.lastConnectionDate", dateFormat="dd/MM/yyyy",target="lastConnectionDate"),
	})
	UsagerDto toDto(Usager entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UsagerDto> toDtos(List<Usager> entities) throws ParseException;

    public default UsagerDto toLiteDto(Usager entity) {
		if (entity == null) {
			return null;
		}
		UsagerDto dto = new UsagerDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		dto.setPrenoms( entity.getPrenoms() );
		dto.setLogin( entity.getLogin() );
		dto.setEmail( entity.getEmail() );
		return dto;
    }

	public default List<UsagerDto> toLiteDtos(List<Usager> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UsagerDto> dtos = new ArrayList<UsagerDto>();
		for (Usager entity : entities) {
			dtos.add(toLiteDto(entity));
		}
		return dtos;
	}

	@Mappings({
		@Mapping(source="dto.id", target="id"),
		@Mapping(source="dto.nom", target="nom"),
		@Mapping(source="dto.prenoms", target="prenoms"),
		@Mapping(source="dto.login", target="login"),
		@Mapping(source="dto.passwd", target="passwd"),
		@Mapping(source="dto.email", target="email"),
		@Mapping(source="dto.matricule", target="matricule"),
		@Mapping(source="dto.roleId", target="roleId"),
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.contact", target="contact"),
		@Mapping(source="dto.isConnected", target="isConnected"),
		@Mapping(source="dto.isLocked", target="isLocked"),
		@Mapping(source="dto.isDefaultPassword", target="isDefaultPassword"),
		@Mapping(source="dto.lastConnectionDate", dateFormat="dd/MM/yyyy",target="lastConnectionDate"),
		@Mapping(source="dto.isFirstConnection", target="isFirstConnection"),
	})
    Usager toEntity(UsagerDto dto) throws ParseException;

    //List<Usager> toEntities(List<UsagerDto> dtos) throws ParseException;

}
