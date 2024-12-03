package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<Recipe> recipes = new ArrayList<>();


    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public double calculateIngredients(){
        double totalQuantity = 0.0;
        for( Recipe r : this.recipes ){
            totalQuantity =+ r.calculateIngredients();
        }
        return totalQuantity;
    }

}
