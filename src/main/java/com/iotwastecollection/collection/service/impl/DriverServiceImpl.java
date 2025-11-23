package com.iotwastecollection.collection.service.impl;

import com.iotwastecollection.collection.domain.entity.Driver;
import com.iotwastecollection.collection.domain.enums.DriverStatus;
import com.iotwastecollection.collection.repository.DriverRepository;
import com.iotwastecollection.collection.service.inter.IDriverService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements IDriverService {
    private final DriverRepository driverRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }

    @Override
    public Driver createDriver(Driver driver) {
        // Validate required fields
        if (driver.getEmail() == null || driver.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Email is required");
        }
        if (driver.getNumeroPermis() == null || driver.getNumeroPermis().trim().isEmpty()) {
            throw new IllegalArgumentException("Numéro de permis is required");
        }
        
        // Check for duplicates
        if (driverRepository.existsByEmail(driver.getEmail())) {
            throw new DataIntegrityViolationException("Email déjà utilisé: " + driver.getEmail());
        }
        if (driverRepository.existsByNumeroPermis(driver.getNumeroPermis())) {
            throw new DataIntegrityViolationException("Numéro de permis déjà utilisé: " + driver.getNumeroPermis());
        }
        
        // Ensure relationships are initialized
        if (driver.getSchedules() == null) {
            driver.setSchedules(new ArrayList<>());
        }
        if (driver.getRoutes() == null) {
            driver.setRoutes(new ArrayList<>());
        }
        
        return driverRepository.save(driver);
    }

    @Override
    @Transactional(readOnly = true)
    public Driver getDriverById(Long id) {
        return driverRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> getAllDrivers() {
        return driverRepository.findAll();
    }

    @Override
    public Driver updateDriver(Driver driver) {
        Driver existing = driverRepository.findById(driver.getId())
                .orElseThrow(() -> new EntityNotFoundException("Driver not found: " + driver.getId()));

        // Vérifier l'unicité de l'email si modifié
        if (!existing.getEmail().equals(driver.getEmail()) && driverRepository.existsByEmail(driver.getEmail())) {
            throw new DataIntegrityViolationException("Email déjà utilisé: " + driver.getEmail());
        }

        // Vérifier l'unicité du numéro de permis si modifié
        if (!existing.getNumeroPermis().equals(driver.getNumeroPermis()) 
                && driverRepository.existsByNumeroPermis(driver.getNumeroPermis())) {
            throw new DataIntegrityViolationException("Numéro de permis déjà utilisé: " + driver.getNumeroPermis());
        }

        existing.setNom(driver.getNom());
        existing.setPrenom(driver.getPrenom());
        existing.setEmail(driver.getEmail());
        existing.setTelephone(driver.getTelephone());
        existing.setNumeroPermis(driver.getNumeroPermis());
        existing.setDateEmbauche(driver.getDateEmbauche());
        existing.setStatus(driver.getStatus());

        return driverRepository.save(existing);
    }

    @Override
    public void deleteDriver(Long id) {
        if (driverRepository.existsById(id)) {
            driverRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Driver not found: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Driver findByEmail(String email) {
        return driverRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("Driver not found: " + email));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Driver> findByStatus(DriverStatus status) {
        return driverRepository.findByStatus(status);
    }
}
