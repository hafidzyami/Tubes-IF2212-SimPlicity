import java.util.HashMap;

public class Inventory < T extends Item> {
    private HashMap <T, Integer> itemList = new HashMap<>();
    private int inventoryCount;

    
    public Inventory() {
        itemList.put((T) new NonFoodItem("Gas Stove"), 1);
        itemList.put((T) new NonFoodItem("Single Bed"), 1);
        itemList.put((T) new NonFoodItem("Toilet"), 1);
        itemList.put((T) new NonFoodItem("Table and Chair"), 1);
        itemList.put((T) new NonFoodItem("Clock"), 1);    
        inventoryCount = itemList.size();
    }

    public HashMap <T, Integer> getInventory(){
        return itemList;
    }

    public int getInventoryCount(){
        return inventoryCount;
    }

    public T getItem (int idx){
        int i = 0;
        T temp = null;
        for (T j : itemList.keySet()){
            if(i == idx){
                temp = j;
            }
            i++;
        }
        return temp;
    }

    public boolean checkItem(T items){
        boolean ada = false;
        for (T i : itemList.keySet()){
            if(i.getName().equals(items.getName())){
                ada = true;
            }
        }
        return ada;
    }

    public void addInventory(T t){
        if(checkItem(t)){
            T temp = t;
            itemList.put(temp,itemList.get(t)+1);
        }
        else{
            itemList.put(t,1);
        }
        inventoryCount++;
    }

    public void deleteInventory(T t){
        if (checkItem(t)){
            T temp = t;
            itemList.put(temp,itemList.get(t)-1);
            if(itemList.get(t) == 0){
                itemList.remove(t);
            }
            inventoryCount--;
        }
    }
    public void printInventory(){
        for(T i : itemList.keySet()){
            System.out.println(i.getName());
        }
    }

    public static void main(String[] args){
        Inventory<Item> x = new Inventory<>();
        x.printInventory();
        x.addInventory(new NonFoodItem("Toilet"));
        x.printInventory();
    }
}
