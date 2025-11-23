package com.iotwastecollection.collection.mapper;

import com.iotwastecollection.collection.domain.dto.request.DriverRequest;
import com.iotwastecollection.collection.domain.dto.response.DriverResponse;
import com.iotwastecollection.collection.domain.entity.Driver;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "schedules", ignore = true)
    @Mapping(target = "routes", ignore = true)
    Driver toEntity(DriverRequest request);

    DriverResponse toResponse(Driver entity);

    List<DriverResponse> toResponseList(List<Driver> entities);
}
