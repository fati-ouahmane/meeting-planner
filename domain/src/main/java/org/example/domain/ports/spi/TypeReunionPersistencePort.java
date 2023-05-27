package org.example.domain.ports.spi;

import org.example.domain.DTO.TypeReunionDTO;

import java.util.List;

public interface TypeReunionPersistencePort {

    TypeReunionDTO addTypeReunion(TypeReunionDTO typeReunionDTO);
    List<TypeReunionDTO> getAllTypesReunion();

}
