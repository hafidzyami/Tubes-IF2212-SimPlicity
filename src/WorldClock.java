public class WorldClock  {
    private int gameDay;
    private int totalElapsed; //satuan detik

    public WorldClock() {
        gameDay = 1;
    }

    public void updateTime(int duration) {
        this.totalElapsed += duration;
    }

    public int getDay() {
        gameDay = (totalElapsed / 720 ) + 1;
        return gameDay;
    }

    public int getSisaWaktu() {
        getDay();
        /* System.out.println(totalElapsed);
        System.out.println(gameDay); */
        return gameDay * 720 - totalElapsed;
    }


    public static void main(String[] args) {
        WorldClock jam = new WorldClock();
        jam.updateTime(40);
        jam.updateTime(1000);
        
        System.out.println(jam.getSisaWaktu());
        System.out.println(jam.gameDay);
    }
}
