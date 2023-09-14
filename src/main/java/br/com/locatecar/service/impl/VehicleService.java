package br.com.locatecar.service.impl;

import br.com.locatecar.exceptions.DuplicateException;
import br.com.locatecar.model.Vehicle;
import br.com.locatecar.repository.api.IVehicleRepository;
import br.com.locatecar.service.api.IVehicleService;

import java.util.ArrayList;
import java.util.List;

public class VehicleService implements IVehicleService {

    private final IVehicleRepository<Vehicle> vehicleRepository;

    public VehicleService(IVehicleRepository<Vehicle> vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public void add(Vehicle vehicleToAdd) {
        if (vehicleRepository.getOneByPrimaryKey(vehicleToAdd.getLicensePlate()) == null) {
            vehicleRepository.add(vehicleToAdd);
        } else {
            throw new DuplicateException("Vehicle with the same License Plate already exists.");
        }
    }

    @Override
    public Vehicle update(Vehicle vehicleToUpdate) {
        Vehicle vehicle = vehicleRepository.getOneByPrimaryKey(vehicleToUpdate.getLicensePlate());

        if (vehicle != null) {
            return vehicleRepository.update(vehicleToUpdate);
        }
        return null;
    }

    @Override
    public Vehicle delete(Vehicle vehicleToDelete) {
        Vehicle vehicle = vehicleRepository.getOneByPrimaryKey(vehicleToDelete.getLicensePlate());

        if (vehicle != null) {
            return vehicleRepository.delete(vehicleToDelete);
        }
        return null;
    }

    @Override
    public Vehicle getOneByDocument(String licensePlate) {
        return vehicleRepository.getOneByPrimaryKey(licensePlate);
    }

    @Override
    public Vehicle getOneByName(String name) {
        return vehicleRepository.getOneByName(name);
    }

    @Override
    public List<Vehicle> getAllByName(String name) {
        List<Vehicle> matchingVehicles = new ArrayList<>();

        List<Vehicle> allVehicles = vehicleRepository.getAll();
        for (Vehicle vehicle : allVehicles) {
            if (vehicle.getModel().equalsIgnoreCase(name)) {
                matchingVehicles.add(vehicle);
            }
        }
        return matchingVehicles;
    }

    @Override
    public List<Vehicle> getAll() {
        return vehicleRepository.getAll();
    }

    @Override
    public void isRented(Vehicle vehicleToRent, boolean condition) {
        Vehicle vehicle = vehicleRepository.getOneByPrimaryKey(vehicleToRent.getLicensePlate());

        if (vehicle != null) {
            vehicleRepository.isRented(vehicleToRent, condition);
        }
    }
}
