package org.example.infrastructure.adapetrs;

import org.example.domain.DTO.TypeReunionDTO;
import org.example.infrastructure.Entity.TypeReunion;
import org.example.infrastructure.Mappers.TypeReunionMapper;
import org.example.infrastructure.Repository.TypeReunionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TypeReunionAdapterTest {

    @InjectMocks
    private TypeReunionAdapter typeReunionAdapter;

    @Mock
    private TypeReunionRepository typeReunionRepository;


    @Test
    void addTypeReunionTest() {
        TypeReunionDTO typeReunionDTO = TypeReunionDTO.builder()
                .libelle("VC")
                .equipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"))
                .build();

        TypeReunion typeReunion = TypeReunionMapper.INSTANCE.typeReunionDTOToTypeReunion(typeReunionDTO);

       Mockito.when(typeReunionRepository.save(Mockito.any(TypeReunion.class))).thenReturn(typeReunion);
       TypeReunionDTO result = typeReunionAdapter.addTypeReunion(typeReunionDTO);

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getLibelle(), typeReunionDTO.getLibelle());
        Assertions.assertEquals(result.getEquipements(), typeReunionDTO.getEquipements()
        );

    }

    @Test
    void getAllTypesReunionTest() {

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

        List<TypeReunion> typeReunionList = TypeReunionMapper.INSTANCE.typeReunionDTOListToTypeReunionList(typeReunionDTOList);
        Mockito.when(typeReunionRepository.findAll()).thenReturn(typeReunionList);


        List<TypeReunionDTO> result = typeReunionAdapter.getAllTypesReunion();


        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.size(), typeReunionList.size());
        Assertions.assertEquals(result.get(0).getLibelle(), typeReunionDTO1.getLibelle());
        Assertions.assertEquals(result.get(0).getEquipements(), typeReunionDTO1.getEquipements());
        Assertions.assertEquals(result.get(1).getLibelle(), typeReunionDTO2.getLibelle());
        Assertions.assertEquals(result.get(1).getEquipements(), typeReunionDTO2.getEquipements());

    }
}