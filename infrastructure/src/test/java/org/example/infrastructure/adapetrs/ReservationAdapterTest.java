package org.example.infrastructure.adapetrs;

import org.example.domain.DTO.ReservationDTO;
import org.example.domain.DTO.SalleDTO;
import org.example.infrastructure.Entity.Reservation;
import org.example.infrastructure.Entity.Salle;
import org.example.infrastructure.Entity.TypeReunion;
import org.example.infrastructure.Mappers.ReservationMapper;
import org.example.infrastructure.Mappers.SalleMapper;
import org.example.infrastructure.Repository.ReservationRepository;
import org.example.infrastructure.Repository.SalleRepository;
import org.example.infrastructure.Repository.TypeReunionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ReservationAdapterTest {


    @Mock
    private ReservationRepository reservationRepository;
    @Mock
    private SalleRepository salleRepository;
    @Mock
    private TypeReunionRepository typeReunionRepository;
    @InjectMocks
    private ReservationAdapter reservationAdapter;

    @Test
    void addReservationTest() {

        ReservationDTO reservationDTO = ReservationDTO.builder()
                .debut(LocalTime.of(9, 0))
                .fin(LocalTime.of(10, 0))
                .typeReunion("VC")
                .nbPersonnes(8)
                .build();

        Reservation reservation = new Reservation();
        reservation.setDebut(reservationDTO.getDebut());
        reservation.setFin(reservationDTO.getFin());
        reservation.setTypeReunion(reservationDTO.getTypeReunion());
        reservation.setNbPersonnes(reservationDTO.getNbPersonnes());

        when(reservationRepository.save(reservation)).thenReturn(reservation);

        ReservationDTO result = reservationAdapter.addReservation(reservationDTO);



        Assertions.assertEquals(reservationDTO.getDebut(), result.getDebut());
        Assertions.assertEquals(reservationDTO.getFin(), result.getFin());
        Assertions.assertEquals(reservationDTO.getTypeReunion(), result.getTypeReunion());
        Assertions.assertEquals(reservationDTO.getNbPersonnes(), result.getNbPersonnes());
    }

    @Test
    void trouverSallesDisponiblesTest() {

        List<Salle> salleList = new ArrayList<>();
        Salle salle1 = new Salle();
        salle1.setNom("E3001");
        salle1.setCapaciteMax(13);
        salle1.setEquipements(Arrays.asList("Ecran",
                "Webcam",
                "Pieuvre"));
        salle1.setFinOccupation(LocalTime.of(8, 0));
        salleList.add(salle1);

        Salle salle2 = new Salle();
        salle2.setNom("E1002");
        salle2.setCapaciteMax(10);
        salle2.setEquipements(Arrays.asList("Ecran"));
        salle2.setFinOccupation(LocalTime.of(8, 0));
        salleList.add(salle2);

        TypeReunion typeReunion = new TypeReunion();
        typeReunion.setLibelle("VC");
        typeReunion.setEquipements(Arrays.asList("Ecran", "Webcam", "Pieuvre"));


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
                .nbPersonnes(6)
                .build();
        reservationDTOList.add(reservationDTO2);
        List<SalleDTO> salleDTOList = SalleMapper.INSTANCE.salleListToSalleDTOList(salleList);
        salleDTOList.get(0).setFinOccupation(LocalTime.of(11, 0));
        List<List<Object>> sallesdisponibles = new ArrayList<>();
        sallesdisponibles.add(Arrays.asList(  reservationDTOList.get(0),salleDTOList.get(0)));
        sallesdisponibles.add(Arrays.asList( reservationDTOList.get(1), "Aucune salle disponible pour la réservation "));


        when(salleRepository.findAll()).thenReturn(salleList);
        Reservation reservation = ReservationMapper.INSTANCE.reservationDTOToReservation(reservationDTO1);
        when(typeReunionRepository.findByLibelleIs(eq(reservation.getTypeReunion()))).thenReturn(Optional.of(typeReunion));

        List<List<Object>> resultats = reservationAdapter.trouverSallesDisponibles(reservationDTOList);
        assertEquals(sallesdisponibles, resultats);

    }
}