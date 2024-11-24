package pk.edu.pl.Dogly_backend.user.dto;

import lombok.Getter;
import lombok.Setter;
import pk.edu.pl.Dogly_backend.dog.Dog;
import pk.edu.pl.Dogly_backend.dog.DogResponse;
import pk.edu.pl.Dogly_backend.security.role.Group;
import pk.edu.pl.Dogly_backend.user.User;
import pk.edu.pl.Dogly_backend.user.address.Address;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public final class UserResponse {
  private String id;
  private String name;
  private String surname;
  private String email;
  private Address address;
  private List<DogResponse> dogs;
  private Set<String> roles;
  private boolean isActive;
  private int phoneNumber;

  public UserResponse(User user) {
    this.id = user.getId().toString();
    this.name = user.getName();
    this.surname = user.getSurname();
    this.email = user.getEmail();
    this.address = user.getAddress();
    this.dogs = getDogList(user.getDogs());
    this.isActive = user.isActive();
    this.phoneNumber = user.getPhoneNumber();
  }

    private Set<String> getSetOfRoles(Set<Group> rolesToMap) {
        Set<String> retVal = new HashSet<>();
        for (Group group : rolesToMap) {
            retVal.add("ROLE_" + group.getRole().name());
        }
        return retVal;
    }

  private List<DogResponse> getDogList(List<Dog> dogs) {
    return dogs.stream().map(DogResponse::new)
      .collect(Collectors.toList());
  }
}
