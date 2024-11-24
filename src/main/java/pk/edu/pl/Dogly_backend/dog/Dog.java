package pk.edu.pl.Dogly_backend.dog;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pk.edu.pl.Dogly_backend.dog_ad.DogAd;
import pk.edu.pl.Dogly_backend.image.Image;
import pk.edu.pl.Dogly_backend.user.User;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Dog implements Serializable {

  @Id
  private UUID id;

  @ManyToOne
  @JoinColumn(name = "owner_id")
  private User owner;

  @NotBlank
  private String name;

  private String breed;

  @Temporal(TemporalType.DATE)
  private Date dogsBirth;

  @OneToMany( mappedBy = "dog", cascade = {CascadeType.ALL})
  private Set<Image> images = new HashSet<>();

  @ManyToMany(cascade = {CascadeType.REMOVE}, mappedBy = "dogs")
  private Set<DogAd> dogAds = new HashSet<>();
}
