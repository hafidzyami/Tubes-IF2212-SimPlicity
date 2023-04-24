public class Clock extends NonFoodItem {
    
    public Clock(){
        super("Clock");
        this.price = 10;
        this.length = 1;
        this.width = 1;
    }

    public int getTime(){
        return 0;
    }

    public static void main(String[] args){
        Clock c = new Clock();
        System.out.println(c.getTime());
    }
}
