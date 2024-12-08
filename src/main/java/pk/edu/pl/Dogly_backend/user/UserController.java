package pk.edu.pl.Dogly_backend.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pk.edu.pl.Dogly_backend.user.dto.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class UserController {

  private final CustomUserDetailsService userDetailsService;

  @PostMapping(
    value = "/api/auth/signup",
    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
  )
  public ResponseEntity<UserResponse> createUser(
    @RequestPart("user") @Valid UserRequest userRequest,
    @RequestPart("imageFiles") MultipartFile[] multipartFiles
  ) {
    return new ResponseEntity<>(userDetailsService.addUser(userRequest, multipartFiles), HttpStatus.CREATED);
  }

  @PutMapping("/api/logout")
  public void logout() {
    userDetailsService.setStateOfUser(false);
  }

  @PostMapping("/api/authenticate")
  public ResponseEntity<JwtResponse> createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    return new ResponseEntity<>(userDetailsService.createJwtToken(jwtRequest), HttpStatus.CREATED);
  }

  @GetMapping("/api/user")
  public ResponseEntity<List<UserResponse>> getAllUsers() {
    return ResponseEntity.ok(userDetailsService.getAll());
  }

  @GetMapping("/api/user/{email}")
  public ResponseEntity<UserResponse> getUserByEmail(@PathVariable String email) {
    return ResponseEntity.ok(userDetailsService.getUser(email));
  }

  @GetMapping("/api/user/logged")
  public ResponseEntity<UserResponse> getLoggedUser() {
    return ResponseEntity.ok(userDetailsService.getLoggedUser());
  }

  @DeleteMapping("/api/user/{email}")
  public void deleteUser(@PathVariable String email) {
    userDetailsService.deleteUser(email);
  }

  @PutMapping("/api/user/update/password")
  public ResponseEntity<PasswordChangeResponse> updatePassword(@Valid @RequestBody PasswordChangeRequest passwordChangeRequest) {
    return ResponseEntity.ok(
      userDetailsService.updatePassword(passwordChangeRequest.getNewPassword(), passwordChangeRequest.getId())
    );
  }

  @PutMapping(
    value = "/api/user/update",
    consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}
  )

  public ResponseEntity<UserResponse> updateUser(
    @RequestPart("user") UserRequest userRequest,
    @RequestPart(name = "imageFiles", required = false) MultipartFile[] multipartFiles
  ) {
    return ResponseEntity.ok(userDetailsService.updateUser(userRequest, multipartFiles));
  }
}
