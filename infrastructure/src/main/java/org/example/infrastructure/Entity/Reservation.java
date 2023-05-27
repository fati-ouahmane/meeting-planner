package org.example.infrastructure.Entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Data

@Builder
@Document(collection = "reservations")
public class Reservation {
    @Id
    private String id;
    private LocalTime debut;
    private LocalTime fin;
    private String typeReunion;
    private int nbPersonnes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalTime getDebut() {
        return debut;
    }

    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }

    public LocalTime getFin() {
        return fin;
    }

    public void setFin(LocalTime fin) {
        this.fin = fin;
    }

    public String getTypeReunion() {
        return typeReunion;
    }

    public void setTypeReunion(String typeReunion) {
        this.typeReunion = typeReunion;
    }

    public int getNbPersonnes() {
        return nbPersonnes;
    }

    public void setNbPersonnes(int nbPersonnes) {
        this.nbPersonnes = nbPersonnes;
    }

    public Reservation() {
    }

    public Reservation(String id, LocalTime debut, LocalTime fin, String typeReunion, int nbPersonnes) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.typeReunion = typeReunion;
        this.nbPersonnes = nbPersonnes;
    }

}
