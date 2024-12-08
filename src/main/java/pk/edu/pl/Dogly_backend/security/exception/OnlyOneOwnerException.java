package pk.edu.pl.Dogly_backend.security.exception;

public class OnlyOneOwnerException extends RuntimeException {

  public OnlyOneOwnerException(String mess) {
    super(mess);
  }
}
