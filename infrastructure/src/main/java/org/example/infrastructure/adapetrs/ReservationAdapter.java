package org.example.infrastructure.adapetrs;


import org.example.infrastructure.Entity.Reservation;
import org.example.infrastructure.Entity.Salle;
import org.example.infrastructure.Entity.TypeReunion;
import org.example.infrastructure.Mappers.ReservationMapper;
import org.example.infrastructure.Mappers.SalleMapper;
import org.example.infrastructure.Repository.ReservationRepository;
import org.example.infrastructure.Repository.SalleRepository;
import org.example.infrastructure.Repository.TypeReunionRepository;
import org.example.domain.DTO.ReservationDTO;
import org.example.domain.ports.spi.ReservationPersistencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationAdapter implements ReservationPersistencePort {

    @Autowired
    private ReservationRepository reservationRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private TypeReunionRepository typeReunionRepository;

    @Override
    public ReservationDTO addReservation(ReservationDTO reservationDTO) {


       Reservation reservation = ReservationMapper.INSTANCE.reservationDTOToReservation(reservationDTO);
       Reservation reservationSaved = reservationRepository.save(reservation);
      return (ReservationDTO) ReservationMapper.INSTANCE.reservationToReservationDTO(reservationSaved);
    }

    @Override
    public List<List<Object>> trouverSallesDisponibles(List<ReservationDTO> reservationDTOs) {
        List<Salle> salles = salleRepository.findAll();
        List<List<Object>> sallesDisponibles = new ArrayList<>();

        for (ReservationDTO reservationDTO : reservationDTOs) {
            Reservation reservation = ReservationMapper.INSTANCE.reservationDTOToReservation(reservationDTO);
            TypeReunion typeReunion = typeReunionRepository.findByLibelleIs(reservation.getTypeReunion())
                    .orElseThrow(() -> new RuntimeException("TypeReunion non trouvé pour le libelle " + reservation.getTypeReunion()));
            List<Object> salleReservation = new ArrayList<>();
            boolean salleTrouvee = false;
            for (Salle salle : salles) {
                if (reservation.getNbPersonnes() <= salle.getCapaciteMax() * 0.7 && typeReunion.getEquipements().equals(salle.getEquipements())) {
                    LocalTime debutReservation = reservation.getDebut();
                    LocalTime finReservation = reservation.getFin();
                    if (debutReservation.compareTo(salle.getFinOccupation()) >= 0) {
                        salle.setFinOccupation(finReservation.plusHours(1));
                        salleReservation.add(ReservationMapper.INSTANCE.reservationToReservationDTO(reservation));
                        salleReservation.add(SalleMapper.INSTANCE.salleToSalleDTO(salle));
                        sallesDisponibles.add(salleReservation);
                        salleTrouvee = true;
                        break;
                    }
                }
            }
            if (!salleTrouvee) {
                List<Object> salleNonDisponible = new ArrayList<>();

                salleNonDisponible.add(ReservationMapper.INSTANCE.reservationToReservationDTO(reservation));
                salleNonDisponible.add("Aucune salle disponible pour la réservation " );
                sallesDisponibles.add(salleNonDisponible);
            }
        }
        return sallesDisponibles;
    }
}
