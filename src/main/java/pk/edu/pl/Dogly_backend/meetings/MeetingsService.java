package pk.edu.pl.Dogly_backend.meetings;

import pk.edu.pl.Dogly_backend.meetings.dto.MeetingRequest;
import pk.edu.pl.Dogly_backend.meetings.dto.MeetingResponse;

import java.util.List;
import java.util.UUID;

public interface MeetingsService {
  MeetingResponse addMeeting(MeetingRequest meeting);

  void deleteMeeting(UUID id);

  List<MeetingResponse> getMeetings(String page);

  MeetingResponse action(UUID id, String action);

  MeetingResponse updateMeeting(MeetingRequest meetingRequest);
}


