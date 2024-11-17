package pk.edu.pl.Dogly_backend.dog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public final class DogResponse {
  private UUID id;
  private String name;
  private Date dogsBirth;
  private String breed;
  private String ownerName;
  private String ownerSurname;
  private String ownerEmail;

  public DogResponse(Dog dog) {
    this.id = dog.getId();
    this.name = dog.getName();
    this.dogsBirth = dog.getDogsBirth();
    this.breed = dog.getBreed();
  }
}
