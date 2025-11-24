package com.iotwastecollection.collection.mapper;

import com.iotwastecollection.collection.domain.dto.request.CollectionRouteRequest;
import com.iotwastecollection.collection.domain.dto.response.CollectionRouteResponse;
import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.entity.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CollectionRouteMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "driverIdToDriver")
    @Mapping(target = "truck", source = "truckId", qualifiedByName = "truckIdToTruck")
    @Mapping(target = "schedules", ignore = true)
    CollectionRoute toEntity(CollectionRouteRequest request);

    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "truckId", source = "truck.id")
    CollectionRouteResponse toResponse(CollectionRoute entity);

    List<CollectionRouteResponse> toResponseList(List<CollectionRoute> entities);

    @Named("driverIdToDriver")
    default Driver driverIdToDriver(Long driverId) {
        if (driverId == null) {
            return null;
        }
        Driver driver = new Driver();
        driver.setId(driverId);
        return driver;
    }

    @Named("truckIdToTruck")
    default Truck truckIdToTruck(Long truckId) {
        if (truckId == null) {
            return null;
        }
        Truck truck = new Truck();
        truck.setId(truckId);
        return truck;
    }
}
