package br.com.locatecar.service.impl;

import br.com.locatecar.exceptions.DuplicateException;
import br.com.locatecar.model.*;
import br.com.locatecar.repository.api.IRentalRepository;
import br.com.locatecar.service.api.IRentalService;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class RentalService implements IRentalService {

    IRentalRepository<Rental> rentalRepository;

    public RentalService(IRentalRepository<Rental> rentalRepository) {
        this.rentalRepository = rentalRepository;
    }

    @Override
    public void add(Rental rentalToAdd) {
        Rental rental = getOneByPrimaryKey(rentalToAdd.getOrderNumber());

        if (rental == null) {
            rentalRepository.add(rentalToAdd);
        } else {
            throw new DuplicateException("Rental with the same ID already exists.");
        }
    }

    @Override
    public Rental update(Rental updatedRental) {
        return rentalRepository.update(updatedRental);
    }

    @Override
    public Rental delete(Rental rentalToDelete) {
        return rentalRepository.delete(rentalToDelete);
    }

    @Override
    public Rental getOneByPrimaryKey(Integer id) {
        return rentalRepository.getOneByPrimaryKey(id);
    }

    @Override
    public List<Rental> getRentalsByReturnDate(LocalDateTime date) {
        return rentalRepository.getRentalsByReturnDate(date);
    }

    @Override
    public List<Rental> getAll() {
        return rentalRepository.getAll();
    }

    private long totalDaysRentals(LocalDateTime rentalDate, LocalDateTime returnDate) {
        Duration rentalDuration = Duration.between(rentalDate, returnDate);
        double roundedHours = (double) rentalDuration.toMinutes() / 60;
        return (long) Math.ceil(roundedHours / 24);
    }

    @Override
    public void returnRental(Integer orderNumber, LocalDateTime returnDate) {
        Rental rental = rentalRepository.getOneByPrimaryKey(orderNumber);

        if (rental != null) {
            BigDecimal estimatedPrice = amountPrice(rental.getClient(), rental.getVehicle(), rental.getRentalDate(), rental.getEstimatedReturnDate());
            rental.setReturnDate(returnDate);
            if (!returnDate.isEqual(rental.getEstimatedReturnDate())) {
                BigDecimal realPrice = amountPrice(rental.getClient(), rental.getVehicle(), rental.getRentalDate(), rental.getReturnDate());
                if (returnDate.isAfter(rental.getEstimatedReturnDate())) {

                    System.out.println("You extended the estimated rental time.\n" +
                            "Because of that, the price increase in : R$" + realPrice.subtract(estimatedPrice) + "\n" +
                            "The new price: " + realPrice);
                } else {
                    System.out.println("You shortened the estimated rental time.\n" +
                            "Because of that, the price decrease in : R$" + realPrice.subtract(estimatedPrice) + "\n" +
                            "The new price: " + realPrice);
                }
            } else {
                System.out.println("Price of the car: " + estimatedPrice);
            }
        }
    }

    @Override
    public BigDecimal amountPrice(Person client, Vehicle vehicle, LocalDateTime rentalDate, LocalDateTime estimatedReturnDate) {
        BigDecimal discountCompanyEntity = BigDecimal.valueOf(0.05);
        BigDecimal discountPersonalEntity = BigDecimal.valueOf(0.10);

        long daysRentals = totalDaysRentals(rentalDate, estimatedReturnDate);

        BigDecimal totalValue = vehicle.getDailyRental().multiply(BigDecimal.valueOf(daysRentals));
        BigDecimal totalValueDiscount = totalValue;

        if ((client instanceof CompanyEntity) && (daysRentals > 5)) {
            BigDecimal discount = totalValue.multiply(discountCompanyEntity);
            totalValueDiscount = totalValue.subtract(discount);
        } else if ((client instanceof PersonalEntity) && (daysRentals > 3)) {
            BigDecimal discount = totalValue.multiply(discountPersonalEntity);
            totalValueDiscount = totalValue.subtract(discount);
        }
        return totalValueDiscount;
    }
}
