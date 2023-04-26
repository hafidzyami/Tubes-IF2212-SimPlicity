public class FoodIngredients extends Food {
    private int price;
    public String name;

    public FoodIngredients(String name) {
        this.name = name;
        switch (name) {
            case "Rice":
                setSatiation(5);
                this.price = 5;
                break;
            case "Potato":
                setSatiation(4);
                this.price = 3;
                break;
            case "Chicken":
                setSatiation(8);
                this.price = 10;  
                break;
            case "Beef":
                setSatiation(15);
                this.price = 12;
                break;
            case "Carrot":
                setSatiation(2);
                this.price = 3;
                break;
            case "Spinach":
                setSatiation(2);
                this.price = 3;
                break;
            case "Nut":
                setSatiation(2);
                this.price = 2;
                break;
            case "Milk":
                setSatiation(1);
                this.price = 2;
                break;
                
            default:
                this.name = null;
                break;
        }
    }

    public int getPrice(String name){
        switch (name) {
            case "Rice":
                return 5;
            case "Potato":
                return 3;
            case "Chicken":
                return 10;
            case "Beef":
                return 12;
            case "Carrot":
                return 3;
            case "Spinach":
                return 3;
            case "Nut":
                return 2;
            case "Milk":
                return 2;
                
            default:
                this.name = null;
                return 0;
        }
    }

}
