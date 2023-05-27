package org.example.domain.ports.spi;

import org.example.domain.DTO.ReservationDTO;

import java.util.List;

public interface ReservationPersistencePort {


    ReservationDTO addReservation(ReservationDTO reservationDTO);
    List<List<Object>> trouverSallesDisponibles(List<ReservationDTO> reservationDTOs);

}
