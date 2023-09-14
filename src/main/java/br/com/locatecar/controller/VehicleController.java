package br.com.locatecar.controller;

import br.com.locatecar.model.Vehicle;
import br.com.locatecar.model.enums.VEHICLE_TYPE;
import br.com.locatecar.service.api.IVehicleService;
import br.com.locatecar.utils.ScannerInput;

import java.util.List;

public class VehicleController {

    private final IVehicleService vehicleService;

    public VehicleController(IVehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    public void registerVehicle() {
        System.out.println("Inform the vehicle brand:");
        String brand = ScannerInput.getString();
        System.out.println("Inform the vehicle model:");
        String model = ScannerInput.getString();
        System.out.println("Inform the vehicle license plate:");
        String licensePlate = ScannerInput.getString();
        System.out.println("Inform the vehicle color:");
        String color = ScannerInput.getString();
        System.out.println("Inform the vehicle year:");
        int year = ScannerInput.getInt();
        System.out.println("Inform the vehicle luggage capacity:");
        double luggageCapacity = ScannerInput.getDouble();

        VEHICLE_TYPE vehicleType = null;
        while (vehicleType == null) {
            System.out.println("Choose vehicle category: (1 - Short | 2 - Medium | 3 - SUV)");
            int categoryVehicle = ScannerInput.getInt();

            switch (categoryVehicle) {
                case 1 -> vehicleType = VEHICLE_TYPE.SHORT;
                case 2 -> vehicleType = VEHICLE_TYPE.MEDIUM;
                case 3 -> vehicleType = VEHICLE_TYPE.SUV;
                default -> System.out.println("Choose a valid vehicle type.");
            }
        }

        Vehicle vehicle = new Vehicle(brand, model, licensePlate, color, year, luggageCapacity, vehicleType);

        if (vehicleService.getOneByDocument(vehicle.getLicensePlate()) == null) {
            vehicleService.add(vehicle);
        } else {
            System.out.println("A vehicle with this license plate already exist");
        }
    }

    public void listAllVehicle() {
        List<Vehicle> vehicles = vehicleService.getAll();

        if (!vehicles.isEmpty()) {
            System.out.println("All vehicles from the locate car: ");
            for (Vehicle vehicle : vehicles) {
                System.out.println("- " + vehicle);
            }
        } else {
            System.out.println("There are no vehicle registered!");
        }
    }

    public void listAvailableVehicle() {
        List<Vehicle> vehiclesAvailable = vehicleService.getAll()
                .stream()
                .filter(vehicle -> !vehicle.isRented())
                .toList();

        if (!vehiclesAvailable.isEmpty()) {
            System.out.println("Available vehicles:");
            for (Vehicle vehicle : vehiclesAvailable) {
                System.out.println("- " + vehicle);
            }
        } else {
            System.out.println("There are no available vehicles");
        }
    }

    public void updateVehicleInfos() {
        List<Vehicle> vehicles = vehicleService.getAll();

        if (!vehicles.isEmpty()) {

            int optionVehicle;
            do {
                for (int i = 0; i < vehicles.size(); i++) {
                    System.out.println(i + " - " + vehicles.get(i));
                }

                System.out.println("Which vehicle do you want to update? Choose by number list.");
                optionVehicle = ScannerInput.getInt();

                if (!(optionVehicle >= 0 && optionVehicle < vehicles.size())) {
                    System.out.println("Choice out of bounds!");
                }
            } while (!(optionVehicle >= 0 && optionVehicle < vehicles.size()));

            Vehicle vehicle = vehicles.get(optionVehicle);

            if (vehicle != null) {
                System.out.println("""
                What do you want to update?
                1 - Brand
                2 - Model
                3 - License Plate
                4 - Color
                5 - Year
                6 - Luggage Capacity
                7 - Vehicle Type""");
                int option = ScannerInput.getInt();

                boolean condition = false;
                switch (option) {
                    case 1 -> {
                        System.out.println("New Brand: ");
                        String brand = ScannerInput.getString();
                        vehicle.setBrand(brand);
                        condition = true;
                    }
                    case 2 -> {
                        System.out.println("New Model: ");
                        String model = ScannerInput.getString();
                        vehicle.setModel(model);
                        condition = true;
                    }
                    case 3 -> {
                        System.out.println("New License Plate: ");
                        String licensePlate = ScannerInput.getString();
                        vehicle.setLicensePlate(licensePlate);
                        condition = true;
                    }
                    case 4 -> {
                        System.out.println("New Color: ");
                        String color = ScannerInput.getString();
                        vehicle.setColor(color);
                        condition = true;
                    }
                    case 5 -> {
                        System.out.println("New Year: ");
                        Integer year = ScannerInput.getInt();
                        vehicle.setYear(year);
                        condition = true;
                    }
                    case 6 -> {
                        System.out.println("New Luggage Capacity: ");
                        Double luggageCapacitity = ScannerInput.getDouble();
                        vehicle.setLuggageCapacity(luggageCapacitity);
                        condition = true;
                    }
                    case 7 -> {
                        int categoryVehicle;
                        do {
                            System.out.println("Enter the updated vehicle category: (1 - Short | 2 - Medium | 3 - SUV)");
                            categoryVehicle = ScannerInput.getInt();
                            switch (categoryVehicle) {
                                case 1 -> vehicle.setVehicleType(VEHICLE_TYPE.SHORT);
                                case 2 -> vehicle.setVehicleType(VEHICLE_TYPE.MEDIUM);
                                case 3 -> vehicle.setVehicleType(VEHICLE_TYPE.SUV);
                                default -> System.out.println("Please, select a valid option!");
                            }
                        } while (!(categoryVehicle > 0 && categoryVehicle <= VEHICLE_TYPE.values().length));
                        condition = true;
                    }
                    default -> System.out.println("Invalid input!");
                }
                if (condition) {
                    System.out.println("Vehicle updated!");
                    System.out.println(vehicle);
                }

            } else {
                System.out.println("Vehicle not found!");
            }
        } else {
            System.out.println("There are no Vehicle registered!");
        }
    }

    public void getVehicleByLicensePlate() {
        System.out.println("What's the license plate do you want to search for?");
        String licensePlate = ScannerInput.getString();

        Vehicle vehicle = vehicleService.getOneByDocument(licensePlate);

        if (vehicle != null) {
            System.out.println("The vehicle is on our data base: ");
            System.out.println(vehicle);
        } else {
            System.out.println("Your vehicle was not found!");
        }
    }

    public void getVehicleByModel() {
        System.out.println("What's the model of the car do you want to search for?");
        String modelCar = ScannerInput.getString();

        List<Vehicle> matchingVehicles = vehicleService.getAllByName(modelCar);

        if (matchingVehicles.size() >= 2) {
            System.out.println("Vehicles matching the model '" + modelCar + "' are:");

            int choice;
            do {
                for (int i = 0; i < matchingVehicles.size(); i++) {
                    System.out.println((i + 1) + ". " + matchingVehicles.get(i).getModel() + " - " + matchingVehicles.get(i));
                }

                System.out.println("Enter number of the vehicle you want to select:");
                choice = ScannerInput.getInt();
                if (!(choice >= 1 && choice <= matchingVehicles.size())) {
                    System.out.println("Invalid choice. Please select a valid vehicle.");
                }
            } while (!(choice >= 1 && choice <= matchingVehicles.size()));

            Vehicle selectedVehicle = matchingVehicles.get(choice - 1);
            System.out.println("You selected the following vehicle:");
            System.out.println(selectedVehicle);

        } else {
            System.out.println("No vehicles matching the model '" + modelCar + "' were found!");
        }
    }

    public void removeVehicle() {
        listAllVehicle();

        System.out.println();
        System.out.println("Which vehicle do you want to remove? (Choose by license plate)");
        String licensePlate = ScannerInput.getString();

        Vehicle vehicle = vehicleService.getOneByDocument(licensePlate);

        if (vehicle != null) {
            Vehicle vehicleDeleted = vehicleService.delete(vehicle);
            System.out.println("Vehicle " + vehicleDeleted.getLicensePlate() + " successfully deleted!");
        } else {
            System.out.println("vehicle not found");
        }

    }
}
