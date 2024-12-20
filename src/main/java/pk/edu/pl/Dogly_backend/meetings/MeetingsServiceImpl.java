package pk.edu.pl.Dogly_backend.meetings;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pk.edu.pl.Dogly_backend.meetings.dto.MeetingRequest;
import pk.edu.pl.Dogly_backend.meetings.dto.MeetingResponse;
import pk.edu.pl.Dogly_backend.meetings.exception.WrongRequestParamsException;
import pk.edu.pl.Dogly_backend.security.authenticatedUser.IAuthenticationFacade;
import pk.edu.pl.Dogly_backend.user.User;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class MeetingsServiceImpl implements MeetingsService {

  private final MeetingsRepository meetingsRepository;
  private final IAuthenticationFacade authenticationFacade;


  @Override
  public MeetingResponse addMeeting(MeetingRequest meetingReq) {
    Meeting meeting = new Meeting();
    LocalDateTime time = LocalDateTime.parse(meetingReq.getDate());
    meeting.setUser(authenticationFacade.getAuthentication());
    meeting.setTitle(meetingReq.getTitle());
    meeting.setId(UUID.randomUUID());
    meeting.setDescription(meetingReq.getDescription());
    meeting.setDate(time);
    meeting.setAddedAt(LocalDateTime.now());
    meeting.setDogPark(meetingReq.getDogPark());
    return new MeetingResponse(meetingsRepository.save(meeting));
  }

  @Override
  public void deleteMeeting(UUID id) {
    meetingsRepository.deleteById(id);
  }

  @Override
  public List<MeetingResponse> getMeetings(String page) {
    if (page.equals("meeting") || page.equals("admin")) {
      return meetingsListToMeetingsResponseList(
        meetingsRepository.findAllOrderByAddedAt()
      );
    } else if (page.equals("user")) {
      User user = authenticationFacade.getAuthentication();
      return meetingsListToMeetingsResponseList(
        meetingsRepository.findAllByUserOrderByAddedAt(user)
      );
    }
    throw new WrongRequestParamsException("Unknown request at GET /api/meetings!");
  }

  @Override
  public MeetingResponse action(UUID id, String action) {
    Meeting meeting = meetingsRepository.findById(id).orElseThrow();
    User user = authenticationFacade.getAuthentication();
    Set<User> clickedInterested = meeting.getInterestedUsers();
    Set<User> clickedGoing = meeting.getGoingUsers();
    boolean containsGoing = clickedGoing.stream()
      .anyMatch(u -> u.getId().equals(user.getId()));
    boolean containsInterested = clickedInterested.stream()
      .anyMatch(u -> u.getId().equals(user.getId()));

    User userToDelete = getUserToDelete(clickedGoing, clickedInterested, user);
    clickedGoing.remove(userToDelete);
    clickedInterested.remove(userToDelete);

    if (action.equals("going") && (!containsGoing || containsInterested)) {
      clickedGoing.add(user);
    } else if (action.equals("interested") && (!containsInterested || containsGoing)) {
      clickedInterested.add(user);
    }
    return new MeetingResponse(meetingsRepository.save(meeting));
  }

  @Override
  public MeetingResponse updateMeeting(MeetingRequest meetingRequest) {
    UUID id = UUID.fromString(meetingRequest.getId());
    Meeting meeting = meetingsRepository.findById(id).orElseThrow();
    meeting.setDate(LocalDateTime.parse(meetingRequest.getDate()));
    meeting.setDescription(meeting.getDescription());
    meeting.setTitle(meeting.getTitle());
    meeting.setDogPark(meetingRequest.getDogPark());
    return new MeetingResponse(meetingsRepository.save(meeting));
  }


  @Scheduled(fixedDelay = 1, timeUnit = TimeUnit.DAYS)
  public void checkIfMeetingsNeedUpdate() {
    meetingsRepository.findAllOrderByAddedAt().forEach(this::deleteExpiredMeeting);
  }

  private void deleteExpiredMeeting(Meeting meeting) {
    if (isMeetingExpired(meeting)) {
      deleteMeeting(meeting.getId());
    }
  }

  private boolean isMeetingExpired(Meeting meeting) {
    return meeting.getDate().plusHours(5L).isBefore(LocalDateTime.now());
  }

  private User getUserToDelete(Set<User> clickedGoing, Set<User> clickedInterested, User user) {
    List<User> usersToDeleteFromGoing = clickedGoing.stream().filter(u -> u.equals(user)).toList();
    List<User> usersToDeleteFromInterested = clickedInterested.stream().filter(u -> u.equals(user)).toList();
    if (!usersToDeleteFromGoing.isEmpty())
      return usersToDeleteFromGoing.get(0);
    if (!usersToDeleteFromInterested.isEmpty()) {
      return usersToDeleteFromInterested.get(0);
    }
    return null;
  }

  private List<MeetingResponse> meetingsListToMeetingsResponseList(List<Meeting> meetings) {
    return meetings.stream()
      .map(MeetingResponse::new)
      .collect(Collectors.toList());
  }
}
