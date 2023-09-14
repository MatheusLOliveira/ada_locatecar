package br.com.locatecar.service.api;

import br.com.locatecar.model.Vehicle;

import java.util.List;

public interface IVehicleService extends IService<Vehicle> {


    Vehicle getOneByDocument(String document);
    Vehicle getOneByName(String name);
    public List<Vehicle> getAllByName(String name);
    public void isRented(Vehicle vehicleToRent, boolean condition);

}
