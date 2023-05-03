public class Clock extends NonFoodItem {
    
    public Clock(){
        super("Clock");
        this.price = 10;
        this.length = 1;
        this.width = 1;
    }

    public int getTime(World world){
        int time = world.getWorldClock().getTotalElapsed();
        return time;
    }

    public static void main(String[] args){
        PurchaseAble c = new Clock();
        System.out.println(c.getName());
    }
}
