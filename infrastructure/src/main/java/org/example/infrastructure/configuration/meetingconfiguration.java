package org.example.infrastructure.configuration;


import org.example.infrastructure.adapetrs.ReservationAdapter;
import org.example.infrastructure.adapetrs.SalleAdapter;
import org.example.infrastructure.adapetrs.TypeReunionAdapter;
import org.example.domain.ports.api.ReservationServicePort;
import org.example.domain.ports.api.SalleServicePort;
import org.example.domain.ports.api.TypeReunionServicePort;
import org.example.domain.ports.spi.ReservationPersistencePort;
import org.example.domain.ports.spi.SallePersistencePort;
import org.example.domain.ports.spi.TypeReunionPersistencePort;
import org.example.domain.service.ReservationServiceImpl;
import org.example.domain.service.SalleServiceImpl;
import org.example.domain.service.TypeReunionServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class meetingconfiguration {


    @Bean
    public ReservationPersistencePort reservationPersistencePort(){
        return new ReservationAdapter();
    }

    @Bean
    public ReservationServicePort reservationServicePort(){
        return new ReservationServiceImpl(reservationPersistencePort());
    }

    @Bean
    public SallePersistencePort sallePersistencePort(){
        return new SalleAdapter();
    }

    @Bean
    public SalleServicePort salleServicePort(){
        return new SalleServiceImpl(sallePersistencePort());
    }

    @Bean
    public TypeReunionPersistencePort typeReunionPersistencePort(){
        return new TypeReunionAdapter();
    }

    @Bean
    public TypeReunionServicePort typeReunionServicePort(){
        return new TypeReunionServiceImpl(typeReunionPersistencePort());
    }


}
