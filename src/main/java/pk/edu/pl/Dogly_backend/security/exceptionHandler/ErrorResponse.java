package pk.edu.pl.Dogly_backend.security.exceptionHandler;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorResponse {
  private String message;
}
