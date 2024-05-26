package tripDemo.comparator;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import tripDemo.mapper.TripMapper;
import tripDemo.model.Trip;
import tripDemo.model.TripEntity;

import java.util.Collections;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class TripDBComparator implements IComparator {

    private final Trip expected, actual;
    private final TripEntity tripEntity;

    public TripDBComparator(Trip expected, TripEntity tripEntity) {
        this.expected = expected;
        this.tripEntity = tripEntity;
        actual = TripMapper.INSTANCE.toDto(tripEntity);
        Collections.sort(actual.getPassengerList());
    }


    @Override
    @Step("Проверка информации в базе данный")
    public void compare() {
        assertThat(actual).usingRecursiveComparison()
                .isEqualTo(expected);
        checkSpecificFields();
    }

    private void checkSpecificFields() {
        Assertions.assertThat(tripEntity.getCompany()).isNotNull();
    }
}