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

    public int getTotalElapsed(){
        return totalElapsed; 
    }

    public int getSisaWaktu() {
        getDay();
        /* System.out.println(totalElapsed);
        System.out.println(gameDay); */
        return gameDay * 720 - totalElapsed;
    }

    // Delay dengan duration dalam detik
    public void wait(int duration) {
        try {
            Thread.sleep(duration*1000);
        }
        catch(Exception e) {
            Thread.interrupted();
        }
    }
    public static void main(String[] args) {
        WorldClock jam = new WorldClock();
        jam.updateTime(40);
        jam.updateTime(1000);
        
        System.out.println(jam.getSisaWaktu());
        System.out.println(jam.gameDay);
    }
}
