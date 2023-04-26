import java.util.ArrayList;

public class Inventory {
    private ArrayList <Item> itemList;
    private int inventoryCount;

    
    public Inventory() {
        itemList = new ArrayList<>();
        inventoryCount = 0;
    }

    public ArrayList<Item> getInventory(){
        return itemList;
    }

    public int getInventoryCount(){
        return inventoryCount;
    }

    public Item getItem (int i){
        return itemList.get(i);
    }

    public int getIndeksItem(Item items){
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
        return idxhsl;
    }

    public void addInventory(Item items){
        itemList.add(items);
    }

    public void addInventory(PurchaseAble items){
        itemList.add((Item) items);
    }

    public void deleteInventory(Item items){
        if(getIndeksItem(items) != -1){
            itemList.remove(getIndeksItem(items));
        }
    }
    public void printInventory(){
        for (Item i :itemList){
            System.out.println(i.name);
        }
    }
}
