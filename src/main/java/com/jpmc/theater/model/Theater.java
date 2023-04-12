package com.jpmc.theater.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import lombok.Setter;

import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.jpmc.theater.util.Util.roundToTwoDecimals;

@Getter
@Setter
public class Theater {

    private LocalDateProvider provider;
    private List<Showing> schedule;

    public Theater(LocalDateProvider provider) {
        this.provider = provider;
        this.schedule = formSchedule();

    }

    public Theater(List<Showing> schedule) {
        this.schedule = schedule;
        this.provider = LocalDateProvider.singleton();
    }

    private List<Showing> formSchedule() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        Movie turningRed = new Movie("Turning Red", Duration.ofMinutes(85), 11, 0);
        Movie theBatMan = new Movie("The Batman", Duration.ofMinutes(95), 9, 0);
        return List.of(
                new Showing(turningRed, 1, provider.currentDateTimeAt(9, 0)),
                new Showing(spiderMan, 2, provider.currentDateTimeAt(11, 0)),
                new Showing(theBatMan, 3, provider.currentDateTimeAt(12, 50)),
                new Showing(turningRed, 4, provider.currentDateTimeAt(14, 30)),
                new Showing(spiderMan, 5, provider.currentDateTimeAt(16, 10)),
                new Showing(theBatMan, 6, provider.currentDateTimeAt(17, 50)),
                new Showing(turningRed, 7, provider.currentDateTimeAt(19, 30)),
                new Showing(spiderMan, 8, provider.currentDateTimeAt(21, 10)),
                new Showing(theBatMan, 9, provider.currentDateTimeAt(23, 0))
        );
    }


    public Reservation reserve(Customer customer, int sequence, int howManyTickets) {
        Showing showing;
        try {
            showing = schedule.get(sequence - 1);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new IllegalStateException("not able to find any showing for given sequence " + sequence);
        }
        return new Reservation(customer, showing, howManyTickets);
    }

    public String humanReadableFormat(Duration duration) {
        long hour = duration.toHours();
        long remainingMin = duration.toMinutes() - TimeUnit.HOURS.toMinutes(duration.toHours());

        return String.format("(%s hour%s %s minute%s)", hour, handlePlural(hour), remainingMin, handlePlural(remainingMin));
    }

    // (s) postfix should be added to handle plural correctly
    private String handlePlural(long value) {
        if (value == 1) {
            return "";
        } else {
            return "(s)";
        }
    }

    public void printSchedule() {
        final String titleHeaderLen = String.valueOf(25);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a E, MMM dd yyyy");

        System.out.println("===================================================");
        schedule.forEach(s ->
                System.out.printf("%s - %s - %s - %s - $%.2f\n", s.getSequenceOfTheDay(), s.getStartTime().format(formatter), s.getMovie().getTitle(), humanReadableFormat(s.getMovie().getRunningTime()), s.getMovieFee())
        );
        System.out.println("===================================================");


    }


    public void printScheduleJson() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(schedule);
        System.out.print(json);
    }
}
