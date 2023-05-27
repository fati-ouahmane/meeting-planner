package org.example.domain.ports.api;

import org.example.domain.DTO.TypeReunionDTO;

import java.util.List;

public interface TypeReunionServicePort {


    TypeReunionDTO addTypeReunion(TypeReunionDTO typeReunionDTO);
       List<TypeReunionDTO> getAllTypesReunion();

}
