package org.example.domain.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
public class TypeReunionDTO {
    private String id;
    private String libelle;
    private List<String> equipements;

    public TypeReunionDTO(String id, String libelle, List<String> equipements) {
        this.id = id;
        this.libelle = libelle;
        this.equipements = equipements;
    }

    public TypeReunionDTO() {
    }

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
}
