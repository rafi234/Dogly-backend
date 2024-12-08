package pk.edu.pl.Dogly_backend.meetings.exception;

public class WrongRequestParamsException extends RuntimeException {

  public WrongRequestParamsException(String mess) {
    super(mess);
  }
}
