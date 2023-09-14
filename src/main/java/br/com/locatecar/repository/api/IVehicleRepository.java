package br.com.locatecar.repository.api;

import br.com.locatecar.model.Vehicle;

public interface IVehicleRepository<T> extends Repository<T> {

    T getOneByName(String name);
    T getOneByPrimaryKey(String licensePlate);
    void isRented(Vehicle vehicleRented, boolean condition);

}
