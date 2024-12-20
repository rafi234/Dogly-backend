package pk.edu.pl.Dogly_backend.security.role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pk.edu.pl.Dogly_backend.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "roles")
public class Group implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Enumerated(EnumType.STRING)
  private Role role;

  @ManyToMany(mappedBy = "roles")
  private Set<User> users = new HashSet<>();

  public Group(Role role) {
    this.role = role;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Group group) {
      return group.getRole().equals(this.role);
    }
    return false;
  }
}
