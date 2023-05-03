import java.util.HashMap;

public class Inventory < T extends Item> {
    private HashMap <T, Integer> itemList = new HashMap<>();
    private int inventoryCount;

    
    public Inventory() {
        itemList.put((T) new NonFoodItem("Gas Stove"), 1);
        itemList.put((T) new NonFoodItem("Single Bed"), 1);
        itemList.put((T) new NonFoodItem("Toilet"), 1);
        itemList.put((T) new NonFoodItem("Table and Chair"), 1);
        itemList.put((T) new Clock(), 1);
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

    public NonFoodItem getOneNFI(int idx){
        int i = 0;
        NonFoodItem temp = null;
        for(T j : getNonFoodItem().keySet()){
            if(i == idx){
                temp = (NonFoodItem) j;
            }
            i++;
        }
        return temp;
    }

    public int getIndeksItem (T items){
        int idx = 0;
        int idxhsl = -1;
        for (T i : itemList.keySet()){
            if(i.getName().equals(items.getName())){
                idxhsl = idx;
            }else{
                idx++;
            }

        }
        return idxhsl;
    }

    public void addInventory(T t){
        if(getIndeksItem(t) != -1){
            Integer temp = itemList.get(getItem(getIndeksItem(t))) + 1;
            itemList.put(getItem(getIndeksItem(t)),temp);
        }
        else{
            itemList.put(t,1);
        }
        inventoryCount++;
    }

    public void deleteInventory(T t){
        if (getIndeksItem(t) != -1){
            Integer temp = itemList.get(getItem(getIndeksItem(t))) - 1;
            itemList.put(getItem(getIndeksItem(t)),temp);
            if(itemList.get(getItem(getIndeksItem(t))) == 0){
                itemList.remove(getItem(getIndeksItem(t)));
            }
            inventoryCount--;
        }
    }

    public HashMap<T,Integer> getNonFoodItem () {
        HashMap<T,Integer> temp = new HashMap <>();
        for(T i : itemList.keySet()){
            if(i instanceof NonFoodItem){
                temp.put(i, temp.get(i));
            }
        }
        return temp;
    }

    public int getNonFoodCount(){
        HashMap<T,Integer> temp = getNonFoodItem();
        return temp.size();
    }

    public HashMap<T,Integer> getFoodItem () {
        HashMap<T,Integer> temp = new HashMap <>();
        for(T i : itemList.keySet()){
            if(i instanceof Food){
                temp.put(i, temp.get(i));
            }
        }
        return temp;
    }

    public void printInventory(HashMap <T,Integer> list){
        int idx = 1;
        for(T i : list.keySet()){
            System.out.println(idx + ". " + i.getName() + " " + itemList.get(i));
            idx++;
        }
    }

    public static void main(String[] args){
        Inventory<Item> x = new Inventory<>();
        FoodIngredients item1 = new FoodIngredients("Rice");
        NonFoodItem item2 = new NonFoodItem("Single Bed");
        x.printInventory(x.getInventory());
        System.out.println(" ");
        x.addInventory(item1);
        x.printInventory(x.getInventory());
        x.addInventory(item2);
        System.out.println(" ");
        x.printInventory(x.getInventory());
        x.addInventory(item2);
        System.out.println(" ");
        x.printInventory(x.getInventory());
        System.out.println(" ");
        x.printInventory(x.getInventory());
        x.addInventory(item2);
        System.out.println(" ");
        x.printInventory(x.getInventory());
        System.out.println(" ");
        x.printInventory(x.getInventory());
        System.out.println(" ");
        HashMap <Item,Integer> cek = x.getFoodItem();
        x.printInventory(cek);
    }
}
