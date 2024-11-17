package pk.edu.pl.Dogly_backend.dog_ad;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static pk.edu.pl.Dogly_backend.dog_ad.AdState.*;


@Service
@RequiredArgsConstructor
@Transactional
public class DogAdService {

  private final DogAdRepository dogAdRepository;


  public DogAdResponse addDogAd(DogAdRequest dogAdRequest) {
    DogAd dogAd = new DogAd();
    dogAd.setId(UUID.randomUUID());
    dogAd.setDate(dogAdRequest.getDate());
    dogAd.setAddedAt(LocalDateTime.now());
    dogAd.setDescription(dogAdRequest.getDescription());
    dogAd.setPrice(dogAdRequest.getPrice());
    return new DogAdResponse(dogAdRepository.save(dogAd));
  }

  public List<DogAdResponse> getAllDogAds(String page) {
    List<DogAd> dogAds = new ArrayList<>();
    switch (page) {
      case "walk" -> dogAds = dogAdRepository.findAllByAdState(WAITING_FOR_USER);
      case "user" -> {
        //TODO
      }
      case "admin" -> dogAds = dogAdRepository.findAll();
    }
    return mapDogAdsListToDTO(dogAds);
  }

  public void processDogAd(DogAdRequest dogAdRequest, String action) {
    UUID id = UUID.fromString(dogAdRequest.getId());
    LocalDateTime confirmedAt = LocalDateTime.now();
    System.out.println("\n\n" + action + "\n\n");
    switch (action) {
      case "confirm" -> {
        //TODO
      }
      case "denied" -> setConfirmation(id, confirmedAt, DENIED);
      case "allowed" -> setConfirmation(id, confirmedAt, ALLOWED);
      case "forbidden" -> {
        //TODO
      }
      case "confirmed" -> setConfirmation(id, confirmedAt, WAITING_FOR_REALIZATION);
      case "complited" -> setConfirmation(id, confirmedAt, COMPLITED);
    }
  }

  public void deleteDogAd(String id) {
    UUID uuid = UUID.fromString(id);
    dogAdRepository.deleteById(uuid);
  }

  private void setConfirmation(
    UUID dogAdId,
    LocalDateTime confirmedAt,
    AdState state
  ) {
    DogAd dogAd = dogAdRepository.findById(dogAdId).orElseThrow();
    dogAd.setConfirmedAt(confirmedAt);
    dogAd.setAdState(state);
    dogAdRepository.save(dogAd);
  }

  private List<DogAdResponse> mapDogAdsListToDTO(List<DogAd> dogAds) {
    return dogAds.stream()
      .map(DogAdResponse::new)
      .collect(Collectors.toList());
  }


  public DogAdResponse updateDogAd(DogAdRequest dogAdRequest) {
    UUID id = UUID.fromString(dogAdRequest.getId());
    DogAd dogAd = dogAdRepository.findById(id).orElseThrow();
    dogAd.setDate(dogAdRequest.getDate());
    dogAd.setPrice(dogAdRequest.getPrice());
    dogAd.setDescription(dogAdRequest.getDescription());
    return new DogAdResponse(dogAdRepository.save(dogAd));
  }
}
