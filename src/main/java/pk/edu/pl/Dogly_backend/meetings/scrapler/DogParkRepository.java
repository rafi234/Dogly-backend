package pk.edu.pl.Dogly_backend.meetings.scrapler;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface DogParkRepository extends CrudRepository<DogPark, Integer> {


  List<DogPark> findAll();

  List<DogPark> findAllByCity(String city);

  List<DogPark> findAllByVoivodeship(String voivodeship);
}
