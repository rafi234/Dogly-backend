package pk.edu.pl.Dogly_backend.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public final class PasswordChangeResponse {
  private String email;
  private String status;
}
