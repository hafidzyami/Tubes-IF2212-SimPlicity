public class FoodIngredients extends Food {
    private int price;
    private String name;

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
}
