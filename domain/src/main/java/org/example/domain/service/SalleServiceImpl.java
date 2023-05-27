package org.example.domain.service;

import org.example.domain.ports.api.SalleServicePort;
import org.example.domain.DTO.SalleDTO;
import org.example.domain.ports.spi.SallePersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class SalleServiceImpl implements SalleServicePort {

    @Autowired
    private SallePersistencePort sallePersistencePort;



   public SalleServiceImpl(SallePersistencePort sallePersistencePort){
        this.sallePersistencePort= sallePersistencePort;
    }
    @Override
    public SalleDTO addSalle(SalleDTO salleDTO) {
        return sallePersistencePort.addSalle(salleDTO);
    }

    @Override
    public List<SalleDTO> getAllSalles() {
        return sallePersistencePort.getAllSalles();
    }
}
