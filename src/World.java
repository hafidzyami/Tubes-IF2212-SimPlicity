import java.util.ArrayList;

public class World {
    private static World instance = new World();
    private Tile map;
    private ArrayList<Home> homeList;
    private WorldClock worldClock;

    private Sim playedSim;
    private ArrayList<Sim> simList = new ArrayList<>();

    // Don't forget change to 64x64
    private final int LEN_CAPACITY = 5;
    private final int WID_CAPACITY = 10;

    private World() {
        Tile matrix = new Tile(LEN_CAPACITY,WID_CAPACITY);
        this.map = matrix;
        this.homeList = new ArrayList<>();
        this.worldClock = new WorldClock();
        
    }

    public void printMap() {
        map.printTile();
    }

    public void updateMap() {
        for(Home temp : homeList) {
            int idX = temp.getLocX();
            int idY = temp.getLocY();

            map.changeTile(temp.getOwner().getSimName(), idX, idY);
        }
    }

    public static World getInstance() {
        return instance;
    }

    public Sim getPlayedSim(){
        return playedSim;
    }

    public WorldClock getWorldClock() {
        return worldClock;
    }
    public void printHomeList() {
        System.out.println("ini list rumah");
    }

    public void setPlayedSim(Sim sim){
        this.playedSim = sim; 
    }
    
    public void addNewHome(Home home) {
        homeList.add(home);
        updateMap();
    }

    public ArrayList<Home> getHomeList(){
        return this.homeList;
    }

    public ArrayList<Sim> getSimList(){
        return this.simList;
    }

    public void addSimList(Sim sim){
        this.simList.add(sim);
    }

    public Tile getWorldTile(){
        return this.map;
    }

    public int getWorldWidth(){
        return this.WID_CAPACITY;
    }

    public int getWorldLength(){
        return this.LEN_CAPACITY;
    }




    public void printSimList(){
        for(int i = 0 ; i < simList.size(); i++){
            System.out.println((i+1) + ". " + simList.get(i).getSimName());
        }
    }
    public static void main(String[] args) {
        World world = World.getInstance();
        world.printMap();
        System.out.println(world.getHomeList());
    }
}
