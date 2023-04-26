import java.util.ArrayList;

public class World {
    private static World instance = new World();
    private Tile map;
    private ArrayList<Home> homeList ;
    private WorldClock worldClock;
    private Sim playedSim;

    // Don't forget change to 64x64
    private final int LEN_CAPACITY = 5;
    private final int WID_CAPACITY = 10;

    private World() {
        Tile matrix = new Tile(LEN_CAPACITY,WID_CAPACITY);
        this.map = matrix;
        this.homeList = new ArrayList<>();
    }

    public void printMap() {
        map.printTile();
    }

    public void updateMap() {
        for(Home temp : homeList) {
            int idX = temp.getLocX();
            int idY = temp.getLocY();

            map.changeTile("H", idX, idY);
        }
    }

    public static World getInstance() {
        return instance;
    }

    public Sim getPlayedSim(){
        return playedSim;
    }

    public void printHomeList() {
        System.out.println("ini list rumah");
    }

    public void setPlayedSim(Sim sim){
        this.playedSim = sim; 
    }
    
    public void addNewHome() {
        Home home = Home.newHome();
        homeList.add(home);
        updateMap();
    }

}
