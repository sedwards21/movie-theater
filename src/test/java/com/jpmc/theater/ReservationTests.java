package com.jpmc.theater;

import com.jpmc.theater.model.*;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {
    LocalDateProvider provider = LocalDateProvider.singleton();
    LocalDateTime nonDiscountDateTime = provider.currentDateTimeAt(10, 0);

    LocalDate DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 7);
    LocalDate NON_DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 12);
    LocalTime NON_DISCOUNT_TIME = LocalTime.of(17, 30);

    Movie specialMovie = new Movie("Special Code Movie", Duration.ofMinutes(90), 10.5, 1);
    Movie veryCheapMovie = new Movie("Very Cheap Movie", Duration.ofMinutes(85), 1, 0);
    Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
    Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
    Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);

    List<Showing> fullShowings = List.of(
            new Showing(turningRed, 1, LocalDateProvider.singleton().currentDateTimeAt(9, 0)),
            new Showing(spiderMan, 2, LocalDateProvider.singleton().currentDateTimeAt(11, 0)),
            new Showing(theBatMan, 3, LocalDateProvider.singleton().currentDateTimeAt(12, 50)),
            new Showing(turningRed, 4, LocalDateProvider.singleton().currentDateTimeAt(14, 30)),
            new Showing(spiderMan, 5, LocalDateProvider.singleton().currentDateTimeAt(16, 10)),
            new Showing(theBatMan, 6, LocalDateProvider.singleton().currentDateTimeAt(17, 50)),
            new Showing(turningRed, 7, LocalDateProvider.singleton().currentDateTimeAt(19, 30)),
            new Showing(spiderMan, 8, LocalDateProvider.singleton().currentDateTimeAt(21, 10)),
            new Showing(theBatMan, 9, LocalDateProvider.singleton().currentDateTimeAt(23, 0))
    );

    List<Showing> cheapList = List.of(
            new Showing(specialMovie, 1, LocalDateProvider.singleton().currentDateTimeAt(9, 0)),
            new Showing(veryCheapMovie, 2, LocalDateProvider.singleton().currentDateTimeAt(11, 0)),
            new Showing(theBatMan, 3, LocalDateProvider.singleton().currentDateTimeAt(12, 50))
            );

    @Test
    void totalFeeFirstMovie() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME)
        );
        var reservationFee = new Reservation(customer, showing, 3).totalFee();
        System.out.println("You have to pay " + reservationFee);
        assertTrue( reservationFee == 30);
    }

    @Test
    void totalFeeSecondMovie() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME)
        );
        var reservationFee = new Reservation(customer, showing, 3).totalFee();

        assertTrue(reservationFee == 30);
    }

    @Test
    void totalFeeSecondMovieWithDiscountDay() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(DISCOUNT_DATE, NON_DISCOUNT_TIME)
        );
        var reservationFee = new Reservation(customer, showing, 3).totalFee();

        assertTrue(reservationFee == 30);
    }

    @Test
    void totalFeeThirdMovie() {

        var customer = new Customer("John Doe", "unused-id");
        var dateTime = provider.currentDateTimeAt(10, 0);
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 8.5, 1),
                3,
                LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME)
        );

        var reservationFee = new Reservation(customer, showing, 3).totalFee();
        System.out.println("You have to pay " + reservationFee);

        assertTrue(reservationFee==20.4);
    }
}
