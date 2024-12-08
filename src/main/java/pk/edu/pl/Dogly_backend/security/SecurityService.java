package pk.edu.pl.Dogly_backend.security;

import pk.edu.pl.Dogly_backend.user.dto.UserResponse;

public interface SecurityService {
  UserResponse grantPermission(String role, String id, String action);
}
