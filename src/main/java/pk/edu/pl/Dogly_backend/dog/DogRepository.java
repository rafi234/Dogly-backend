package pk.edu.pl.Dogly_backend.dog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DogRepository extends CrudRepository<Dog, UUID> {
}
