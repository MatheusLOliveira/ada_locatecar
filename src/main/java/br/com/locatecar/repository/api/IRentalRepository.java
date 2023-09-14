package br.com.locatecar.repository.api;

import java.time.LocalDateTime;
import java.util.List;

public interface IRentalRepository<Rental> extends Repository<Rental> {

    Rental getOneByPrimaryKey(Integer id);
    List<Rental> getRentalsByReturnDate(LocalDateTime date);

}
