
package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author panos
 */

public class Recipe {
    private String name;
    private String description;
    private Set<CookingTool> cookingTools = new HashSet<>();
    private Map<String, Ingredient> ingredients = new HashMap<>();
    private List<Step> steps = new ArrayList<>();


    public Recipe(String name,String description){
            this.name = name;
            parseRecipe(description);
    }

    private void addIngredient(Ingredient ingredient){
        if (ingredients.containsKey(ingredient.getName())){
            ingredients.get(ingredient.getName()).addQuantity(ingredient.getQuantity());
        }else {
            ingredients.put(ingredient.getName(), ingredient);
        }
    }

    private void addStep(Step step){
        steps.add(step);
    }

    private void addCookingTool(CookingTool cookingTool){
        cookingTools.add(cookingTool);
    }

    public double calculateTotalTimeDuration(){
        double totalTimeDuration = 0;
        for(Step s : steps){
            double timeDuration = s.getTimeDuration();
            totalTimeDuration += timeDuration;
        }
        return totalTimeDuration;
    }

    private void parseRecipe(String description) {
        extractIngredients(description);
        extractCookingTools(description);
        extractStep(description);
    }

    private void extractIngredients(String description) {
        Pattern ingredientPattern = Pattern.compile("@([^\\{\\s]+)(\\{([^%\\}]+)%?([^\\}]*)\\})?");
        Matcher matcher = ingredientPattern.matcher(description);

        while (matcher.find()) {
            String name = matcher.group(1);
            String quantityStr = matcher.group(3);
            String unit = matcher.group(4);

            double quantity = 0;
            if (quantityStr != null) {
                try {
                    quantity = Double.parseDouble(quantityStr);
                } catch (NumberFormatException e) {
                    // Χειρισμός εξαίρεσης
                }
            }

            Ingredient ingredient = new Ingredient(name, quantity, unit);
            addIngredient(ingredient);
        }
    }

    private void extractCookingTools(String description) {
        Pattern cookingToolPattern = Pattern.compile("#([^\\{\\s]+)(\\{[^\\}]*\\})?");
        Matcher matcher = cookingToolPattern.matcher(description);
    
        while (matcher.find()) {
            String name = matcher.group(1);
            CookingTool cookingTool = new CookingTool(name);
            addCookingTool(cookingTool);
        }
    }
    private void extractStep(String description) {
        String[] steps = description.split("\\n\\s*\\n");
        for(String s : steps ){
            Pattern timePattern = Pattern.compile("~\\{([^%\\}]+)%([^\\}]+)\\}");
            Matcher matcher = timePattern.matcher(s);

            while (matcher.find()) {
                String timeStr = matcher.group(1);
                String timeUnit = matcher.group(2);
            
                try {
                    double timeDuration = Double.parseDouble(timeStr);
                    Step step = new Step(s, timeDuration, timeUnit);
                    addStep(step);
                } catch (NumberFormatException e) {
                    // Χειρισμός εξαίρεσης
                }
            }
        }
    }

    public String getName(){
        return name;
    }

    public Set<CookingTool> getCookingTools() {
        return Collections.unmodifiableSet(cookingTools);
    }

    public HashMap<String, Ingredient> getIngredients() {
        return (HashMap<String, Ingredient>) Collections.unmodifiableMap(ingredients);
    }

    public List<Step> getTotalTime() {
        return Collections.unmodifiableList(steps);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Υλικά: \n");
        for(Map.Entry<String, Ingredient> entry : ingredients.entrySet()){
            sb.append(" ").append(entry.getValue().toString()).append("\n");
        }
        sb.append("Σκεύη: \n");
        for(CookingTool c : cookingTools){
            sb.append(" ").append(c.toString()).append("\n");
        }
        sb.append("Συνολική ώρα: \n");
        sb.append(" ").append(calculateTotalTimeDuration()).append("\n");

        sb.append("Βήματα: \n");
        int counter = 1;
        for(Step s : steps){
            sb.append(" ").append(counter).append(". ").append(s.toString()).append("\n");
            counter ++;
        }
        return sb.toString();
    }
}
