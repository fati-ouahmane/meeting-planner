package org.example.domain.service;

import org.example.domain.DTO.ReservationDTO;
import org.example.domain.DTO.SalleDTO;
import org.example.domain.DTO.TypeReunionDTO;
import org.example.domain.ports.spi.ReservationPersistencePort;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceImplTest {
    @InjectMocks
    private ReservationServiceImpl reservationService;

    @Mock
    private ReservationPersistencePort reservationPersistencePort;

    @Test
    void addReservation() {

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .debut(LocalTime.of(9, 0))
                .fin(LocalTime.of(10, 0))
                .typeReunion("VC")
                .nbPersonnes(8)
                .build();

        when(reservationPersistencePort.addReservation(any(ReservationDTO.class))).thenReturn(reservationDTO);


        ReservationDTO result = reservationService.addReservation(reservationDTO);

       Assertions.assertEquals(reservationDTO.getDebut(), result.getDebut());
        Assertions.assertEquals(reservationDTO.getFin(), result.getFin());
        Assertions.assertEquals(reservationDTO.getTypeReunion(), result.getTypeReunion());
        Assertions.assertEquals(reservationDTO.getNbPersonnes(), result.getNbPersonnes());
}



    @Test
    void trouverSallesDisponibles() {
        List<ReservationDTO> reservationDTOList = new ArrayList<>();
        ReservationDTO reservationDTO1 = ReservationDTO.builder()
                .debut(LocalTime.of(9, 0))
                .fin(LocalTime.of(10, 0))
                .typeReunion("VC")
                .nbPersonnes(8)
                .build();
        reservationDTOList.add(reservationDTO1);
        ReservationDTO reservationDTO2 = ReservationDTO.builder()
                .debut(LocalTime.of(9, 0))
                .fin(LocalTime.of(10, 0))
                .typeReunion("VC")
                .nbPersonnes(16)
                .build();
        reservationDTOList.add(reservationDTO2);

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

        when(reservationPersistencePort.trouverSallesDisponibles(reservationDTOList)).thenReturn(Arrays.asList(
                Arrays.asList(reservationDTOList.get(0), salleDTOList.get(0) ),
                Arrays.asList(reservationDTOList.get(1),salleDTOList.get(1) )

        ));

        List<List<Object>> result = reservationService.trouverSallesDisponibles(reservationDTOList);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(reservationDTO1, result.get(0).get(0));
        assertEquals(salleDTO1, result.get(0).get(1));
        assertEquals(reservationDTO2, result.get(1).get(0));
        assertEquals(salleDTO2, result.get(1).get(1));


    }
}