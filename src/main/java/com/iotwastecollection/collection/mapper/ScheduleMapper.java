package com.iotwastecollection.collection.mapper;

import com.iotwastecollection.collection.domain.dto.request.ScheduleRequest;
import com.iotwastecollection.collection.domain.dto.response.ScheduleResponse;
import com.iotwastecollection.collection.domain.entity.CollectionRoute;
import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.entity.Schedule;
import com.iotwastecollection.collection.domain.entity.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ScheduleMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "driver", source = "driverId", qualifiedByName = "driverIdToDriver")
    @Mapping(target = "truck", source = "truckId", qualifiedByName = "truckIdToTruck")
    @Mapping(target = "route", source = "routeId", qualifiedByName = "routeIdToRoute")
    Schedule toEntity(ScheduleRequest request);

    @Mapping(target = "driverId", source = "driver.id")
    @Mapping(target = "truckId", source = "truck.id")
    @Mapping(target = "routeId", source = "route.id")
    ScheduleResponse toResponse(Schedule entity);

    List<ScheduleResponse> toResponseList(List<Schedule> entities);

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

    @Named("routeIdToRoute")
    default CollectionRoute routeIdToRoute(Long routeId) {
        if (routeId == null) {
            return null;
        }
        CollectionRoute route = new CollectionRoute();
        route.setId(routeId);
        return route;
    }
}
