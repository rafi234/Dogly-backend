package pk.edu.pl.Dogly_backend.user.user_exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserAlreadyExist extends ResponseStatusException {
  public UserAlreadyExist(String message) {
    super(HttpStatus.BAD_REQUEST, message);
  }
}
