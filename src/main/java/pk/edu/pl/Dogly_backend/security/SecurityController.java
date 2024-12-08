package pk.edu.pl.Dogly_backend.security;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pk.edu.pl.Dogly_backend.user.dto.UserResponse;

@RestController
@RequiredArgsConstructor
public class SecurityController {

  final private SecurityService securityService;

  @PutMapping("/api/grant/user/{id}")
  public ResponseEntity<UserResponse> grantPermissions(
    @RequestParam String role,
    @RequestParam String action,
    @PathVariable String id
  ) {
    return new ResponseEntity<>(securityService.grantPermission(role, id, action), HttpStatus.OK);
  }
}
