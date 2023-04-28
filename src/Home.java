import java.util.ArrayList;
import java.util.Random;

public class Home {
    private int homeNumber ;
    private Point location;
    private ArrayList<Room> roomList = new ArrayList<>(); 
    private Sim owner;

    Random randomizer = new Random(12345678);
    
    public Home() {

    }

    public Home(Point location) {
        this.location = location;
    }
    
    public static Home newHome(World world) {
        Home retHome = new Home(new Point(3, 4));
        Room ruang01 = Room.firstRoom(retHome);
        retHome.roomList.add(ruang01);
        return retHome;
    }

    public static Home newHome(World world,Point point) {
        Home retHome = new Home(new Point(point.getX(),point.getY()));
        Room ruang01 = Room.firstRoom(retHome);
        retHome.roomList.add(ruang01);
        return retHome;
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
