package pk.edu.pl.Dogly_backend.meetings;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pk.edu.pl.Dogly_backend.user.User;

import java.util.List;
import java.util.UUID;

@Repository
public interface MeetingsRepository extends JpaRepository<Meeting, UUID> {

  @Query(
    value = "SELECT * from Meeting order by added_at",
    nativeQuery = true
  )
  List<Meeting> findAllOrderByAddedAt();

  List<Meeting> findAllByUserOrderByAddedAt(User user);
}
