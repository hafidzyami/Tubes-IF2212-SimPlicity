import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.awt.Point;

public class Home {
    private int homeNumber ;
    private Point location;
    private HashMap <String,Room> roomList = new HashMap<>(); 
    private Sim owner;

    
    Random randomizer = new Random(12345678);
    
    public Home() {

    }

    public Home(Point locationOnMap) {
        this.location = location;
    }
    
    public static Home newHome() {
        Home retHome = new Home(new Point(3, 4));
        Room ruang01 = Room.firstRoom();
        retHome.roomList.put("ruang01",ruang01);
        return retHome;
    }

    public Point getLocation() {
        return location;
    }

    public HashMap <String,Room> getRoomList () {
        return roomList;
    }
    
    public int getLocX() {
        return (int) location.getX();
    }

    public int getLocY() {
        return (int) location.getY();
    }
}
