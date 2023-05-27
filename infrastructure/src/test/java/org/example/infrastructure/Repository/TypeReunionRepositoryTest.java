package org.example.infrastructure.Repository;

import org.example.domain.service.TypeReunionServiceImpl;
import org.example.infrastructure.Entity.TypeReunion;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TypeReunionRepositoryTest {


    @Mock
    private TypeReunionRepository typeReunionRepository;

    @InjectMocks
    private TypeReunionServiceImpl typeReunionService;

    @Test
    public void testFindByLibelleIs() {
        // given
        TypeReunion typeReunion = new TypeReunion();
        typeReunion.setLibelle("VC");
        typeReunion.setEquipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"));
        Optional<TypeReunion> optionalTypeReunion = Optional.of(typeReunion);
        when(typeReunionRepository.findByLibelleIs("VC")).thenReturn(optionalTypeReunion);


        Optional<TypeReunion> result = typeReunionRepository.findByLibelleIs("VC");

        assertEquals(result, optionalTypeReunion);
    }
}
