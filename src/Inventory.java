import java.util.ArrayList;

public class Inventory < T extends Item> {
    private ArrayList <T> itemList;
    private int inventoryCount;

    
    public Inventory() {
        itemList = new ArrayList<>();
        itemList.add((T) new NonFoodItem("Single Bed"));
        itemList.add((T) new NonFoodItem("Toilet"));
        itemList.add((T) new NonFoodItem("Gas Stove"));
        itemList.add((T) new NonFoodItem("Table and Chair"));
        itemList.add((T) new NonFoodItem("Clock"));    
        inventoryCount = itemList.size();
    }

    public ArrayList<T> getInventory(){
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
            if(i.getName().equals(items.getName())){
                idxhsl = idx;
            }
            else {
                idx++;
            }
        }
        return idxhsl;
    }

    public void addInventory(T t){
        itemList.add(t);
        inventoryCount++;
    }

    public void deleteInventory(Item items){
        if(getIndeksItem(items) != -1){
            itemList.remove(getIndeksItem(items));
            inventoryCount--;
        }
    }
    public void printInventory(){
        for (int i = 0; i < itemList.size(); i++){
            System.out.println((i+1) + ". " + itemList.get(i).getName());
        }
    }
}
