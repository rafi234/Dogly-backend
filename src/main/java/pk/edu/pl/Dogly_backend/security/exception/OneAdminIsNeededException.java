package pk.edu.pl.Dogly_backend.security.exception;

public class OneAdminIsNeededException extends RuntimeException {

  public OneAdminIsNeededException(String mess) {
    super(mess);
  }
}
