package org.example.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalTime;


@Builder
@Data

public class ReservationDTO {



    private String id;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime debut;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime fin;
    private String typeReunion;
    private int nbPersonnes;

    public ReservationDTO() {
    }

    public ReservationDTO(String id, LocalTime debut, LocalTime fin, String typeReunion, int nbPersonnes) {
        this.id = id;
        this.debut = debut;
        this.fin = fin;
        this.typeReunion = typeReunion;
        this.nbPersonnes = nbPersonnes;
    }

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
}
