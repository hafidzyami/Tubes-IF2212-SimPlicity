import java.util.HashMap;
import java.util.Random;
import java.awt.Point;

public class Home {
    private int homeNumber ;
    private Point locationOnMap;
    private HashMap <String,Room> roomList = new HashMap<>(); 
    private int roomTotal;

    
    Random randomizer = new Random(12345678);
    
    public Home() {

    }

    public Home(Point locationOnMap) {
        this.locationOnMap = locationOnMap;
    }
    
    public static Home newHome() {
        Home retHome = new Home(new Point(3, 4));
        Room ruang01 = Room.firstRoom();
        retHome.roomList.put("ruang01",ruang01);
        return retHome;
    }

    public Point getLocationOnMap() {
        return locationOnMap;
    }

    public int getLocX() {
        return (int) locationOnMap.getX();
    }

    public int getLocY() {
        return (int) locationOnMap.getY();
    }
}
