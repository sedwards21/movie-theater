import com.jpmc.theater.model.LocalDateProvider;
import com.jpmc.theater.model.Theater;

public class TheaterApplication {
    public static void main(String[] args) {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
        theater.printScheduleJson();
    }
}
