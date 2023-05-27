package org.example.domain.ports.api;

import org.example.domain.DTO.SalleDTO;

import java.util.List;

public interface SalleServicePort {

    SalleDTO addSalle(SalleDTO salleDTO);
    List<SalleDTO> getAllSalles();

}
