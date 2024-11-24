package pk.edu.pl.Dogly_backend.security.authenticatedUser;

import pk.edu.pl.Dogly_backend.user.User;

public interface IAuthenticationFacade {
  User getAuthentication();
}
