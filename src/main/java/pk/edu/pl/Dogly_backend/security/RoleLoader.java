package pk.edu.pl.Dogly_backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pk.edu.pl.Dogly_backend.security.role.Group;
import pk.edu.pl.Dogly_backend.security.role.GroupRepository;
import pk.edu.pl.Dogly_backend.security.role.Role;

@Component
public class RoleLoader {

  private final GroupRepository groupRepository;

  @Autowired
  public RoleLoader(GroupRepository groupRepository) {
    this.groupRepository = groupRepository;
    createRoles();
  }

  private void createRoles() {
    if (groupRepository.count() != Role.values().length) {
      for (Role role : Role.values()) {
        groupRepository.save(new Group(role));
      }
    }
  }
}
