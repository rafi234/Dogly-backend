package pk.edu.pl.Dogly_backend.image;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import pk.edu.pl.Dogly_backend.dog.Dog;
import pk.edu.pl.Dogly_backend.user.User;

import java.util.UUID;

@Repository
public interface ImageRepository extends CrudRepository<Image, UUID> {
    void deleteByDog(Dog dog);
    void deleteByUser(User user);
}
