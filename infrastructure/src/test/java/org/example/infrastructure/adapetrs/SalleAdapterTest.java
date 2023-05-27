package org.example.infrastructure.adapetrs;

import org.example.domain.DTO.SalleDTO;
import org.example.domain.DTO.TypeReunionDTO;
import org.example.infrastructure.Entity.Reservation;
import org.example.infrastructure.Entity.Salle;
import org.example.infrastructure.Mappers.ReservationMapper;
import org.example.infrastructure.Mappers.SalleMapper;
import org.example.infrastructure.Repository.SalleRepository;
import org.example.infrastructure.Repository.TypeReunionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SalleAdapterTest {



    @InjectMocks
    private SalleAdapter salleAdapter;


    @Mock
    private SalleRepository salleRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addSalleTest() {

        SalleDTO salleDTO = SalleDTO.builder()
                .nom("E1001")
                .capaciteMax(23)
                .equipements(Arrays.asList(""))
                .finOccupation(LocalTime.of(8, 0))
                .build();

        Salle salle = SalleMapper.INSTANCE.salleDTOToSalle(salleDTO);
        Mockito.when(salleRepository.save(salle)).thenReturn(salle);


        // When
        SalleDTO result = salleAdapter.addSalle(salleDTO);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getNom(), salleDTO.getNom());
        Assertions.assertEquals(result.getCapaciteMax(), salleDTO.getCapaciteMax());
        Assertions.assertEquals(result.getEquipements(), salleDTO.getEquipements());



    }

    @Test
    void getAllSallesTest() {

        List<Salle> salleList = new ArrayList<>();
        Salle salle1 = new Salle();
        salle1.setNom("E1001");
        salle1.setCapaciteMax(23);
        salle1.setEquipements(Arrays.asList(""));
        salle1.setFinOccupation(LocalTime.of(8, 0));
        salleList.add(salle1);

        Salle salle2 = new Salle();
        salle2.setNom("E1002");
        salle2.setCapaciteMax(10);
        salle2.setEquipements(Arrays.asList("Ecran"));
        salle2.setFinOccupation(LocalTime.of(8, 0));
        salleList.add(salle2);

        List<SalleDTO> salleDTOList = SalleMapper.INSTANCE.salleListToSalleDTOList(salleList);
        Mockito.when(salleRepository.findAll()).thenReturn(salleList);

        List<SalleDTO> result = salleAdapter.getAllSalles();


        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), salleList.size());
        Assertions.assertEquals(result.get(0).getNom(), salleDTOList.get(0).getNom());
        Assertions.assertEquals(result.get(0).getCapaciteMax(), salleDTOList.get(0).getCapaciteMax());
        Assertions.assertEquals(result.get(0).getEquipements(), salleDTOList.get(0).getEquipements());
        Assertions.assertEquals(result.get(0).getFinOccupation(), salleDTOList.get(0).getFinOccupation());
        Assertions.assertEquals(result.get(1).getNom(), salleDTOList.get(1).getNom());
        Assertions.assertEquals(result.get(1).getCapaciteMax(), salleDTOList.get(1).getCapaciteMax());
        Assertions.assertEquals(result.get(1).getEquipements(), salleDTOList.get(1).getEquipements());
        Assertions.assertEquals(result.get(1).getFinOccupation(),salleDTOList.get(1).getFinOccupation());

    }

    }
