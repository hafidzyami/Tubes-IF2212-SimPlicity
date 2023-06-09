import java.util.ArrayList;

public class World {
    private static World instance = new World();
    private Tile map;
    private ArrayList<House> houseList;
    private WorldClock worldClock;

    private Sim playedSim;
    private ArrayList<Sim> simList = new ArrayList<>();

    // TODO : Don't forget change to 64x64
    private final int LEN_CAPACITY = 64;
    private final int WID_CAPACITY = 64;

    private World() {
        Tile matrix = new Tile(LEN_CAPACITY,WID_CAPACITY);
        this.map = matrix;
        this.houseList = new ArrayList<>();
        this.worldClock = new WorldClock();
        
    }

    public void printMap() {
        map.printTile();
    }

    public synchronized boolean updateMap() {
        for (House temp : houseList) {
            int idX = temp.getLocX();
            int idY = temp.getLocY();
            map.changeTile(temp.getOwner().getSimName(), idX, idY);
        }
        ArrayList<Sim> deadSim = new ArrayList<>();
        for(Sim temp : simList) {
            if(temp.checkDie()) {
                System.out.println(temp.getSimName() +" Mati");
                deadSim.add(temp);
            }
        }
        for(Sim temp : deadSim) {
            simList.remove(temp);
        }
        deadSim = new ArrayList<>();
        

        ArrayList<House> hauntedHouse = new ArrayList<>();
        for(House temp : houseList) {
            int idX = temp.getLocX();
            int idY = temp.getLocY();
            if (!temp.getOwner().checkDie()) {
                map.changeTile(temp.getOwner().getSimName(), idX, idY);
            }
            else {
                System.out.println(temp.getOwner().getSimName() +" sudah mati dan rumahnya hilang");
                hauntedHouse.add(temp);
            }
        }
        for(House temp : hauntedHouse) {
            houseList.remove(temp);
            int idX = temp.getLocX();
            int idY = temp.getLocY();
            map.changeTile("E", idX, idY);
        }
        hauntedHouse = new ArrayList<>();

        if (!simList.contains(playedSim)) {
            if(simList.size() > 0) {
                playedSim = simList.get(0);
                System.out.println("Otomatis memainkan " + playedSim.getSimName());
                for(Sim temp : simList) {
                    temp.setCurrentHouse(temp.getMyHouse());
                    //System.out.println(temp.getMyHouse().getRoomList().get(0).getRoomName());
                    temp.setCurrentRoom(temp.getMyHouse().getRoomList().get(0));
                }
            }
            else {
                return true;
            }
        }
        return false;
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
    public void printHouseList() {
        System.out.println("ini list rumah");
    }

    public void setPlayedSim(Sim sim){
        this.playedSim = sim; 
    }
    
    public void addNewHouse(House house) {
        houseList.add(house);
        updateMap();
    }

    public ArrayList<House> getHouseList(){
        return this.houseList;
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

    public void updateNotPlayed(int duration) {
        for(Sim temp : simList) {
            if(!temp.equals(playedSim)) {
                temp.gainNegativeEffect(duration);
                temp.nextPassiveTime(duration);
            } 
        }
    }
    
    public static void main(String[] args) {
        World world = World.getInstance();
        world.printMap();
        System.out.println(world.getHouseList());
        House house1 = House.newHouse(world, 5, 5);
        Sim sim1 = new Sim("aku", house1, world);
        house1.setOwner(sim1);
        world.addSimList(sim1);
        world.addNewHouse(house1);
        world.setPlayedSim(sim1);
        sim1.setCurrentRoom(house1.getRoomList().get(0));
        world.updateMap();
    }
}
