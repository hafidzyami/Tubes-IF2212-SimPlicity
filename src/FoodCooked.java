import java.util.ArrayList;

public class FoodCooked extends Food implements CookAble {
    private ArrayList<FoodIngredients> ingredients = new ArrayList<>();

    public FoodCooked(String mealName) {
        this.name = mealName;
        switch (mealName) {
            case "Chicken Rice":
                setSatiation(16);
                ingredients.add(new FoodIngredients("Chicken"));
                ingredients.add(new FoodIngredients("Rice"));
                break;
            case "Nut Milk":
                setSatiation(5);
                ingredients.add(new FoodIngredients("Nut"));
                ingredients.add(new FoodIngredients("Milk"));
                break;
            case "Curry Rice":
                setSatiation(30);
                ingredients.add(new FoodIngredients("Rice"));
                ingredients.add(new FoodIngredients("Potato"));
                ingredients.add(new FoodIngredients("Carrot"));
                ingredients.add(new FoodIngredients("Beef"));
                break;
            case "Steak":
                setSatiation(22);
                ingredients.add(new FoodIngredients("Potato"));
                ingredients.add(new FoodIngredients("Beef"));
                break;
            case "Stir Fry Vegetables":
                setSatiation(5);
                ingredients.add(new FoodIngredients("Carrot"));
                ingredients.add(new FoodIngredients("Spinach"));
                break;
            default:
                mealName = null;
                ingredients.clear();
                break;
        }
    }

    public ArrayList<FoodIngredients> getIngredientsList() {
        return ingredients;
    }
}
