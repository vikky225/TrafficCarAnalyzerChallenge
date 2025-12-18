package com.aips.traffic.domain;

import java.time.LocalDate;

public class DailyTotal {
    private final LocalDate date;
    private final int totalCars;

    public DailyTotal(LocalDate date, int totalCars) {
        this.date = date;
        this.totalCars = totalCars;
    }
    public LocalDate getDate() {
        return date;
    }

    public int getTotalCars() {
        return totalCars;
    }
}