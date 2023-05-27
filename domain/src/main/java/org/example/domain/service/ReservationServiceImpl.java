package org.example.domain.service;

import org.example.domain.ports.api.ReservationServicePort;
import org.example.domain.DTO.ReservationDTO;
import org.example.domain.ports.spi.ReservationPersistencePort;

import java.util.List;

public class ReservationServiceImpl implements ReservationServicePort {

    private ReservationPersistencePort reservationPersistencePort;

    public ReservationServiceImpl(ReservationPersistencePort reservationPersistencePort){
        this.reservationPersistencePort= reservationPersistencePort;
    }
    @Override
    public ReservationDTO addReservation(ReservationDTO reservationDTO) {
        return reservationPersistencePort.addReservation(reservationDTO);
    }

    @Override
    public List<List<Object>> trouverSallesDisponibles(List<ReservationDTO> reservationDTOs) {
        return  reservationPersistencePort.trouverSallesDisponibles(reservationDTOs);
    }
}
