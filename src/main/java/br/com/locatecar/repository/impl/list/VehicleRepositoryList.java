package br.com.locatecar.repository.impl.list;

import br.com.locatecar.model.Vehicle;
import br.com.locatecar.repository.api.IVehicleRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VehicleRepositoryList implements IVehicleRepository<Vehicle> {

    List<Vehicle> vehicles;

    public VehicleRepositoryList() {
        this.vehicles = new ArrayList<>();
    }

    @Override
    public void add(Vehicle vehicleToAdd) {
        this.vehicles.add(vehicleToAdd);
    }

    @Override
    public Vehicle update(Vehicle updatedVehicle) {
        for (int i = 0; i < this.vehicles.size(); i++) {
            Vehicle vehicle = this.vehicles.get(i);
            if (vehicle.getLicensePlate().equalsIgnoreCase(updatedVehicle.getLicensePlate())) {
                this.vehicles.set(i, updatedVehicle);
                return updatedVehicle;
            }
        }
        return null;
    }

    @Override
    public Vehicle delete(Vehicle vehicleToDelete) {
        for (int i = 0; i < this.vehicles.size(); i++) {
            Vehicle vehicle = this.vehicles.get(i);
            if (vehicle.getLicensePlate().equalsIgnoreCase(vehicleToDelete.getLicensePlate())) {
                vehicles.remove(vehicle);
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public Vehicle getOneByPrimaryKey(String requestedLicensePlate) {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(requestedLicensePlate)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public Vehicle getOneByName(String model) {
        for (Vehicle vehicle : this.vehicles) {
            if (vehicle.getModel().equalsIgnoreCase(model)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        return Collections.unmodifiableList(vehicles);
    }

    @Override
    public void isRented(Vehicle vehicleRented, boolean condition) {
            vehicleRented.setRented(condition);
    }
}
