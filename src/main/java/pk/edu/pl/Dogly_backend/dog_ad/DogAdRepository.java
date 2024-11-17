package pk.edu.pl.Dogly_backend.dog_ad;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DogAdRepository extends CrudRepository<DogAd, UUID> {
    List<DogAd> findAll();
    List<DogAd> findAllByAdState(AdState state);
}
