import java.util.HashMap;

public class Room {
    private HashMap <String,Item> itemList = new HashMap<>();

    /* Game akan men-generate rumah dengan 
    1 ruangan yang dimensi 6x6 dengan objek basic 
    berupa kasur, toilet, kompor, kursi, meja, dan jam */
    public Room() {

    }

    public static  Room firstRoom() {
        Room retRoom = new Room();

        Item toilet01 = new NonFoodItem("Toilet");
        Item kursi01 = new NonFoodItem("Kursi");
        Item meja01 = new NonFoodItem("Meja");
        Item jam01 = new NonFoodItem("Jam");

        retRoom.itemList.put("toilet01",toilet01);
        retRoom.itemList.put("kursi01",kursi01);
        retRoom.itemList.put("meja01",meja01);
        retRoom.itemList.put("jam01",jam01);
        return retRoom;
    }

}
