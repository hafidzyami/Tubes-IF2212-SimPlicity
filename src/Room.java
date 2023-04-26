import java.util.HashMap;

public class Room {
    private HashMap <String,Item> itemList = new HashMap<>();
    private String roomName;
    /* Game akan men-generate rumah dengan 
    1 ruangan yang dimensi 6x6 dengan objek basic 
    berupa kasur, toilet, kompor, kursi, meja, dan jam */
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

    public static Room firstRoom() {
        Room retRoom = new Room("Main Room");

        Item toilet01 = new NonFoodItem("Toilet");
        Item kursi01 = new NonFoodItem("Table and Chair");
        Item meja01 = new NonFoodItem("Clock");
        Item jam01 = new NonFoodItem("Gas Stove");

        retRoom.itemList.put("toilet01",toilet01);
        retRoom.itemList.put("kursi01",kursi01);
        retRoom.itemList.put("meja01",meja01);
        retRoom.itemList.put("jam01",jam01);
        return retRoom;
    }

}
