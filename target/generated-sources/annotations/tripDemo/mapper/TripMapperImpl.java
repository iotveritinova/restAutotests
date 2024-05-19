package tripDemo.mapper;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import tripDemo.model.CompanyEntity;
import tripDemo.model.Passenger;
import tripDemo.model.PassengerEntity;
import tripDemo.model.Trip;
import tripDemo.model.TripEntity;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-20T00:06:51+0400",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 11.0.13 (Oracle Corporation)"
)
public class TripMapperImpl implements TripMapper {

    @Override
    public Trip toDto(TripEntity tripEntity) {
        if ( tripEntity == null ) {
            return null;
        }

        Trip trip = new Trip();

        trip.setCompanyId( tripEntityCompanyId( tripEntity ) );
        trip.setId( tripEntity.getId() );
        trip.setPlane( tripEntity.getPlane() );
        trip.setTownFrom( tripEntity.getTownFrom() );
        trip.setTownTo( tripEntity.getTownTo() );
        trip.setTimeOut( tripEntity.getTimeOut() );
        trip.setTimeIn( tripEntity.getTimeIn() );
        if ( trip.getPassengerList() != null ) {
            List<Passenger> list = passengerEntityListToPassengerList( tripEntity.getPassengerList() );
            if ( list != null ) {
                trip.getPassengerList().addAll( list );
            }
        }

        return trip;
    }

    @Override
    public TripEntity toEntity(Trip trip) {
        if ( trip == null ) {
            return null;
        }

        TripEntity tripEntity = new TripEntity();

        tripEntity.setCompany( tripToCompanyEntity( trip ) );
        if ( trip.getId() != null ) {
            tripEntity.setId( trip.getId() );
        }
        tripEntity.setPlane( trip.getPlane() );
        tripEntity.setTownFrom( trip.getTownFrom() );
        tripEntity.setTownTo( trip.getTownTo() );
        tripEntity.setTimeOut( trip.getTimeOut() );
        tripEntity.setTimeIn( trip.getTimeIn() );
        tripEntity.setPassengerList( passengerListToPassengerEntityList( trip.getPassengerList() ) );

        return tripEntity;
    }

    private Long tripEntityCompanyId(TripEntity tripEntity) {
        if ( tripEntity == null ) {
            return null;
        }
        CompanyEntity company = tripEntity.getCompany();
        if ( company == null ) {
            return null;
        }
        long id = company.getId();
        return id;
    }

    protected Passenger passengerEntityToPassenger(PassengerEntity passengerEntity) {
        if ( passengerEntity == null ) {
            return null;
        }

        Passenger passenger = new Passenger();

        passenger.setId( passengerEntity.getId() );
        passenger.setFirstName( passengerEntity.getFirstName() );
        passenger.setMiddleName( passengerEntity.getMiddleName() );
        passenger.setLastName( passengerEntity.getLastName() );

        return passenger;
    }

    protected List<Passenger> passengerEntityListToPassengerList(List<PassengerEntity> list) {
        if ( list == null ) {
            return null;
        }

        List<Passenger> list1 = new ArrayList<Passenger>( list.size() );
        for ( PassengerEntity passengerEntity : list ) {
            list1.add( passengerEntityToPassenger( passengerEntity ) );
        }

        return list1;
    }

    protected CompanyEntity tripToCompanyEntity(Trip trip) {
        if ( trip == null ) {
            return null;
        }

        CompanyEntity companyEntity = new CompanyEntity();

        if ( trip.getCompanyId() != null ) {
            companyEntity.setId( trip.getCompanyId() );
        }

        return companyEntity;
    }

    protected PassengerEntity passengerToPassengerEntity(Passenger passenger) {
        if ( passenger == null ) {
            return null;
        }

        PassengerEntity passengerEntity = new PassengerEntity();

        if ( passenger.getId() != null ) {
            passengerEntity.setId( passenger.getId() );
        }
        passengerEntity.setFirstName( passenger.getFirstName() );
        passengerEntity.setMiddleName( passenger.getMiddleName() );
        passengerEntity.setLastName( passenger.getLastName() );

        return passengerEntity;
    }

    protected List<PassengerEntity> passengerListToPassengerEntityList(List<Passenger> list) {
        if ( list == null ) {
            return null;
        }

        List<PassengerEntity> list1 = new ArrayList<PassengerEntity>( list.size() );
        for ( Passenger passenger : list ) {
            list1.add( passengerToPassengerEntity( passenger ) );
        }

        return list1;
    }
}
