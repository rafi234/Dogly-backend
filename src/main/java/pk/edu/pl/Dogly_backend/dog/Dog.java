package pk.edu.pl.Dogly_backend.dog;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Dog implements Serializable {

  @Id
  private UUID id;

  @NotBlank
  private String name;

  private String breed;

  @Temporal(TemporalType.DATE)
  private Date dogsBirth;
}
