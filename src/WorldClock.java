public class WorldClock  {
    private int gameDay;
    private int totalElapsed; //satuan detik
    private final int MINUTE_IN_DAY = 12;

    public WorldClock() {
        gameDay = 1;
    }

    public void updateTime(int duration) {
        this.totalElapsed += duration;
    }

    public int getDay() {
        gameDay = totalElapsed / 720 ;
        return gameDay;
    }

    
    public static void main(String[] args) {
        WorldClock jam = new WorldClock();
        jam.updateTime(40);
        jam.updateTime(1000);
        System.out.println(jam.gameDay);
    }
}
