package pk.edu.pl.Dogly_backend.meetings;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pk.edu.pl.Dogly_backend.meetings.scrapler.DogPark;
import pk.edu.pl.Dogly_backend.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Meeting implements Serializable {

  @Id
  private UUID id;
  @ManyToOne
  @JoinColumn(name = "user_id")
  private User user;
  private String title;
  private String description;
  @Column(name = "date")
  private LocalDateTime date;
  @Column(name = "added_at")
  private LocalDateTime addedAt;
  @ManyToOne
  @JoinColumn(name = "dog_park_id")
  private DogPark dogPark;

  @ManyToMany
  @JoinTable(
    name = "interested_user",
    joinColumns = @JoinColumn(name = "interested_Id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> interestedUsers = new HashSet<>();
  @ManyToMany
  @JoinTable(
    name = "going_user",
    joinColumns = @JoinColumn(name = "going_Id"),
    inverseJoinColumns = @JoinColumn(name = "user_id")
  )
  private Set<User> goingUsers = new HashSet<>();
}
