package org.example.infrastructure.adapetrs;


import org.example.infrastructure.Entity.TypeReunion;
import org.example.infrastructure.Mappers.TypeReunionMapper;
import org.example.infrastructure.Repository.TypeReunionRepository;
import org.example.domain.DTO.TypeReunionDTO;
import org.example.domain.ports.spi.TypeReunionPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeReunionAdapter implements TypeReunionPersistencePort {

    @Autowired
    private TypeReunionRepository typeReunionRepository;



    @Override
    public TypeReunionDTO addTypeReunion(TypeReunionDTO typeReunionDTO) {

       TypeReunion typeReunion = TypeReunionMapper.INSTANCE.typeReunionDTOToTypeReunion(typeReunionDTO);
        TypeReunion typeReunionSaved = typeReunionRepository.save(typeReunion);
        return  TypeReunionMapper.INSTANCE.typeReunionToTypeReunionDTO(typeReunionSaved);

    }

    @Override
    public List<TypeReunionDTO> getAllTypesReunion() {
        List<TypeReunion> typeReunionList =typeReunionRepository.findAll();

        return TypeReunionMapper.INSTANCE.typeReunionListToTypeReunionDTOList(typeReunionList);
    }
}
