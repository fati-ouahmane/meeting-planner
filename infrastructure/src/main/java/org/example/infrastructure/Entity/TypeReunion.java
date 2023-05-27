package org.example.infrastructure.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data

@Builder
@Document(collection = "typereunion")
public class TypeReunion {
    @Id
    private String id;

    private String libelle;

    private List<String> equipements;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<String> getEquipements() {
        return equipements;
    }

    public void setEquipements(List<String> equipements) {
        this.equipements = equipements;
    }

    public TypeReunion() {
    }

    public TypeReunion(String id, String libelle, List<String> equipements) {
        this.id = id;
        this.libelle = libelle;
        this.equipements = equipements;
    }


}
