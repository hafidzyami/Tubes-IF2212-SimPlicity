import java.util.ArrayList;

public class Inventory {
    private ArrayList <Item> itemList;
    private int inventoryCount;

    
    public Inventory() {
        itemList = new ArrayList<>();
        inventoryCount = 0;
    }

    public ArrayList getInventory(){
        return itemList;
    }

    public int getInventoryCount(){
        return inventoryCount;
    }

    public void addInventory(Item items){
        itemList.add(items);
    }

    public void deleteInventory(Item items){
        int idx = 0;
        int idxhsl = -1;
        for (Item i : itemList){
            if(i.name.equals(items.name)){
                idxhsl = idx;
            }
            else {
                idx++;
            }
        }
        if (idxhsl != -1){
            itemList.remove(idxhsl);
        }
    }
    public void printInventory(){
        for (Item i :itemList){
            System.out.prinln(i.name);
        }
    }
}
