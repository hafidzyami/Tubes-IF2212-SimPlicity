import java.util.ArrayList;

public class House {
    private int houseNumber;
    private Point location;
    private ArrayList<Room> roomList = new ArrayList<>(); 
    private Sim owner;
    private Tile houseTile = new Tile(7, 7);

    public House() {

    }

    public House(Point location) {
        this.location = location;
    }
    
    public static House newHouse(World world, int x, int y) {
        House retHouse = new House(new Point(x, y));
        Room ruang01 = Room.firstRoom(retHouse);
        retHouse.roomList.add(ruang01);
        retHouse.houseTile.changeTile(ruang01.getRoomName(), 3, 3);
        return retHouse;
    }

    public static House newHouse(World world,Point point) {
        House retHouse = new House(new Point(point.getX(),point.getY()));
        Room ruang01 = Room.firstRoom(retHouse);
        ruang01.setRoomCoordinate(3, 3);
        retHouse.roomList.add(ruang01);
        retHouse.houseTile.changeTile(ruang01.getRoomName(), 3, 3);
        return retHouse;
    }

    public Point getLocation() {
        return location;
    }

    public Tile getHouseTile(){
        return houseTile;
    }

    public ArrayList<Room> getRoomList () {
        return roomList;
    }
    
    public int getLocX() {
        return (int) location.getX();
    }

    public int getLocY() {
        return (int) location.getY();
    }

    public Sim getOwner() {
        return owner;
    }

    public void setOwner(Sim sim) {
        this.owner = sim;
    }

    public void addRoom(Room room){
        this.roomList.add(room);
    }

    public void printRoomList(){
        for(int i = 0; i < roomList.size(); i++){
            System.out.println((i+1) + ". " + roomList.get(i).getRoomName());
        }
    }

    public void updateMap() {
        for(Room temp : roomList) {
            int idX = temp.getRoomCoordinate().getX();
            int idY = temp.getRoomCoordinate().getY();
            houseTile.changeTile(temp.getRoomName(), idX, idY);
        }
    }
}
