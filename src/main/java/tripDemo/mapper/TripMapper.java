package tripDemo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import tripDemo.model.Trip;
import tripDemo.model.TripEntity;

@Mapper(uses = TripMapper.class)
public interface TripMapper {
    TripMapper INSTANCE = Mappers.getMapper(TripMapper.class);

    @Mapping(target = "companyId", source = "company.id")
    Trip toDto(TripEntity tripEntity);
    @Mapping(target = "company.id", source = "companyId")
    TripEntity toEntity(Trip trip);
}
