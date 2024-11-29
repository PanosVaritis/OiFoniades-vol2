package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private List<Recipe> recipes;

    public ShoppingList(List<Recipe> recipes){
        this.recipes = new ArrayList<>();
        
        for(Recipe r : recipes){
            addRecipe(r);
        }
    }

    public void addRecipe(Recipe recipe){
        this.recipes.add(recipe);
    }

    public void calculateIngredients(){
       for( Recipe r : this.recipes ){
        r.getIngredients();
        //TODO
       }
    }

}
