package org.example.infrastructure.Mappers;

import org.example.domain.DTO.ReservationDTO;
import org.example.infrastructure.Entity.Reservation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface ReservationMapper {

    ReservationMapper INSTANCE = Mappers.getMapper(ReservationMapper.class);


    @Mapping(target = "debut",
            expression = "java(java.time.LocalTime.parse(reservation.getDebut().toString(), java.time.format.DateTimeFormatter.ISO_LOCAL_TIME))")
    @Mapping(target = "fin",
            expression = "java(java.time.LocalTime.parse(reservation.getFin().toString(), java.time.format.DateTimeFormatter.ISO_LOCAL_TIME))")
    ReservationDTO reservationToReservationDTO(Reservation reservation);

    @Mapping(target = "debut",
            expression = "java(java.time.LocalTime.parse(reservationDTO.getDebut().toString(), java.time.format.DateTimeFormatter.ISO_LOCAL_TIME))")
    @Mapping(target = "fin",
            expression = "java(java.time.LocalTime.parse(reservationDTO.getFin().toString(), java.time.format.DateTimeFormatter.ISO_LOCAL_TIME))")
    Reservation reservationDTOToReservation(ReservationDTO reservationDTO);

    List<ReservationDTO> reservationListToReservationDTOList(List<Reservation> reservationList);

    List<Reservation> reservationDTOListToReservationList(List<ReservationDTO> reservationDTOList);

}
