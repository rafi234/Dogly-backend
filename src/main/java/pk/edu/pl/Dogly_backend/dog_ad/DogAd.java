package pk.edu.pl.Dogly_backend.dog_ad;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pk.edu.pl.Dogly_backend.dog.Dog;
import pk.edu.pl.Dogly_backend.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static pk.edu.pl.Dogly_backend.dog_ad.AdState.WAITING_FOR_USER;


@Entity
@Getter @Setter
@NoArgsConstructor
public class DogAd implements Serializable {

  @Id
  private UUID id;

  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime date;

  private String description;

  private double price;

  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime addedAt;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;

  @ManyToMany
  @JoinTable(
    name = "dog_ad_dogs",
    joinColumns = @JoinColumn(name = "dog_ad_id"),
    inverseJoinColumns = @JoinColumn(name = "dog_id")
  )
  private Set<Dog> dogs = new HashSet<>();

  @ManyToOne
  @JoinColumn(name = "confirmed_user")
  private User confirmedUser;

  @Column(columnDefinition = "TIMESTAMP")
  private LocalDateTime confirmedAt;

  @Enumerated(EnumType.STRING)
  private AdState adState = WAITING_FOR_USER;
}
