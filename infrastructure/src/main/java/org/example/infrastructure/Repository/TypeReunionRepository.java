package org.example.infrastructure.Repository;



import org.example.infrastructure.Entity.TypeReunion;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository

public interface TypeReunionRepository extends MongoRepository<TypeReunion, String> {



    Optional<TypeReunion> findByLibelleIs(String libelle);
}
