import java.util.ArrayList;

public class FoodCooked extends Food {
    private ArrayList<FoodIngredients> ingredients;

    public FoodCooked(String mealName) {
        this.name = mealName;
        switch (mealName) {
            case "Chicken Rice":
                setSatiation(16);
                break;
            case "Nut Milk":
                setSatiation(5);
                break;
            case "Curry Rice":
                setSatiation(30);
                break;
            case "Steak":
                setSatiation(22);
                break;
            case "Stir Fry Vegetables":
                setSatiation(5);
                break;
            default:
                mealName = null;
                break;
        }
    }

    //getter 
    public int getSatiation(){
        return satiation; 
    }
}
