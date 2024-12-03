
package org.hua.cookhelp;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author panos
 */

public class Recipe {
    private String name;
    private Set<CookingTool> cookingTools = new HashSet<>();
    private Map<String, Ingredient> ingredients = new HashMap<>();


    public Recipe(String name){
            this.name = name;
    }

    public void addIngredient(Ingredient ingredient){
        if (ingredients.containsKey(ingredient.getName())){
            ingredients.get(ingredient.getName()).addQuantity(ingredient.getQuantity());
        }else {
            ingredients.put(ingredient.getName(), ingredient);
        }
    }

    public void addCookingTool(CookingTool cookingTool){
        cookingTools.add(cookingTool);
    }


    public String getName(){
        return name;
    }

    public double calculateIngredients(){
        double totalQuantity = 0.0;
        for( Ingredient i : this.ingredients.values()){
            totalQuantity =+ i.getQuantity();
        }
        return totalQuantity;
    } 

    public Set<CookingTool> getCookingTools() {
        return Collections.unmodifiableSet(cookingTools);
    }

    public HashMap<String, Ingredient> getIngredients() {
        return (HashMap<String, Ingredient>) Collections.unmodifiableMap(ingredients);
    }

}
