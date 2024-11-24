package pk.edu.pl.Dogly_backend.security.role;

import org.springframework.data.repository.CrudRepository;

public interface GroupRepository extends CrudRepository<Group, Long> {
  Group findByRole(Role role);
}
