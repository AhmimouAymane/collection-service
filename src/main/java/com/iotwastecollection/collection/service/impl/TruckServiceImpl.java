package com.iotwastecollection.collection.service.impl;

import com.iotwastecollection.collection.domain.entity.Truck;
import com.iotwastecollection.collection.domain.enums.TruckStatus;
import com.iotwastecollection.collection.repository.TruckRepository;
import com.iotwastecollection.collection.service.inter.ITruckService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TruckServiceImpl implements ITruckService {
    private final TruckRepository truckRepository;

    @Autowired
    public TruckServiceImpl(TruckRepository truckRepository) {
        this.truckRepository = truckRepository;
    }

    @Override
    public Truck createTruck(Truck truck) {
        if (truckRepository.existsByImmatriculation(truck.getImmatriculation())) {
            throw new DataIntegrityViolationException("Immatriculation déjà utilisée: " + truck.getImmatriculation());
        }
        return truckRepository.save(truck);
    }

    @Override
    @Transactional(readOnly = true)
    public Truck getTruckById(Long id) {
        return truckRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Truck not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Truck> getAllTrucks() {
        return truckRepository.findAll();
    }

    @Override
    public Truck updateTruck(Truck truck) {
        Truck existing = truckRepository.findById(truck.getId())
                .orElseThrow(() -> new EntityNotFoundException("Truck not found: " + truck.getId()));

        // Vérifier l'unicité de l'immatriculation si modifiée
        if (!existing.getImmatriculation().equals(truck.getImmatriculation()) 
                && truckRepository.existsByImmatriculation(truck.getImmatriculation())) {
            throw new DataIntegrityViolationException("Immatriculation déjà utilisée: " + truck.getImmatriculation());
        }

        existing.setImmatriculation(truck.getImmatriculation());
        existing.setMarque(truck.getMarque());
        existing.setModele(truck.getModele());
        existing.setCapaciteMaximale(truck.getCapaciteMaximale());
        existing.setDateAchat(truck.getDateAchat());
        existing.setDateDernierEntretien(truck.getDateDernierEntretien());
        existing.setStatus(truck.getStatus());

        return truckRepository.save(existing);
    }

    @Override
    public void deleteTruck(Long id) {
        if (truckRepository.existsById(id)) {
            truckRepository.deleteById(id);
        } else {
            throw new EntityNotFoundException("Truck not found: " + id);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Truck findByImmatriculation(String immatriculation) {
        return truckRepository.findByImmatriculation(immatriculation)
                .orElseThrow(() -> new EntityNotFoundException("Truck not found: " + immatriculation));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Truck> findByStatus(TruckStatus status) {
        return truckRepository.findByStatus(status);
    }
}
