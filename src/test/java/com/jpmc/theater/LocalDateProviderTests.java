package com.jpmc.theater;

import com.jpmc.theater.model.LocalDateProvider;
import org.junit.jupiter.api.Test;

public class LocalDateProviderTests {
    @Test
    void makeSureCurrentTime() {
        System.out.println("Current date is - " + LocalDateProvider.singleton().currentDate());
    }
}
