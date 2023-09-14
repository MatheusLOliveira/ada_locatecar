package br.com.locatecar.service.api;

import br.com.locatecar.model.Person;
import br.com.locatecar.model.Rental;
import br.com.locatecar.model.Vehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface IRentalService extends IService<Rental>{

    BigDecimal amountPrice(Person client, Vehicle vehicle, LocalDateTime rentalDate, LocalDateTime estimatedReturnDate);
    void returnRental(Integer orderNumber, LocalDateTime returnDate);
    Rental getOneByPrimaryKey(Integer id);
    List<Rental> getRentalsByReturnDate(LocalDateTime date);

}
