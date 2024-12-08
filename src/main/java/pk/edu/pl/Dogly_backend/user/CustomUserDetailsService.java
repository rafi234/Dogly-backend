package pk.edu.pl.Dogly_backend.user;

import org.springframework.web.multipart.MultipartFile;
import pk.edu.pl.Dogly_backend.user.dto.*;

import java.util.List;

public interface CustomUserDetailsService {
  UserResponse addUser(UserRequest userRequest, MultipartFile[] multipartFiles);

  List<UserResponse> getAll();

  UserResponse getUser(String email);

  void deleteUser(String email);

  PasswordChangeResponse updatePassword(String newPassword, String email);

  void setStateOfUser(boolean state);

  UserResponse updateUser(UserRequest userRequest, MultipartFile[] multipartFiles);

  JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception;

  UserResponse getLoggedUser();
}
