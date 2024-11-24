package pk.edu.pl.Dogly_backend.security.authenticatedUser;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import pk.edu.pl.Dogly_backend.security.UserDetailsImpl;
import pk.edu.pl.Dogly_backend.user.User;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

  @Override
  public User getAuthentication() {
    try {
      return ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
    } catch (ClassCastException e) {
      throw new RuntimeException("no user is logged in");
    }
  }
}
