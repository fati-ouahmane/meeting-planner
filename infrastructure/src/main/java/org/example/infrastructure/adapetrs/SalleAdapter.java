package org.example.infrastructure.adapetrs;


import org.example.infrastructure.Entity.Salle;
import org.example.infrastructure.Mappers.SalleMapper;
import org.example.infrastructure.Repository.SalleRepository;
import org.example.domain.DTO.SalleDTO;
import org.example.domain.ports.spi.SallePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class SalleAdapter implements SallePersistencePort {

    @Autowired
    private SalleRepository salleRepository;



    @Override
    public SalleDTO addSalle(SalleDTO salleDTO) {
    Salle salle = SalleMapper.INSTANCE.salleDTOToSalle(salleDTO);
    // Salle salle = new Salle(salleDTO.getNom(),salleDTO.getCapaciteMax(),salleDTO.getEquipements(),salleDTO.getFinOccupation()) ;

        Salle salleSaved = salleRepository.save(salle);


        return  SalleMapper.INSTANCE.salleToSalleDTO(salleSaved);

    }

    @Override
    public List<SalleDTO> getAllSalles() {
        List<Salle> salleList = salleRepository.findAll();

        return SalleMapper.INSTANCE.salleListToSalleDTOList(salleList);
    }
}
