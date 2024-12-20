package pk.edu.pl.Dogly_backend.dog;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IDogService {
  DogResponse addDog(DogRequest dogRequest, MultipartFile[] files);

    List<DogResponse> getLoggedUserDog();

  void deleteDogById(String id);

  DogResponse editDog(DogRequest dogRequest, MultipartFile[] files);

  List<DogResponse> getAllDog();
}

