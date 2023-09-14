package br.com.locatecar.controller;

import br.com.locatecar.utils.ScannerInput;

public class MenuController {

    public void menu(){
        System.out.println();
        System.out.println("Main Menu:");
        System.out.println("____________________________________");
        System.out.println(
                """
                        0) Finish Program
                        1) Create Client
                        2) Register Vehicle
                        3) Rent a Vehicle
                        4) Return a Vehicle
                        5) List All Vehicles
                        6) List Available Vehicles
                        7) List All Users
                        8) Update Infos About an User
                        9) Update Infos About a Vehicle
                        10) Find Vehicle by License Plate
                        11) List All Company Entity Clients
                        12) List All Personal Entity Clients
                        13) Find Vehicle by Model
                        14) Delete Vehicle by License Plate
                        15) Delete Users by Document""");
        System.out.println("____________________________________");
    }

    public void menuOperation(ClientController clientController, VehicleController vehicleController, RentalController rentalController) {
        int option;
        do {
            menu();
            System.out.println("Choose your option: ");
            option = ScannerInput.getInt();

            switch (option) {
                case 1 -> clientController.createClient();
                case 2 -> vehicleController.registerVehicle();
                case 3 -> rentalController.creatRental();
                case 4 -> rentalController.finishRental();
                case 5 -> vehicleController.listAllVehicle();
                case 6 -> vehicleController.listAvailableVehicle();
                case 7 -> clientController.listAllUsers();
                case 8 -> clientController.updateClientInfos();
                case 9 -> vehicleController.updateVehicleInfos();
                case 10 -> vehicleController.getVehicleByLicensePlate();
                case 11 -> clientController.listAllCompanyEntitiesClients();
                case 12 -> clientController.listAllPersonalEntitiesClients();
                case 13 -> vehicleController.getVehicleByModel();
                case 14 -> vehicleController.removeVehicle();
                case 15 -> clientController.removeUser();
            }
        } while (option != 0);

    }
}
