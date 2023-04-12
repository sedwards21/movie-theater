package com.jpmc.theater.service;

import com.jpmc.theater.Constants;
import com.jpmc.theater.model.Movie;
import com.jpmc.theater.model.Showing;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;

@NoArgsConstructor
@Service
public class PricingService {
    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");

    public static boolean isMovieASpecial(int specialCode) {
        return Constants.MOVIE_CODE_SPECIAL == specialCode;
    }

    public static boolean isShowtimeBeetween(int start, int end, LocalDateTime showStartTime) {
        return start <= showStartTime.getHour() && showStartTime.getHour() < end;
    }

    public static double getSequenceDiscount(int showSequence) {
        double discount = 0.0;
        switch (showSequence) {
            case 1:
                discount = 3.0;
            case 2:
                discount = 2.0;
        }
        return discount;

    }

    public static double getFixedBasedDiscounts(int showSequence, LocalDateTime showStartTime) {
        double discount = getSequenceDiscount(showSequence);
        //Any movies showing on 7th, you'll get 1$ discount
        if (showStartTime.getDayOfMonth() == Constants.LUCKY_DAY_FOR_DISCOUNT) {
            discount = Math.max(discount, 1.0);
        }

        return discount;
    }

    public static double getPercentageBased(double ticketPrice, int specialCode, LocalDateTime showStartTime) {
        double discount = 0.0;
        System.out.println(showStartTime);
        if (isShowtimeBeetween(11, 16, showStartTime)) {
            //Any movies showing starting between 11AM ~ 4pm, get 25% discount
            discount = ticketPrice * Constants.MIDDAY_DISCOUNT_PERCENTAGE;
        } else if (isMovieASpecial(specialCode)) {
            discount = ticketPrice * Constants.SPECIAL_MOVIE_DISCOUNT_PERCENTAGE;
        }

        return discount;
    }

    public static double calculateDiscount(int showSequence, Movie movie, LocalDateTime showStartTime) {
///Seperated into fixed and percentage because we only care about the highest discount for each
        double fixedDiscount = getFixedBasedDiscounts(showSequence, showStartTime);
        double percentageDiscount = getPercentageBased(movie.getTicketPrice(), movie.getSpecialCode(), showStartTime);
        // biggest discount wins
        return fixedDiscount > percentageDiscount ? fixedDiscount : percentageDiscount;
    }

    public double calculateTicketPrice(Showing showing) {
        double discount = calculateDiscount(showing.getSequenceOfTheDay(), showing.getMovie(), showing.getStartTime());
        return (showing.getMovie().getTicketPrice() - discount);
    }


}
