

/*
 * Java transformer for entity table users 
 * Created on 2022-10-22 ( Time 12:04:11 )
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
 * TRANSFORMER for table "users"
 * 
 * @author Smile Backend generator
 *
 */
@Mapper
public interface UsersTransformer {

	UsersTransformer INSTANCE = Mappers.getMapper(UsersTransformer.class);

	@FullTransformerQualifier
	@Mappings({
		@Mapping(source="entity.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="entity.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="entity.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="entity.lastConnectionDate", dateFormat="dd/MM/yyyy",target="lastConnectionDate"),
		//@Mapping(ignore=true, target="role"),
		@Mapping(source="entity.role.id", target="roleId"),
		@Mapping(source="entity.role.libelle", target="roleLibelle"),
		@Mapping(source="entity.role.code", target="roleCode"),
	})
	UsersDto toDto(Users entity) throws ParseException;

	@IterableMapping(qualifiedBy = {FullTransformerQualifier.class})
    List<UsersDto> toDtos(List<Users> entities) throws ParseException;

    public default UsersDto toLiteDto(Users entity) {
		if (entity == null) {
			return null;
		}
		UsersDto dto = new UsersDto();
		dto.setId( entity.getId() );
		dto.setNom( entity.getNom() );
		dto.setPrenoms( entity.getPrenoms() );
		dto.setLogin( entity.getLogin() );
		dto.setEmail( entity.getEmail() );
		return dto;
    }

	public default List<UsersDto> toLiteDtos(List<Users> entities) {
		if (entities == null || entities.stream().allMatch(o -> o == null)) {
			return null;
		}
		List<UsersDto> dtos = new ArrayList<UsersDto>();
		for (Users entity : entities) {
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
		@Mapping(source="dto.createdAt", dateFormat="dd/MM/yyyy",target="createdAt"),
		@Mapping(source="dto.updatedAt", dateFormat="dd/MM/yyyy",target="updatedAt"),
		@Mapping(source="dto.deletedAt", dateFormat="dd/MM/yyyy",target="deletedAt"),
		@Mapping(source="dto.createdBy", target="createdBy"),
		@Mapping(source="dto.updatedBy", target="updatedBy"),
		@Mapping(source="dto.deletedBy", target="deletedBy"),
		@Mapping(source="dto.lastConnectionDate", dateFormat="dd/MM/yyyy",target="lastConnectionDate"),
		@Mapping(source="dto.isConnected", target="isConnected"),
		@Mapping(source="dto.isLocked", target="isLocked"),
		@Mapping(source="dto.isDeleted", target="isDeleted"),
		@Mapping(source="dto.isDefaultPassword", target="isDefaultPassword"),
		@Mapping(source="dto.isFirstConnection", target="isFirstConnection"),
		@Mapping(source="dto.contact", target="contact"),
		@Mapping(source="role", target="role"),
	})
    Users toEntity(UsersDto dto, Role role) throws ParseException;

    //List<Users> toEntities(List<UsersDto> dtos) throws ParseException;

}
