package org.example.domain.service;

import org.example.domain.DTO.TypeReunionDTO;
import org.example.domain.ports.spi.TypeReunionPersistencePort;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class TypeReunionServiceImplTest {
    @InjectMocks
    private TypeReunionServiceImpl typeReunionService;

    @Mock
    private TypeReunionPersistencePort typeReunionPersistencePort;




    @Test
    public void testAddTypeReunion() {
        // Setup
        TypeReunionDTO typeReunionDTO = TypeReunionDTO.builder()
                .libelle("VC")
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .build();
        when(typeReunionPersistencePort.addTypeReunion(any(TypeReunionDTO.class))).thenReturn(typeReunionDTO);

        // Test
        TypeReunionDTO result = typeReunionService.addTypeReunion(typeReunionDTO);

        // Verify
        assertEquals(typeReunionDTO.getLibelle(), result.getLibelle());
        assertEquals(typeReunionDTO.getEquipements(), result.getEquipements());
    }
    @Test
    public void testGetAllTypesReunion() {

        List<TypeReunionDTO> typeReunionDTOList = new ArrayList<>();
        TypeReunionDTO typeReunionDTO1 = TypeReunionDTO.builder()
                .libelle("VC")
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .build();
        typeReunionDTOList.add(typeReunionDTO1);
        TypeReunionDTO typeReunionDTO2 = TypeReunionDTO.builder()
                .libelle("SPEC")
                .equipements(Arrays.asList("Tableau"))
                .build();
        typeReunionDTOList.add(typeReunionDTO2);


        when(typeReunionPersistencePort.getAllTypesReunion()).thenReturn(typeReunionDTOList);

        // Test
        List<TypeReunionDTO> result = typeReunionService.getAllTypesReunion();

        // Verify
        Assertions.assertEquals(typeReunionDTOList.size(), result.size());
        Assertions.assertEquals(typeReunionDTOList.get(0).getLibelle(), result.get(0).getLibelle());
        Assertions.assertEquals(typeReunionDTOList.get(0).getEquipements(), result.get(0).getEquipements());
        Assertions.assertEquals(typeReunionDTOList.get(1).getLibelle(), result.get(1).getLibelle());
        Assertions.assertEquals(typeReunionDTOList.get(1).getEquipements(), result.get(1).getEquipements());



    }



}