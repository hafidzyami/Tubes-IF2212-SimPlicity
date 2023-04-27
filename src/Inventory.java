import java.util.ArrayList;

public class Inventory {
    private ArrayList <Item> itemList;
    private int inventoryCount;

    
    public Inventory() {
        itemList = new ArrayList<>();
        itemList.add(new NonFoodItem("Single Bed"));
        itemList.add(new NonFoodItem("Toilet"));
        itemList.add(new NonFoodItem("Gas Stove"));
        itemList.add(new NonFoodItem("Table and Chair"));
        itemList.add(new NonFoodItem("Clock"));    
        inventoryCount = itemList.size();
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
            if(i.getName().equals(items.getName())){
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
        inventoryCount++;
    }

    public void addInventory(PurchaseAble items){
        itemList.add((Item) items);
        inventoryCount++;
    }

    public void deleteInventory(Item items){
        if(getIndeksItem(items) != -1){
            itemList.remove(getIndeksItem(items));
        }
        inventoryCount--;
    }
    public void printInventory(){
        for (Item temp :itemList){
            System.out.println(temp.name);
        }
    }
}
