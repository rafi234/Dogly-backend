package pk.edu.pl.Dogly_backend.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pk.edu.pl.Dogly_backend.dog.Dog;
import pk.edu.pl.Dogly_backend.dog_ad.DogAd;
import pk.edu.pl.Dogly_backend.security.role.Group;
import pk.edu.pl.Dogly_backend.user.address.Address;

import javax.persistence.*;
import java.util.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "users")
public class User {
  @Id
  @Column(name = "user_id")
  private UUID id;
  private String name;
  private String surname;
  @Column(nullable = false)
  private String email;
  @Column(nullable = false)
  private String password;

  private int phoneNumber;

  private boolean isActive = false;

  @ManyToMany(
    cascade = {CascadeType.PERSIST, CascadeType.MERGE},
    fetch = FetchType.EAGER
  )
  @JoinTable(
    name = "user_group",
    joinColumns = @JoinColumn(name = "user_id"),
    inverseJoinColumns = @JoinColumn(name = "role_id")
  )
  private Set<Group> roles = new HashSet<>();

  @OneToOne(orphanRemoval = true)
  @JoinColumn(name = "address_id", referencedColumnName = "id")
  private Address address;

  @OneToMany(mappedBy = "owner")
  private List<Dog> dogs = new ArrayList<>();

  @OneToMany(mappedBy = "user")
  private List<DogAd> dogAds = new ArrayList<>();

  @OneToMany(mappedBy = "confirmedUser")
  private Set<DogAd> confirmedDogAds = new HashSet<>();

  public User(UUID id, String name, String surname, String email, String password, int phoneNumber) {
    this.id = id;
    this.name = name;
    this.surname = surname;
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof User) {
      return ((User) obj).id.equals(this.id);
    }
    return false;
  }
}
