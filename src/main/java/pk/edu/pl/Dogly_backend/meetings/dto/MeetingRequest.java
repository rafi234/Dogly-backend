package pk.edu.pl.Dogly_backend.meetings.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pk.edu.pl.Dogly_backend.meetings.scrapler.DogPark;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public final class MeetingRequest {

  private String id;

  @NotBlank(message = "You must enter a title!")
  private String title;

  private String description;

  @NotBlank(message = "Date must be entered!")
  private String date;

  @NotNull(message = "You must chose a place of meeting!")
  private DogPark dogPark;
}
