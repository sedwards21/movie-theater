package com.jpmc.theater;

import com.jpmc.theater.model.*;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TheaterTests extends AbstractPojoTester {

    Movie specialMovie = new Movie("Special Code Movie", Duration.ofMinutes(90), 10.5, 1);
    Movie veryCheapMovie = new Movie("Special Code Movie", Duration.ofMinutes(90), 10.5, 1);
    Movie theBatMan = new Movie("Special Code Movie", Duration.ofMinutes(90), 10.5, 1);

    List<Showing> cheapList = List.of(
            new Showing(specialMovie, 1, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(9, 0))),
            new Showing(veryCheapMovie, 2, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(11, 0))),
            new Showing(theBatMan, 3, LocalDateTime.of(LocalDateProvider.singleton().currentDate(), LocalTime.of(12, 50)))
    );

    @Test
    public void testGettersAndSetters() {
        this.testGetterSetter(Theater.class);
    }

    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(cheapList);
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(reservation.totalFee(), 31.52);
    }

    @Test
    void reserveNonExistentSequence() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.setSchedule(cheapList);
        Customer john = new Customer("John Doe", "id-12345");
        assertThrows(Exception.class,
                () -> {
                    theater.reserve(john, 35, 4);
                });
    }

    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.setSchedule(cheapList);
        theater.printSchedule();
    }

    @Test
    void printMovieScheduleJson() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.setSchedule(cheapList);
        theater.printScheduleJson();
    }
}
