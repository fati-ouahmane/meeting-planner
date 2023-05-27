package org.example.domain.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;


@Builder
@Data
public class SalleDTO {

    private String id;
    private String nom;
    private int capaciteMax;
    private List<String> equipements;
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime finOccupation;

    public SalleDTO() {
    }

    public SalleDTO(String id, String nom, int capaciteMax, List<String> equipements, LocalTime finOccupation) {
        this.id = id;
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.equipements = equipements;
        this.finOccupation = finOccupation;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(int capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public List<String> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<String> equipements) {
        this.equipements = equipements;
    }

    public LocalTime getFinOccupation() {
        return finOccupation;
    }

    public void setFinOccupation(LocalTime finOccupation) {
        this.finOccupation = finOccupation;
    }
}

