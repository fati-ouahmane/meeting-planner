package org.example.domain.service;

import org.example.domain.ports.api.TypeReunionServicePort;
import org.example.domain.DTO.TypeReunionDTO;
import org.example.domain.ports.spi.TypeReunionPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class TypeReunionServiceImpl implements TypeReunionServicePort {

    @Autowired
    private TypeReunionPersistencePort typeReunionPersistencePort;

    public TypeReunionServiceImpl(TypeReunionPersistencePort typeReunionPersistencePort){
        this.typeReunionPersistencePort= typeReunionPersistencePort;
    }
    @Override
    public TypeReunionDTO addTypeReunion(TypeReunionDTO typeReunionDTO) {
        return typeReunionPersistencePort.addTypeReunion(typeReunionDTO);
    }

    @Override
    public List<TypeReunionDTO> getAllTypesReunion() {
        return typeReunionPersistencePort.getAllTypesReunion();
    }
}
