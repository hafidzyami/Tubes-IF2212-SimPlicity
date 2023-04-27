import java.util.HashMap;

public class Room {
    private HashMap <String,Item> itemList = new HashMap<>();
    private String roomName;
    /* Game akan men-generate rumah dengan 
    1 ruangan yang dimensi 6x6 dengan objek basic 
    berupa kasur, toilet, kompor, kursi, meja, dan jam */
    private Tile roomTile = new Tile(6, 6);

    public Tile getRoomTile(){
        return this.roomTile;
    }
    

    public Room(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomName(){
        return this.roomName;
    }

    public HashMap <String,Item> getItemList(){
        return this.itemList;
    }

    public void removeItem(String name){
        this.itemList.remove(name);
    }

    public void addItem(String key, Item item){
        this.itemList.put(key, item);
    }

    public static Room firstRoom(World world) {
        Sim sim = world.getPlayedSim();
        Room retRoom = new Room("Main Room");

        Item toilet01 = new NonFoodItem("Toilet");
        Item kursi01 = new NonFoodItem("Table and Chair");
        Item meja01 = new NonFoodItem("Clock");
        Item jam01 = new NonFoodItem("Gas Stove");

        sim.getSimInventory().addInventory(toilet01);
        sim.getSimInventory().addInventory(kursi01);
        sim.getSimInventory().addInventory(meja01);
        sim.getSimInventory().addInventory(jam01);
        return retRoom;
    }

    public static void main(String[] args){
        //Room room = new Room("dapur").firstRoom();
        //System.out.println(room.getItemList().get("kursi01").getName());
    }

}
