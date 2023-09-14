package br.com.locatecar.model;

import br.com.locatecar.model.enums.VEHICLE_TYPE;

import java.math.BigDecimal;

public class Vehicle {

    private String brand;
    private String model;
    private String licensePlate;
    private String color;
    private Integer year;
    private BigDecimal dailyRental;
    private Boolean isRented;
    private Double luggageCapacity;
    private VEHICLE_TYPE vehicleType;

    public Vehicle(String brand, String model, String licensePlate, String color, Integer year,
                   Double luggageCapacity, VEHICLE_TYPE vehicleType) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.year = year;
        this.dailyRental = vehicleType.getDailyRentalBigDecimal();
        this.isRented = false;
        this.luggageCapacity = luggageCapacity;
        this.vehicleType = vehicleType;
    }

    public Vehicle(String brand, String model, String licensePlate, String color, Integer year,
                   BigDecimal dailyRental, Boolean isRented, Double luggageCapacity, VEHICLE_TYPE vehicleType) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.color = color;
        this.year = year;
        this.dailyRental = dailyRental;
        this.isRented = isRented;
        this.luggageCapacity = luggageCapacity;
        this.vehicleType = vehicleType;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getDailyRental() {
        return dailyRental;
    }

    public boolean isRented() {
        return isRented;
    }

    public void setRented(boolean rented) {
        isRented = rented;
    }

    public Double getLuggageCapacity() {
        return luggageCapacity;
    }

    public void setLuggageCapacity(Double luggageCapacity) {
        this.luggageCapacity = luggageCapacity;
    }

    public VEHICLE_TYPE getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(VEHICLE_TYPE vehicleType) {
        this.vehicleType = vehicleType;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", color='" + color + '\'' +
                ", year=" + year +
                ", dailyRental=" + dailyRental +
                ", isRented=" + isRented +
                ", luggageCapacity=" + luggageCapacity +
                ", vehicleType=" + vehicleType +
                '}';
    }
}
