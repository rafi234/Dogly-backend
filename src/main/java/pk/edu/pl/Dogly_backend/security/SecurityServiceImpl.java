package pk.edu.pl.Dogly_backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pk.edu.pl.Dogly_backend.security.exception.OneAdminIsNeededException;
import pk.edu.pl.Dogly_backend.security.exception.OnePermissionIsNeededException;
import pk.edu.pl.Dogly_backend.security.exception.OnlyOneOwnerException;
import pk.edu.pl.Dogly_backend.security.role.Group;
import pk.edu.pl.Dogly_backend.security.role.GroupRepository;
import pk.edu.pl.Dogly_backend.security.role.Role;
import pk.edu.pl.Dogly_backend.user.User;
import pk.edu.pl.Dogly_backend.user.UserRepository;
import pk.edu.pl.Dogly_backend.user.dto.UserResponse;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

  final private UserRepository userRepo;
  final private GroupRepository groupRepo;

  @Override
  public UserResponse grantPermission(String role, String id, String action) {
    if (role.equals("OWNER")) {
      throw new OnlyOneOwnerException("It must be only one owner!");
    }
    UUID uuid = UUID.fromString(id);
    User user = userRepo.findById(uuid).orElseThrow();
    Role newUserRole = Role.valueOf(role);
    Group group = groupRepo.findByRole(newUserRole);

    if (action.equals("grant")) {
      user.getRoles().add(group);
    } else if (action.equals("delete")) {
      if (user.getRoles().size() == 1) {
        throw new OnePermissionIsNeededException("User must have at least 1 permission");
      }
      if (role.equals("ADMIN")) {
        int numberOfAdmin = getNumberOfAdmins();
        if (numberOfAdmin == 1) {
          throw new OneAdminIsNeededException("It must be at least ADMIN on a server!");
        }
      }
      user.getRoles().remove(group);
    }
    return new UserResponse(userRepo.save(user));
  }

  private int getNumberOfAdmins() {
    List<User> users = (List<User>) userRepo.findAll();
    return users.stream()
      .filter(u -> u.getRoles().contains(groupRepo.findByRole(Role.ADMIN))).
      toArray().length;
  }
}
