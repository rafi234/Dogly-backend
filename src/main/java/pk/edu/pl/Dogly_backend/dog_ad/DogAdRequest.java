package pk.edu.pl.Dogly_backend.dog_ad;

import lombok.Data;
import lombok.NoArgsConstructor;
import pk.edu.pl.Dogly_backend.user.dto.UserRequest;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public final class DogAdRequest {

  private String id;

  private Set<String> dogIds;

  @NotNull(message = "Date can't be null!")
  private LocalDateTime date;

  @NotNull(message = "Remuneration can't be null!")
  private double price;

  private String description;

  private UserRequest user;
  private AdState adState;
}
