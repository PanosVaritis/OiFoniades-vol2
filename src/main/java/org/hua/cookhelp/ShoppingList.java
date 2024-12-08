package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {
    private List<Recipe> recipes = new ArrayList<>();
    private Map<String, Ingredient> totalIngredients = new HashMap<>();

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }


    public void calculateIngredients() {
        for (Recipe r : recipes) {

            for (Map.Entry<String, Ingredient> e : r.getIngredients().entrySet()) {
                String ingredientName = e.getKey();
                Ingredient ingredient = e.getValue();

                if (totalIngredients.containsKey(ingredientName)) {
                    totalIngredients.get(ingredientName).addQuantity(ingredient.getQuantity());
                } else {
                    totalIngredients.put(ingredientName, ingredient);
                }
            }
        }
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Υλικά: \n");
        for(Map.Entry<String, Ingredient> entry : totalIngredients.entrySet()){
            sb.append(" ").append(entry.getValue().toString()).append("\n");
        }
        return sb.toString();

    }
}


