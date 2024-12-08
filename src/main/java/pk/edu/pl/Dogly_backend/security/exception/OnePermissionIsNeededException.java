package pk.edu.pl.Dogly_backend.security.exception;

public class OnePermissionIsNeededException extends RuntimeException {

  public OnePermissionIsNeededException(String mess) {
    super(mess);
  }
}
