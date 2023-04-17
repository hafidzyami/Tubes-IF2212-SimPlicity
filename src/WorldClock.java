import java.time.Duration;
import java.time.Instant;

public class WorldClock  {
    private Instant startTime = Instant.now();
    private Duration gameTime;
    private int gameDay;
    private static final int MINUTE_IN_DAY = 12;

    public WorldClock() {
        this.gameDay = 1;
    }
    
    public int getGameDay() {
        Instant now = Instant.now();
        Duration.between(startTime, now);
        refreshTime();
        return gameDay;
    }

    private void refreshTime() {
        gameDay =(int) (gameTime.toSeconds() % 12);
    }


}
