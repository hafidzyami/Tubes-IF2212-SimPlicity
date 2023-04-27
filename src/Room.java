import java.util.HashMap;

public class Room {
    private HashMap <String,Item> itemList = new HashMap<>();
    private String roomName;
    private Home RoomLoc;
    /* Game akan men-generate rumah dengan 
    1 ruangan yang dimensi 6x6 dengan objek basic 
    berupa kasur, toilet, kompor, kursi, meja, dan jam */
    private Tile roomTile = new Tile(6, 6);

    public Room(String roomName,Home home) {
        this.roomName = roomName;
        this.RoomLoc = home;
    }

    public Tile getRoomTile(){
        return this.roomTile;
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

    public static Room firstRoom(Home home) {
        Room retRoom = new Room("Ruang utama",home);
        return retRoom;
    }

    public static void main(String[] args){
        //Room room = new Room("dapur").firstRoom();
        //System.out.println(room.getItemList().get("kursi01").getName());
    }

}
