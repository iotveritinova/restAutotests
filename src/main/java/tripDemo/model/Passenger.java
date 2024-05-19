package tripDemo.model;


import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.builder.CompareToBuilder;

@Entity
@Table(name = "PASSENGER")
@Data
public class Passenger implements Comparable<Passenger> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;

    @Override
    public int compareTo(Passenger o) {
        return new CompareToBuilder()
                .append(getId(), o.getId())
                .append(getFirstName(), o.getFirstName())
                .append(getMiddleName(), o.getMiddleName())
                .append(getLastName(), o.getLastName())
                .toComparison();
    }

    public static class Builder {
        private final Passenger passenger;

        public Builder() {
            passenger = new Passenger();
        }

        public Builder withRandomCompletely() {
            passenger.firstName = RandomStringUtils.random(5, true, false);
            passenger.middleName = RandomStringUtils.random(5, true, false);
            passenger.lastName = RandomStringUtils.random(5, true, false);
            return this;
        }

        public Passenger build() {
            return passenger;
        }

        public Builder withFirstName(String firstName){
            passenger.firstName=firstName;
            return this;
        }

        public Builder withLastName(String lastName){
            passenger.lastName=lastName;
            return this;
        }

        public Builder withMiddleName(String middleName){
            passenger.middleName=middleName;
            return this;
        }

        public Builder withId(Long id){
            passenger.id=id;
            return this;
        }
    }
}