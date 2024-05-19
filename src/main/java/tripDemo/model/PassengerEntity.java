package tripDemo.model;

import jakarta.persistence.*;
import lombok.Data;

@Table(name = "Passenger")
@Entity
@Data
public class PassengerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "middle_name")
    private String middleName;
    @Column(name = "last_name")
    private String lastName;
}