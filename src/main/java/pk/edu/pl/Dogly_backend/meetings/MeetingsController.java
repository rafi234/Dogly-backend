package pk.edu.pl.Dogly_backend.meetings;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pk.edu.pl.Dogly_backend.meetings.dto.MeetingRequest;
import pk.edu.pl.Dogly_backend.meetings.dto.MeetingResponse;
import pk.edu.pl.Dogly_backend.security.annotation.IsUser;
import pk.edu.pl.Dogly_backend.security.annotation.IsUserLogged;
import pk.edu.pl.Dogly_backend.security.annotation.IsUserOrAdmin;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/meetings")
public class MeetingsController {

  private final MeetingsService meetingsService;

  @PostMapping()
  @IsUser
  public ResponseEntity<MeetingResponse> createMeetings(@RequestBody @Valid MeetingRequest meetingRequest) {
    return new ResponseEntity<>(meetingsService.addMeeting(meetingRequest), HttpStatus.CREATED);
  }

  @DeleteMapping("/{id}")
  @IsUserLogged
  public ResponseEntity<?> deleteMeeting(@PathVariable String id) {
    meetingsService.deleteMeeting(UUID.fromString(id));
    return ResponseEntity.ok().build();
  }

  @PutMapping("/{id}/action")
  @IsUser
  public ResponseEntity<?> actionMeeting(
    @PathVariable String id,
    @RequestParam String action) {
    meetingsService.action(UUID.fromString(id), action);
    return ResponseEntity.ok().build();
  }

  @PutMapping
  @IsUserOrAdmin
  public ResponseEntity<MeetingResponse> updateMeeting(@RequestBody @Valid MeetingRequest meetingRequest) {
    return ResponseEntity.ok(meetingsService.updateMeeting(meetingRequest));
  }

  @GetMapping()
  public ResponseEntity<List<MeetingResponse>> getAllMeetings(@RequestParam String page) {
    return ResponseEntity.ok(meetingsService.getMeetings(page));
  }
}
