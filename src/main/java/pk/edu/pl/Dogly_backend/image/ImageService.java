package pk.edu.pl.Dogly_backend.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pk.edu.pl.Dogly_backend.dog.Dog;
import pk.edu.pl.Dogly_backend.user.User;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void saveImage(Image image) {
        imageRepository.save(image);
    }

    public Set<Image> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<Image> imageSet = new HashSet<>();
        for (MultipartFile file : multipartFiles) {
            Image image = new Image(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageSet.add(image);
        }
        return imageSet;
    }

    public void deleteAllDogImages(Dog dog) {
        imageRepository.deleteByDog(dog);
    }

    public void deleteAllUserImages(User user) {
        imageRepository.deleteByUser(user);
    }
}
