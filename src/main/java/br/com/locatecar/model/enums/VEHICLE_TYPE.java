package br.com.locatecar.model.enums;

import java.math.BigDecimal;

public enum VEHICLE_TYPE {
    SHORT(BigDecimal.valueOf(100.00)),
    MEDIUM(BigDecimal.valueOf(150.00)),
    SUV(BigDecimal.valueOf(200.00));

    private final BigDecimal dailyRentalBigDecimal;

    VEHICLE_TYPE(BigDecimal dailyRentalBigDecimal) {
        this.dailyRentalBigDecimal = dailyRentalBigDecimal;
    }

    public BigDecimal getDailyRentalBigDecimal() {
        return dailyRentalBigDecimal;
    }
}
