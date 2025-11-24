package com.iotwastecollection.collection.mapper;

import com.iotwastecollection.collection.domain.dto.request.TruckRequest;
import com.iotwastecollection.collection.domain.dto.response.TruckResponse;
import com.iotwastecollection.collection.domain.entity.Truck;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TruckMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "routes", ignore = true)
    Truck toEntity(TruckRequest request);

    TruckResponse toResponse(Truck entity);

    List<TruckResponse> toResponseList(List<Truck> entities);
}
