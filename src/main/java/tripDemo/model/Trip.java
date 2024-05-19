package tripDemo.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "TRIP")
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "company_id")
    private Long companyId;
    @Column(name = "plane")
    private String plane;
    @Column(name = "town_from")
    private String townFrom;
    @Column(name = "town_to")
    private String townTo;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "time_out")
    private LocalDateTime timeOut;
    @JsonSerialize(using= LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @Column(name = "time_in")
    private LocalDateTime timeIn;
    //тут неверно тянется, разобраться что делать с таблицей trip_passenger
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "TRIP_PASSENGER",
            joinColumns = {@JoinColumn(name = "trip_id")},
            inverseJoinColumns = {@JoinColumn(name = "passenger_id")}
    )
    private final List<Passenger> passengerList = new ArrayList<>();

    public static class Builder {
        private final Trip trip;

        public Builder() {
            trip = new Trip();
        }

        public Builder withPassengers(List<Passenger> passengerList) {
            trip.passengerList.addAll(passengerList);
            Collections.sort(trip.passengerList);
            return this;
        }

        public Builder withPlane(String plane) {
            trip.plane = plane;
            return this;
        }

        public Builder withTownTo(String townTo) {
            trip.townTo = townTo;
            return this;
        }

        public Builder withRandomMainInfo(long companyId) {
            trip.companyId = companyId;
            trip.plane = RandomStringUtils.random(5, true, false);
            trip.townFrom = RandomStringUtils.random(10, true, false);
            trip.townTo = RandomStringUtils.random(10, true, false);
            trip.timeOut = LocalDateTime.now().plusDays(5).truncatedTo(ChronoUnit.SECONDS);
            trip.timeIn = LocalDateTime.now().plusDays(5).plusHours(5).truncatedTo(ChronoUnit.SECONDS);
            return this;
        }

        public Trip build() {
            return trip;
        }

        public Builder withId(long id) {
            trip.id = id;
            return this;
        }
    }
}