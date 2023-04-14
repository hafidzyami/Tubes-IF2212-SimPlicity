import java.awt.Point;

public class Location {
    private Point upperLeft;
    private int length;
    private int width;

    public Location(Point upperLeft,int length,int width) {
        this.upperLeft = upperLeft;
        this.length = length;
        this.width = width; 
    }
}
