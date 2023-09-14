package br.com.locatecar.controller;

import br.com.locatecar.model.Person;
import br.com.locatecar.model.Rental;
import br.com.locatecar.model.Vehicle;
import br.com.locatecar.service.api.IRentalService;
import br.com.locatecar.service.impl.PersonService;
import br.com.locatecar.service.impl.VehicleService;
import br.com.locatecar.utils.FormatterDate;
import br.com.locatecar.utils.ScannerInput;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class RentalController {

    private final VehicleController vehicleController;
    private final IRentalService rentalService;
    private final VehicleService vehicleService;
    private final PersonService personService;


    public RentalController(VehicleController vehicleController, IRentalService rentalService, VehicleService vehicleService, PersonService personService) {
        this.vehicleController = vehicleController;
        this.rentalService = rentalService;
        this.vehicleService = vehicleService;
        this.personService = personService;
    }

    public void creatRental() {
        System.out.println("Please, inform your valid document: ");
        String document = ScannerInput.getString();
        System.out.println("Please, inform your password: ");
        String password = ScannerInput.getString();

        Person client = personService.getOneByPrimaryKey(document);

        if (client != null && client.getPassword().equalsIgnoreCase(password)) {
            System.out.println("Successful login!");


            vehicleController.listAvailableVehicle();

            System.out.println("Select your vehicle option by model: ");
            String vehicleByModel = ScannerInput.getString();
            System.out.println("Which address do you want to take the car?");
            String rentalAddress = ScannerInput.getString();
            System.out.println("Which address do you want to return the car?");
            String returnAddress = ScannerInput.getString();
            System.out.println("What is the pick-up day? (dd/MM/yyyy)");
            String rentalDay = ScannerInput.getString();
            System.out.println("What is the pick-up hour? (HH)");
            String rentalHour = ScannerInput.getString();
            System.out.println("What is the return day? (dd/MM/yyyy)");
            String estimatedReturnDay = ScannerInput.getString();
            System.out.println("What is the return hour? (HH)");
            String estimatedReturnHour = ScannerInput.getString();

            Vehicle vehicle = vehicleService.getOneByName(vehicleByModel);
            LocalDateTime rentalDate = FormatterDate.execute(rentalDay, rentalHour);
            LocalDateTime estimatedReturnDate = FormatterDate.execute(estimatedReturnDay, estimatedReturnHour);
            BigDecimal amountRentalPrice = rentalService.amountPrice(client, vehicle, rentalDate, estimatedReturnDate);


            if (vehicle != null) {
                Rental rental = new Rental(vehicle, client, rentalDate, estimatedReturnDate, rentalAddress, returnAddress, amountRentalPrice);
                rentalService.add(rental);
                vehicleService.isRented(vehicle, true);
                System.out.println("Your rental was successfully created!");
            } else {
                System.out.println("Your vehicle was not found or is rented already.");
            }
        } else {
            System.out.println("Wrong document or password");
        }
    }

    public void finishRental() {
        System.out.println("Please, inform your valid document: ");
        String document = ScannerInput.getString();
        System.out.println("Please, inform your password: ");
        String password = ScannerInput.getString();

        Person client = personService.getOneByPrimaryKey(document);

        if (client != null && client.getPassword().equalsIgnoreCase(password)) {

            List<Rental> rentals = rentalService.getAll()
                    .stream()
                    .filter(rental -> rental.getClient().getDocument()
                            .equalsIgnoreCase(client.getDocument())).toList();

            if (!rentals.isEmpty()) {

                for (Rental rental : rentals) {
                    System.out.println(rental);
                }

                System.out.println("Inform the order number of your rental: ");
                int orderNumber = ScannerInput.getInt();

                Rental rental = rentalService.getOneByPrimaryKey(orderNumber);

                if (rental != null) {

                    System.out.println("What is the return day? (dd/MM/yyyy)");
                    String returnDay = ScannerInput.getString();
                    System.out.println("What is the return hour? (HH)");
                    String returnHour = ScannerInput.getString();
                    LocalDateTime returnDate = FormatterDate.execute(returnDay, returnHour);

                    rentalService.returnRental(orderNumber, returnDate);
                    vehicleService.isRented(rental.getVehicle(), false);
                    rentalService.delete(rental);
                }
            } else {
                System.out.println("You don't hava a rental occurring");
            }
        }
    }
}
