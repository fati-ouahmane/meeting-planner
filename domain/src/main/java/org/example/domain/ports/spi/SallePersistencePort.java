package org.example.domain.ports.spi;

import org.example.domain.DTO.SalleDTO;

import java.util.List;

public interface SallePersistencePort {


    SalleDTO addSalle(SalleDTO salleDTO);
    List<SalleDTO> getAllSalles();
}
