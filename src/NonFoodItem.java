import java.awt.Point;


public class NonFoodItem extends Item implements PurchaseAble {
    // atribut Item
    // private String relatedAction;

    // atribut 
    protected String name;
    protected Point upperLeft;
    protected int length;
    protected int width;
    protected int price;
    protected boolean isAvailable;


    // konstruktor
    public NonFoodItem(String name){
        this.name = name;
        this.upperLeft = new Point(0, 0);
        // pada dasarnya NonFoodItem bersifat available
        this.isAvailable = true;

        // check berdasarkan nama item
        if(name.equals("Single Bed")){
            this.price = 50;
            this.length = 4;
            this.width = 1;
        }
        else if(name.equals("Queen Size Bed")){
            this.price = 100;
            this.length = 4;
            this.width = 2;
        }
        else if(name.equals("King Size Bed")){
            this.price = 150;
            this.length = 5;
            this.width = 2;
        }
        else if(name.equals("Toilet")){
            this.price = 50;
            this.length = 1;
            this.width = 1;
        }
        else if(name.equals("Gas Stove")){
            this.price = 100;
            this.length = 2;
            this.width = 1;
        }
        else if(name.equals("Electric Stove")){
            this.price = 200;
            this.length = 1;
            this.width = 1;
        }
        else if(name.equals("Table and Chair")){
            this.price = 50;
            this.length = 3;
            this.width = 3;
        }
    }

    // getter
    public String getName(){
        return name;
    }

    public Point getUpperLeft(){
        return upperLeft;
    }

    public int getLength(){
        return length;
    }

    public int getWidth(){
        return width;
    }

    public int getPrice(){
        return price;
    }

    public boolean getStatus(){
        return isAvailable;
    }

    // setter
    public void setStatus(boolean status){
        this.isAvailable = status;
    }

    public static void main(String[] args){
        NonFoodItem sb = new NonFoodItem("Single Bed");
        System.out.println("name :" + sb.name);
        System.out.println("upperLeft :" + sb.upperLeft);
        System.out.println("price :" + sb.price);
        System.out.println("length :" + sb.length);
        System.out.println("width :" + sb.width);
        System.out.println("isAvailable :" + sb.isAvailable);
    }



}
