package pk.edu.pl.Dogly_backend.dog;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pk.edu.pl.Dogly_backend.user.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface DogRepository extends CrudRepository<Dog, UUID> {
    List<Dog> findByOwner(User user);
}
