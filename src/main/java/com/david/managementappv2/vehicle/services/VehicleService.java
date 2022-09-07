package com.david.managementappv2.vehicle.services;

import com.david.managementappv2.vehicle.models.Vehicle;
import com.david.managementappv2.vehicle.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    //Get All Vehicles
    public List<Vehicle> findAll(){
        return vehicleRepository.findAll();
    }

    //Get Vehicle By Id
    public Vehicle findById(int id) {
        return vehicleRepository.findById(id).orElse(null);
    }

    //Delete Vehicle
    public void delete(int id) {
        vehicleRepository.deleteById(id);
    }

    //Update Vehicle
    public void save(Vehicle vehicle) {
        vehicleRepository.save(vehicle);
    }

}