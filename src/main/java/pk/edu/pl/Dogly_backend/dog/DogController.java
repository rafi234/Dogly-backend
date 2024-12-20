package pk.edu.pl.Dogly_backend.dog;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pk.edu.pl.Dogly_backend.security.annotation.IsAdminOrOwner;
import pk.edu.pl.Dogly_backend.security.annotation.IsUser;
import pk.edu.pl.Dogly_backend.security.annotation.IsUserLogged;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dog")
public class DogController {

  private final IDogService dogService;

  @PostMapping(consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
  @IsUser
  public ResponseEntity<DogResponse> createDog(@RequestPart("dog") @Valid DogRequest dogRequest,
                                               @RequestPart("imageFile") MultipartFile[] images
  ) {
    return new ResponseEntity<>(dogService.addDog(dogRequest, images), HttpStatus.CREATED);
  }

  @GetMapping("/user")
  @IsUser
  public ResponseEntity<List<DogResponse>> getLoggedUserDogs() {
    return ResponseEntity.ok(dogService.getLoggedUserDog());
  }

  @PutMapping()
  @IsUserLogged
  public ResponseEntity<DogResponse> editDog(@RequestPart("dog") @Valid DogRequest dogRequest,
                                             @RequestPart("imageFiles") MultipartFile[] files) {
    return ResponseEntity.ok(dogService.editDog(dogRequest, files));
  }

  @DeleteMapping("/{id}")
  @IsUserLogged
  public void deleteDogById(@PathVariable String id) {
    dogService.deleteDogById(id);
  }

  @GetMapping()
  @IsAdminOrOwner
  public ResponseEntity<List<DogResponse>> getAllDogs() {
    return ResponseEntity.ok(dogService.getAllDog());
  }
}
