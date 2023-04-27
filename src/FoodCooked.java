import java.util.ArrayList;

public class FoodCooked extends Food implements CookAble {
    private ArrayList<FoodIngredients> ingredients;
    public String mealName;

    public FoodCooked(String mealName) {
        this.mealName = mealName;
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

    public ArrayList<FoodIngredients> getIngredientsList() {
        return ingredients;
    }
}
