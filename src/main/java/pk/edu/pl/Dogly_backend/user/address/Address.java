package pk.edu.pl.Dogly_backend.user.address;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class Address implements Serializable {

    @Id
    private UUID id;
    private String country;
    private String voivodeship;
    private String city;
    private String street;
    @Column(name = "postal_code")
    private String postalCode;

    @Override
    public String toString() {
        return "Country:  " + country +
                "\nVoivodeship: " + voivodeship +
                "\nCity " + city + ", " + postalCode +
                "\nStreet: " + street;
    }
}
