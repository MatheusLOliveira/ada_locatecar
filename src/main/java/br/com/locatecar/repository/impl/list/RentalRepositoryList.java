package br.com.locatecar.repository.impl.list;

import br.com.locatecar.model.Rental;
import br.com.locatecar.repository.api.IRentalRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class RentalRepositoryList implements IRentalRepository<Rental> {

    List<Rental> rentals;

    public RentalRepositoryList() {
        this.rentals = new ArrayList<>();
    }

    @Override
    public void add(Rental rental) {
        rentals.add(rental);
    }

    @Override
    public Rental update(Rental updatedRental) {
        for (int i = 0; i < this.rentals.size(); i++) {
            Rental rental = this.rentals.get(i);
            if (rental.getOrderNumber().equals(updatedRental.getOrderNumber())) {
                this.rentals.set(i, updatedRental);
                return updatedRental;
            }
        }
        return null;
    }

    @Override
    public Rental delete(Rental rentalToDelete) {
        Rental rental = getOneByPrimaryKey(rentalToDelete.getOrderNumber());

        if (rental != null) {
            rentals.remove(rentalToDelete);
        }
        return rental;
    }

    public Rental getOneByPrimaryKey(Integer id) {
        for (Rental rental : this.rentals) {
            if (rental.getOrderNumber().equals(id)) {
                return rental;
            }
        }
        return null;
    }

    @Override
    public List<Rental> getRentalsByReturnDate(LocalDateTime date) {
        return rentals.stream()
                .filter(rental -> rental.getReturnDate().isEqual(date))
                .collect(Collectors.toList());
    }

    @Override
    public List<Rental> getAll() {
        return Collections.unmodifiableList(rentals);
    }
}
