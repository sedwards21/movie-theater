package com.jpmc.theater.model;

import com.jpmc.theater.service.PricingService;

import java.time.LocalDateTime;

import static com.jpmc.theater.util.Util.roundToTwoDecimals;

public class Showing {
    private Movie movie;
    private int sequenceOfTheDay;
    private LocalDateTime showStartTime;
    private transient PricingService resyService;

    public Showing(Movie movie, int sequenceOfTheDay, LocalDateTime showStartTime) {
        this.movie = movie;
        this.sequenceOfTheDay = sequenceOfTheDay;
        this.showStartTime = showStartTime;
        this.resyService = new PricingService();
    }

    public Movie getMovie() {
        return movie;
    }

    public LocalDateTime getStartTime() {
        return showStartTime;
    }

    public double getMovieFee() {
        return  roundToTwoDecimals(resyService.calculateTicketPrice(this));
    }

    public int getSequenceOfTheDay() {
        return sequenceOfTheDay;
    }

}
