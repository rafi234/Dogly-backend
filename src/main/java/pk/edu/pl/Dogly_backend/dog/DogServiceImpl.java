package pk.edu.pl.Dogly_backend.dog;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional
@RequiredArgsConstructor
public class DogServiceImpl implements IDogService {

  private final DogRepository dogRepository;

  @Override
  public DogResponse addDog(DogRequest dogRequest, MultipartFile[] files) {

    Dog dog = new Dog();
    dog.setId(UUID.randomUUID());
    dog.setName(dogRequest.name());
    dog.setBreed(dogRequest.breed());
    dog.setDogsBirth(dogRequest.dogsBirth());

    Dog savedDog = dogRepository.save(dog);
    return new DogResponse(savedDog);
  }

  @Override
  public DogResponse editDog(DogRequest dogRequest, MultipartFile[] files) {
    Dog dog = dogRepository.findById(UUID.fromString(dogRequest.id())).orElseThrow();

    if (!dogRequest.dogsBirth().equals(dog.getDogsBirth()))
      dog.setDogsBirth(dogRequest.dogsBirth());

    if (!dogRequest.name().equals(dog.getName()))
      dog.setName(dogRequest.name());

    if (!dogRequest.breed().equals(dog.getBreed()))
      dog.setBreed(dogRequest.breed());

    Dog savedDog = dogRepository.save(dog);

    return new DogResponse(savedDog);
  }

  @Override
  public List<DogResponse> getAllDog() {
    List<Dog> dogs = (List<Dog>) dogRepository.findAll();
    return dogListToDogResponseList(dogs);
  }

  @Override
  public void deleteDogById(String id) {
    UUID uuid = UUID.fromString(id);
    dogRepository.deleteById(uuid);
  }

  private List<DogResponse> dogListToDogResponseList(List<Dog> dogs) {
    return dogs.stream()
      .map(DogResponse::new)
      .collect(Collectors.toList());
  }
}
