package br.com.locatecar.repository.impl.file;

import br.com.locatecar.model.enums.VEHICLE_TYPE;
import br.com.locatecar.model.Vehicle;
import br.com.locatecar.repository.api.IVehicleRepository;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class VehicleRepositoryFile implements IVehicleRepository<Vehicle> {

    private static final String FILE_PATH = "vehicles.txt";

    public VehicleRepositoryFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void add(Vehicle vehicleToAdd) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
                writer.write(vehicleToAdd.toString());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }

    @Override
    public Vehicle update(Vehicle vehicleUpdate) {
            List<Vehicle> vehicles = readVehiclesFromFile();
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                if (vehicle.getLicensePlate().equalsIgnoreCase(vehicleUpdate.getLicensePlate())) {
                    vehicles.set(i, vehicleUpdate);
                    writeVehiclesToFile(vehicles);
                    return vehicleUpdate;
                }
            }
        return null;
    }

    @Override
    public Vehicle delete(Vehicle vehicleToDelete) {
            List<Vehicle> vehicles = readVehiclesFromFile();
            for (int i = 0; i < vehicles.size(); i++) {
                Vehicle vehicle = vehicles.get(i);
                if (vehicle.getLicensePlate().equalsIgnoreCase(vehicleToDelete.getLicensePlate())) {
                    vehicles.remove(i);
                    writeVehiclesToFile(vehicles);
                    return vehicleToDelete;
                }
            }
        return null;
    }

    @Override
    public Vehicle getOneByPrimaryKey(String document) {
        List<Vehicle> vehicles = readVehiclesFromFile();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getLicensePlate().equalsIgnoreCase(document)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public Vehicle getOneByName(String model) {
        List<Vehicle> vehicles = readVehiclesFromFile();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getModel().equalsIgnoreCase(model)) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public List<Vehicle> getAll() {
        return readVehiclesFromFile();
    }

    private void writeVehiclesToFile(List<Vehicle> vehicles) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Vehicle vehicle : vehicles) {
                writer.write(vehicle.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void isRented(Vehicle vehicleRented, boolean condition) {

    }

    private List<Vehicle> readVehiclesFromFile() {
        List<Vehicle> vehicles = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Vehicle vehicle = parseVehicleFromString(line);
                vehicles.add(vehicle);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle parseVehicleFromString(String line) {
        String content = line.substring(line.indexOf("{") + 1, line.lastIndexOf("}"));
        String[] keyValuesPairs = content.split(",");

        String brand = "";
        String model = "";
        String licensePlate = "";
        String color = "";
        Integer year = null;
        BigDecimal dailyRental = null;
        Boolean isRented = false;
        Double luggageCapacity = null;
        VEHICLE_TYPE vehicleType = null;

        for (String pairs : keyValuesPairs) {
            String[] parts = pairs.trim().split("=");
            if (parts.length == 2) {
                String key = parts[0].trim();
                String value = parts[1].trim();
                if (value.contains("'")) {
                    value = value.substring(parts[1].indexOf("'") + 1, parts[1].lastIndexOf("'"));
                }

                switch (key) {
                    case "brand" -> {
                        brand = value;
                    }
                    case "model" -> {
                        model = value;
                    }
                    case "licensePlate" -> {
                        licensePlate = value;
                    }
                    case "color" -> {
                        color = value;
                    }
                    case "year" -> {
                        year = Integer.parseInt(value);
                    }
                    case "dailyRental" -> {
                        double intermediativeRental = Double.parseDouble(value);
                        dailyRental = BigDecimal.valueOf(intermediativeRental);
                    }
                    case "isRented" -> {
                        isRented = "true".equalsIgnoreCase(value);
                    }
                    case "luggageCapacity" -> {
                        luggageCapacity = Double.parseDouble(value);
                    }
                    case "vehicleType" -> {
                        vehicleType = VEHICLE_TYPE.valueOf(value);
                    }
                }
            }
        }
        return new Vehicle(brand, model, licensePlate, color, year, dailyRental, isRented, luggageCapacity, vehicleType);
    }
}
