package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<Recipe> recipes;

    public ShoppingList(){
        recipes = new ArrayList<>();
    }

    public void addRecipe(Recipe recipe){
        recipes.add(recipe);
    }

    public void calculateIngredients(){
       //TODO
    }

}
