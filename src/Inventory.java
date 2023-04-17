import java.util.ArrayList;

public class Inventory {
    private ArrayList <Item> itemList = new ArrayList<>();

    
    public Inventory(Item ... items) {
        for(Item temp : items) {
            itemList.add(temp);
        }
    }
}
