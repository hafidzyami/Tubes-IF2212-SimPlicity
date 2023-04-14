import java.util.ArrayList;
import java.util.Arrays;

public class World {
    private static World instance = new World();
    private ArrayList<ArrayList<String>> map;
    private ArrayList<Home> homeList ;
    private final int LEN_CAPACITY = 64;
    private final int WID_CAPACITY = 64;

    private World() {
        String[] arr = new String[LEN_CAPACITY];
        Arrays.fill(arr, "c");
        ArrayList<String> row = new ArrayList<>(Arrays.asList(arr)) ;
        ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();
        for (int i = 0;i < WID_CAPACITY;i++) {
            matrix.add(row);
        }
        this.map = matrix;
    }

    public void printMap() {
        System.out.println("ini peta");
        for (ArrayList<?> temp : map) {
            System.out.println(temp.toString().replaceAll("[\\[\\]\\,\\ ]", ""));
        }
        System.out.println("ya itu peta");
    }

    public static World getZaWarudo() {
        return instance;
    }

    public void printListRumah() {
        System.out.println("ini list rumah");
    }
    
    public void addRumah(Home Home) {
        homeList.add(Home);
    }

    public void removeRumah(Home Home) {
        homeList.remove(Home);
    }
}
