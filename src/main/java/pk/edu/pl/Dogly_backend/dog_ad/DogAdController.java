package pk.edu.pl.Dogly_backend.dog_ad;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/dog/walks")
public class DogAdController {

    private final DogAdService dogAdService;

    @GetMapping()
    public ResponseEntity<List<DogAdResponse>> getAllDogAds(@RequestParam String page) {
        return ResponseEntity.ok(dogAdService.getAllDogAds(page));
    }

    @PostMapping()
    public ResponseEntity<DogAdResponse> createDogAd(@RequestBody @Valid DogAdRequest dogAdRequest) {
        return new ResponseEntity<>(dogAdService.addDogAd(dogAdRequest), HttpStatus.CREATED);
    }


    @PutMapping("/confirm")
    public void processingDogAd(
            @RequestParam(required = false) String action,
            @RequestBody @Valid DogAdRequest dogAdRequest
    ) {
        dogAdService.processDogAd(dogAdRequest, action);
    }

    @DeleteMapping("/{id}")
    public void deleteDogAd(@PathVariable String id) {
        dogAdService.deleteDogAd(id);
    }

    @PutMapping()
    public ResponseEntity<DogAdResponse> updateDogAd(@RequestBody @Valid DogAdRequest dogAdRequest) {
        System.out.println(dogAdRequest);
        return ResponseEntity.ok(dogAdService.updateDogAd(dogAdRequest));
    }
}
