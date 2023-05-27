package org.example.infrastructure.Repository;



import org.example.infrastructure.Entity.Salle;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleRepository extends MongoRepository<Salle, String> {

}
