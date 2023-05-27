package org.example.infrastructure.Mappers;


import org.example.domain.DTO.TypeReunionDTO;
import org.example.infrastructure.Entity.TypeReunion;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TypeReunionMapper {


    TypeReunionMapper INSTANCE = Mappers.getMapper(TypeReunionMapper.class);

    TypeReunionDTO typeReunionToTypeReunionDTO(TypeReunion typeReunion);

    TypeReunion typeReunionDTOToTypeReunion(TypeReunionDTO typeReunionDTO);

    List<TypeReunionDTO> typeReunionListToTypeReunionDTOList(List<TypeReunion> typeReunionList);

    List<TypeReunion> typeReunionDTOListToTypeReunionList(List<TypeReunionDTO> typeReunionDTOList);
}
