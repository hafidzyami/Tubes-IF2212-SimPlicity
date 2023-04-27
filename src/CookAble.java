import java.util.ArrayList;

public interface CookAble {
    public ArrayList<FoodIngredients> getIngredientsList();
    public CookAble processCook();
}
