package org.example.infrastructure.Mappers;



import org.example.domain.DTO.SalleDTO;
import org.example.infrastructure.Entity.Salle;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper
public interface SalleMapper {

    SalleMapper INSTANCE = Mappers.getMapper(SalleMapper.class);

    @Mapping(
            target = "finOccupation",
            expression = "java(java.time.LocalTime.parse(salle.getFinOccupation().toString(), java.time.format.DateTimeFormatter.ISO_LOCAL_TIME))"
    )
    SalleDTO salleToSalleDTO(Salle salle);

    @Mapping(
            target = "finOccupation",
            expression = "java(java.time.LocalTime.parse(salleDTO.getFinOccupation().toString(), java.time.format.DateTimeFormatter.ISO_LOCAL_TIME))"
    )
    Salle salleDTOToSalle(SalleDTO salleDTO);


    List<SalleDTO> salleListToSalleDTOList(List<Salle> salleList);

    List<Salle> salleDTOListToSalleList(List<SalleDTO> salleDTOList);
}
