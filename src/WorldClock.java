public class WorldClock  {
    private int gameDay;
    private int totalElapsed; //satuan detik
    private boolean haveAddSim;

    public WorldClock() {
        gameDay = 1;
        haveAddSim = false;
    }

    public void updateTime(int duration) {
        int temp = getDay();
        this.totalElapsed += duration;
        if(temp != getDay()){
            haveAddSim = false;
        }
    }

    public boolean getHaveAddSim(){
        return haveAddSim;
    }

    public void setHaveAddSim(boolean add){
        haveAddSim = add;
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
            for(int i=0;i < duration;i++) {
                System.out.print("Waiting.");
                Thread.sleep(500);
                System.out.print("\b\b\b\b\b\b\b\b");
                Thread.sleep(500);
                System.out.println();
            }
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
