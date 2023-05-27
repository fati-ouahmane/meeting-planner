package org.example.domain.service;

import org.example.domain.DTO.SalleDTO;
import org.example.domain.ports.spi.SallePersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SalleServiceImplTest {



    @Mock
    private SallePersistencePort sallePersistencePort;

    @InjectMocks
    private SalleServiceImpl salleService;
    @Test
    void addSalle() {

        SalleDTO salleDTO = SalleDTO.builder()
                .nom("E1001")
                .capaciteMax(23)
                .equipements(Arrays.asList(""))
                .finOccupation(LocalTime.of(8, 0))
                .build();


        when(sallePersistencePort.addSalle(salleDTO)).thenReturn(salleDTO);

        SalleDTO result = salleService.addSalle(salleDTO);



        Assertions.assertEquals(salleDTO.getNom(), result.getNom());
        Assertions.assertEquals(salleDTO.getCapaciteMax(), result.getCapaciteMax());
        Assertions.assertEquals(salleDTO.getFinOccupation(), result.getFinOccupation());
    }

    @Test
    void getAllSallesTest() {

        List<SalleDTO> salleDTOList = new ArrayList<>();
        SalleDTO salleDTO1 = SalleDTO.builder()
                .nom("E1001")
                .capaciteMax(23)
                .equipements(Arrays.asList(""))
                .finOccupation(LocalTime.of(8, 0))
                .build();

        salleDTOList.add(salleDTO1);

        SalleDTO salleDTO2 = SalleDTO.builder()
                .nom("E1002")
                .capaciteMax(10)
                .equipements(Arrays.asList("Ecran"))
                .finOccupation(LocalTime.of(8, 0))
                .build();

        salleDTOList.add(salleDTO2);

        when(sallePersistencePort.getAllSalles()).thenReturn( salleDTOList);


        List<SalleDTO> result = salleService.getAllSalles();


        Assertions.assertEquals( salleDTOList.size(), result.size());
        Assertions.assertEquals( salleDTOList.get(0).getNom(), result.get(0).getNom());
        Assertions.assertEquals( salleDTOList.get(0).getCapaciteMax(), result.get(0).getCapaciteMax());
        Assertions.assertEquals( salleDTOList.get(0).getFinOccupation(), result.get(0).getFinOccupation());
        Assertions.assertEquals( salleDTOList.get(0).getEquipements(), result.get(0).getEquipements());
        Assertions.assertEquals( salleDTOList.get(1).getNom(), result.get(1).getNom());
        Assertions.assertEquals( salleDTOList.get(1).getCapaciteMax(), result.get(1).getCapaciteMax());
        Assertions.assertEquals( salleDTOList.get(1).getFinOccupation(), result.get(1).getFinOccupation());
        Assertions.assertEquals( salleDTOList.get(1).getEquipements(), result.get(1).getEquipements());

    }
}