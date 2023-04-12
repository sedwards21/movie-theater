package com.jpmc.theater;

import com.jpmc.theater.model.*;
import com.jpmc.theater.service.PricingService;
import org.junit.jupiter.api.Test;

import java.time.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MovieTests extends AbstractPojoTester {

    PricingService rService = new PricingService();
    LocalDate NON_DISCOUNT_DATE = LocalDate.of(2023, Month.APRIL, 12);
    LocalTime NON_DISCOUNT_TIME = LocalTime.of(17, 30);

    @Test
    public void testGettersAndSetters() {
        this.testGetterSetter(Movie.class);
    }

    @Test
    public void testHashCode() {
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);

        int hash = turningRed.hashCode();
        assertEquals(1380412033,hash );
    }


}
