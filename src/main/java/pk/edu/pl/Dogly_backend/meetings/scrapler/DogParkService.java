package pk.edu.pl.Dogly_backend.meetings.scrapler;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class DogParkService {

  private final DogParkRepository dogParkRepository;

  public List<DogPark> getAllParks() {
    return dogParkRepository.findAll();
  }

  public List<DogPark> getParksByCity(String city) {
    return dogParkRepository.findAllByCity(city);
  }

  public List<DogPark> getAllParksByVoivodeship(String voivodeship) {
    return dogParkRepository.findAllByVoivodeship(voivodeship);
  }

  @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.DAYS)
  public void updateDogParks() {
    final List<DogPark> dogParksLoaded = WepScrapper.getAllDogParks();
    final List<DogPark> dogParks = dogParkRepository.findAll();
    for (DogPark dogPark : dogParksLoaded) {
      if (!isDogParkInDatabase(dogParks, dogPark)) {
        dogParkRepository.save(dogPark);
      }
    }
  }

  private boolean isDogParkInDatabase(List<DogPark> dogParks, DogPark dogPark) {
    return dogParks.stream().anyMatch(dp -> dp.getLocation().equals(dogPark.getLocation()));
  }
}

