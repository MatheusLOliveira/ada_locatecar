package br.com.locatecar;

import br.com.locatecar.controller.ClientController;
import br.com.locatecar.controller.MenuController;
import br.com.locatecar.controller.RentalController;
import br.com.locatecar.controller.VehicleController;
import br.com.locatecar.model.*;
import br.com.locatecar.repository.api.IPersonRepository;
import br.com.locatecar.repository.api.IRentalRepository;
import br.com.locatecar.repository.api.IVehicleRepository;
import br.com.locatecar.repository.impl.list.PersonRepositoryList;
import br.com.locatecar.repository.impl.list.RentalRepositoryList;
import br.com.locatecar.repository.impl.list.VehicleRepositoryList;
import br.com.locatecar.service.impl.*;

public class Main {
    public static void main(String[] args) {
        IPersonRepository<Person> personRepository = new PersonRepositoryList();
        IVehicleRepository<Vehicle> vehicleRepository = new VehicleRepositoryList();
        IRentalRepository<Rental> rentalRepository = new RentalRepositoryList();


        PersonService personService = new PersonService(personRepository);
        VehicleService vehicleService = new VehicleService(vehicleRepository);
        RentalService rentalService = new RentalService(rentalRepository);

        ClientController clientController = new ClientController(personService);
        VehicleController vehicleController = new VehicleController(vehicleService);
        RentalController rentalController = new RentalController(vehicleController, rentalService, vehicleService, personService);

        MenuController menu = new MenuController();
        menu.menuOperation(clientController, vehicleController, rentalController);

    }
}