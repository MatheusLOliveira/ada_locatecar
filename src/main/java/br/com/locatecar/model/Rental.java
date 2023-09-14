package br.com.locatecar.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Rental {

    private static final LocalDateTime DATE_NOW = LocalDateTime.now();

    private static Integer lastOrderNumber = 0;
    private Integer orderNumber;
    private Vehicle vehicle;
    private Person person;
    private LocalDateTime rentalDate;
    private LocalDateTime returnDate;
    private LocalDateTime estimatedReturnDate;
    private String rentalAddress;
    private String returnAddress;
    private BigDecimal amountRentalPrice;

    public Rental(Vehicle vehicle, Person person, LocalDateTime rentalDate,
                  LocalDateTime estimatedReturnDate, String rentalAddress, String returnAddress, BigDecimal amountRentalPrice) {
        this.orderNumber = lastOrderNumber++;
        this.vehicle = vehicle;
        this.person = person;
        this.rentalDate = rentalDate;
        this.estimatedReturnDate = estimatedReturnDate;
        this.rentalAddress = rentalAddress;
        this.returnAddress = returnAddress;
        this.amountRentalPrice = amountRentalPrice;
    }

    public static Integer getLastOrderNumber() {
        return lastOrderNumber;
    }

    public static void setLastOrderNumber(Integer lastOrderNumber) {
        Rental.lastOrderNumber = lastOrderNumber;
    }

    public Integer getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Person getClient() {
        return person;
    }

    public void setClient(Person person) {
        this.person = person;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDateTime getEstimatedReturnDate() {
        return estimatedReturnDate;
    }

    public void setEstimatedReturnDate(LocalDateTime estimatedReturnDate) {
        this.estimatedReturnDate = estimatedReturnDate;
    }

    public String getRentalAddress() {
        return rentalAddress;
    }

    public void setRentalAddress(String rentalAddress) {
        this.rentalAddress = rentalAddress;
    }

    public String getReturnAddress() {
        return returnAddress;
    }

    public void setReturnAddress(String returnAddress) {
        this.returnAddress = returnAddress;
    }

    public BigDecimal getAmountRentalPrice() {
        return amountRentalPrice;
    }

    public void setAmountRentalPrice(BigDecimal amountRentalPrice) {
        this.amountRentalPrice = amountRentalPrice;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "orderNumber=" + orderNumber +
                ", vehicle=" + vehicle +
                ", client=" + person +
                ", rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", estimatedReturnDate=" + estimatedReturnDate +
                ", rentalAddress='" + rentalAddress + '\'' +
                ", returnAddress='" + returnAddress + '\'' +
                ", amountRentalPrice='" + amountRentalPrice + '\'' +
                '}';
    }
}
