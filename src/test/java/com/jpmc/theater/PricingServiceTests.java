package com.jpmc.theater;

import com.jpmc.theater.model.Movie;
import com.jpmc.theater.service.PricingService;
import com.jpmc.theater.model.Showing;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PricingServiceTests {
    PricingService pricingService = new PricingService();
    String TEST_MOVIE_TITLE = "TEST_TITLE";
    double TEST_TICKET_PRICE = 12.5;
    int NON_DISCOUNT_SEQUENCE = 5;
    LocalDate DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 7);
    LocalDate NON_DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 12);
    LocalTime NON_DISCOUNT_TIME = LocalTime.of(17, 30);

    @Test
    void testSpecialMoviePrice() {
        var TEST_TICKET_PRICE = 1.00;
        Movie spiderMan = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 1);
        Showing showing = new Showing(spiderMan, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be 20% off of the regular price of 12.5
        assertEquals(0.8 * TEST_TICKET_PRICE, pricingService.calculateTicketPrice(showing));
    }

    @Test
    void testDiscountDayPrice() {
        Movie testMovie = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(90), TEST_TICKET_PRICE, 0);
        Showing showing = new Showing(testMovie, NON_DISCOUNT_SEQUENCE, LocalDateTime.of(DISCOUNT_DATE, NON_DISCOUNT_TIME));
        // ticket price should be 20% off of the regular price of 12.5
        assertEquals(TEST_TICKET_PRICE - 1, pricingService.calculateTicketPrice(showing));
    }

    @Test
    public void testCalculateDiscountForNonDiscountShowings() {
        Movie test1 = new Movie(TEST_MOVIE_TITLE, Duration.ofMinutes(55), TEST_TICKET_PRICE, 0);
        for (int sequence = 4; sequence <= 9; sequence++) {
            Showing showing = new Showing(test1, sequence, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));
            // discount should be 0.0
            assertEquals(0.0, pricingService.calculateDiscount(showing.getSequenceOfTheDay(), showing.getMovie(), showing.getStartTime()));
        }
    }

    @Test
    void specialMovieWith50PercentDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(NON_DISCOUNT_DATE, NON_DISCOUNT_TIME));

        assertEquals(10, pricingService.calculateTicketPrice(showing));
    }
}
