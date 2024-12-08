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

    public Map<String, Ingredient> calculateIngredients() {
        for (Recipe r : recipes) {

            for (Map.Entry<String, Ingredient> e : r.getIngredients().entrySet()) {
                String ingredientName = e.getKey();
                Ingredient ingredient = e.getValue();

                if (totalIngredients.containsKey(ingredientName)) {
                    totalIngredients.get(ingredientName).addQuantity(ingredient.getQuantity());
                } else {
                    totalIngredients.put(ingredientName, new Ingredient(ingredient.getName(), ingredient.getQuantity()));
                }
            }
        }

        return totalIngredients;
    }
}


