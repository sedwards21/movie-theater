package com.jpmc.theater.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class LocalDateProvider {
    private static LocalDateProvider instance = null;

    /**
     * @return make sure to return singleton instance
     */
    public static LocalDateProvider singleton() {
        if (instance == null) {
            instance = new LocalDateProvider();
        }
            return instance;
        }

    public LocalDate currentDate() {
            return LocalDate.now();
    }


    public LocalDateTime currentDateTimeAt(int hour, int minute) {
        return LocalDateTime.of(currentDate(), LocalTime.of(hour, minute));
    }

}
