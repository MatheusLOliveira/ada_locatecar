package br.com.locatecar.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class FormatterDate {

    public static LocalDateTime execute(String day, String hour) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH");
        LocalDate localDate = LocalDate.parse(day, dateFormatter);
        LocalTime localTime = LocalTime.parse(hour, timeFormatter);

        return localDate.atTime(localTime);
    }

}
