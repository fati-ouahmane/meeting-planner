package org.example.domain.ports.api;

import org.example.domain.DTO.ReservationDTO;

import java.util.List;

public interface ReservationServicePort {

    ReservationDTO addReservation(ReservationDTO reservationDTO);
    List<List<Object>> trouverSallesDisponibles(List<ReservationDTO> reservationDTOs);

}
