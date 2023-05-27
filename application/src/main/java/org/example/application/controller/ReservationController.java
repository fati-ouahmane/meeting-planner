package org.example.application.controller;

import org.example.domain.DTO.ReservationDTO;
import org.example.domain.ports.api.ReservationServicePort;
import org.example.domain.ports.api.SalleServicePort;
import org.example.domain.ports.api.TypeReunionServicePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@CrossOrigin(origins = "*", maxAge = 3600)

public class ReservationController {


    @Autowired
    ReservationServicePort reservationServicePort;

    @Autowired
    SalleServicePort salleServicePort;

    @Autowired
    TypeReunionServicePort typeReunionServicePort;

    @PostMapping(value = "/addReservation", consumes = "application/json", produces = "application/json")

    public ReservationDTO addReservation(@RequestBody ReservationDTO reservationDTO) {


        return reservationServicePort.addReservation(reservationDTO);
    }

    @GetMapping("/sallesdisponibles")
    public List<List<Object>> trouverSallesDisponibles(@RequestBody List<ReservationDTO> reservationDTOS) {
        return reservationServicePort.trouverSallesDisponibles(reservationDTOS);
    }
}
