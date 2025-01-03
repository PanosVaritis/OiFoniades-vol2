package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {
    private List<Recipe> recipeList = new ArrayList<>();
    private Map<String, Ingredient> totalIngredients = new HashMap<>();

    public void addRecipe(Recipe recipe){
        recipeList.add(recipe);
    }

    //loop over all Ingredients of each recipe if totalIngredients contains the key sum it else add it to the map
    public void calculateIngredients() {
        for (Recipe r : recipeList) {
            for (Map.Entry<String, Ingredient> entry : r.getIngredientList().entrySet()) {
                String ingredientName = entry.getKey();
                Ingredient ingredient = entry.getValue();
                if (totalIngredients.containsKey(ingredientName)) {
                    totalIngredients.get(ingredientName).addQuantity(ingredient.getQuantity(),ingredient.getUnit());
                    //calculates each Ingredient oddUnit HashMap and merges if it exists
                    for (Map.Entry<String, Double> oddUnitEntry : ingredient.getOddUnits().entrySet()) {
                        totalIngredients.get(ingredientName).addOddUnits(oddUnitEntry.getValue(), oddUnitEntry.getKey());
                    }
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


