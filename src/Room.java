import java.util.HashMap;

public class Room {
    private HashMap <String,Item> itemList = new HashMap<>();
    private String roomName;
    private House RoomLoc;
    private Point roomCoordinate = new Point(0, 0);
    /* Game akan men-generate rumah dengan 
    1 ruangan yang dimensi 6x6 dengan objek basic 
    berupa kasur, toilet, kompor, kursi, meja, dan jam */
    private Tile roomTile = new Tile(6, 6);

    public Room(String roomName,House house) {
        this.roomName = roomName;
        this.RoomLoc = house;
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
        for(int i = 1; i <= 6; i++){
            for(int j = 1; j <= 6; j++){
                if(roomTile.getTile(i, j).equals(name)){
                    roomTile.changeTile("E", i, j);
                }
            }
        }
        this.itemList.remove(name);
    }

    public void addItem(String key, Item item){
        this.itemList.put(key, item);
    }

    public static Room firstRoom(House house) {
        Room retRoom = new Room("RuangUtama",house);
        retRoom.setRoomCoordinate(4, 4);
        return retRoom;
    }

    public Point getRoomCoordinate(){
        return this.roomCoordinate;
    }

    public void setRoomCoordinate(int x, int y){
        this.roomCoordinate.setX(x);
        this.roomCoordinate.setY(y);
    }

    

    public static void main(String[] args){
        //Room room = new Room("dapur").firstRoom();
        //System.out.println(room.getItemList().get("kursi01").getName());
    }

}
