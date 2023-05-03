import java.util.ArrayList;

public class House {
    private int houseNumber ;
    private Point location;
    private ArrayList<Room> roomList = new ArrayList<>(); 
    private Sim owner;

    public House() {

    }

    public House(Point location) {
        this.location = location;
    }
    
    public static House newHouse(World world, int x, int y) {
        House retHouse = new House(new Point(x, y));
        Room ruang01 = Room.firstRoom(retHouse);
        retHouse.roomList.add(ruang01);
        return retHouse;
    }

    public static House newHouse(World world,Point point) {
        House retHouse = new House(new Point(point.getX(),point.getY()));
        Room ruang01 = Room.firstRoom(retHouse);
        retHouse.roomList.add(ruang01);
        return retHouse;
    }

    public Point getLocation() {
        return location;
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
}
