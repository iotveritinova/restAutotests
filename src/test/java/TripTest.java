import org.apache.commons.lang3.RandomUtils;
import org.assertj.core.api.Assertions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import tripDemo.comparator.TripComparator;
import tripDemo.comparator.TripDBComparator;
import tripDemo.model.Passenger;
import tripDemo.model.Trip;
import tripDemo.model.TripEntity;
import tripDemo.repo.TripRepository;
import tripDemo.steps.TripSteps;

import java.util.ArrayList;

public class TripTest extends BaseTest {

    private Trip trip;
    private TripEntity tripEntity;

    @BeforeMethod
    public void init() {
        trip = new Trip.Builder()
                .withRandomMainInfo(1)
                .withPassengers(new ArrayList<Passenger>() {{
                    for (int i = 0; i < RandomUtils.nextInt(1, 3); i++) {
                        add(new Passenger.Builder().withRandomCompletely().build());
                    }
                }}).build();
    }

    @BeforeMethod(onlyForGroups = {"withExistTrip"})
    public void prepareTrip() {
        trip = TripSteps.createTrip(trip);
    }

    @Test(groups = {"withAddedEntity"})
    public void createTrip() {
        Trip responseTrip = TripSteps.sendPost(trip);
        new TripComparator(responseTrip, trip).compare();
        tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        new TripDBComparator(responseTrip, tripEntity).compare();
    }

    @Test(groups = {"withExistTrip", "withAddedEntity"})
    public void getTrip() {
        Trip responseTrip = TripSteps.sendGet(trip.getId());
        new TripComparator(trip, responseTrip).compare();
        tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        new TripDBComparator(responseTrip, tripEntity).compare();
    }

    @Test(groups = {"withExistTrip", "withAddedEntity"})
    public void putTrip() {
        trip.setPlane("newPlane");

        Trip responseTrip = TripSteps.sendPut(trip);
        new TripComparator(trip, responseTrip).compare();
        tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        new TripDBComparator(responseTrip, tripEntity).compare();
    }

    @Test(groups = {"withExistTrip"})
    public void deleteTrip() {
        Trip responseTrip = TripSteps.sendDelete(trip.getId());
        new TripComparator(trip, responseTrip).compare();
        TripEntity tripEntity = TripRepository.getInstance().getById(TripEntity.class, responseTrip.getId());
        Assertions.assertThat(tripEntity).isNull();
    }

    @AfterMethod(onlyForGroups = {"withAddedEntity"})
    public void deleteEntity() {
        TripRepository.getInstance().delete(tripEntity);
    }

}