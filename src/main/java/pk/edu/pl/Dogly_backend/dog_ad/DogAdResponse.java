package pk.edu.pl.Dogly_backend.dog_ad;

import lombok.Data;
import lombok.NoArgsConstructor;
import pk.edu.pl.Dogly_backend.dog.Dog;
import pk.edu.pl.Dogly_backend.dog.DogResponse;
import pk.edu.pl.Dogly_backend.user.dto.UserResponse;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public final class DogAdResponse {

  private String id;
  private double price;
  private String description;
  private UserResponse user;
  private Set<DogResponse> dogs;
  private LocalDateTime date;
  private LocalDateTime addedAt;
  private LocalDateTime confirmedAt;
  private AdState adState;


  public DogAdResponse(DogAd dogAd) {
    this.date = dogAd.getDate();
    this.addedAt = dogAd.getAddedAt();
    this.id = dogAd.getId().toString();
    this.price = dogAd.getPrice();
    this.description = dogAd.getDescription();
    this.user = new UserResponse(dogAd.getUser());
    this.dogs = createDogsSet(dogAd.getDogs());
    this.adState = dogAd.getAdState();
    this.confirmedAt = dogAd.getConfirmedAt();
  }

  private Set<DogResponse> createDogsSet(Set<Dog> dogs) {
    return dogs.stream()
      .map(DogResponse::new)
      .collect(Collectors.toSet());
  }
}
