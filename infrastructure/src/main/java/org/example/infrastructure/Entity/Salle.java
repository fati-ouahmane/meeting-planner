package org.example.infrastructure.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Document(collection = "salles")
public class Salle {

    @Id
    private String id;

    private String nom;

    private int capaciteMax;

    private List<String> equipements;

    private LocalTime finOccupation;

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

    public Salle() {
    }

    public Salle(String id, String nom, int capaciteMax, List<String> equipements, LocalTime finOccupation) {
        this.id = id;
        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.equipements = equipements;
        this.finOccupation = finOccupation;
    }
    public Salle( String nom, int capaciteMax, List<String> equipements, LocalTime finOccupation) {

        this.nom = nom;
        this.capaciteMax = capaciteMax;
        this.equipements = equipements;
        this.finOccupation = finOccupation;
    }
}