package com.iotwastecollection.collection.repository;

import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.enums.DriverStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.List;

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    Optional<Driver> findByEmail(String email);
    Optional<Driver> findByNumeroPermis(String numeroPermis);
    List<Driver> findByStatus(DriverStatus status);
    boolean existsByEmail(String email);
    boolean existsByNumeroPermis(String numeroPermis);
}
