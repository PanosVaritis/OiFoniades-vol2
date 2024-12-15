
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
    private Set<CookingTool> cookingToolList = new HashSet<>();
    private Map<String, Ingredient> ingredientList = new HashMap<>();
    private List<Step> stepList = new ArrayList<>();

    public Recipe(String name,String description){
            this.name = name;
            parseRecipe(description);
    }

    private void addIngredient(Ingredient ingredient){
        ingredient.convertMeasurements();
        if (ingredientList.containsKey(ingredient.getName())){
            ingredientList.get(ingredient.getName()).addQuantity(ingredient.getQuantity(), ingredient.getUnit());
        }else{
            ingredientList.put(ingredient.getName(), ingredient);
            if (!ingredient.isStandardUnit(ingredient.getUnit())) {
                ingredient.addOddUnits(ingredient.getQuantity(), ingredient.getUnit());
                ingredient.setQuantity(0);
                ingredient.setUnit("");
            }
        }
    }


    private void addStep(Step step){
        stepList.add(step);
    }

    private void addCookingTool(CookingTool cookingTool){
        cookingToolList.add(cookingTool);
    }

    public double calculateTotalTimeDuration(){
        double totalTimeDuration = 0;
        for(Step s : stepList){
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
        Pattern ingredientPattern = Pattern.compile("@([^\\\\{]+)(\\{([^%\\}]+)%?([^\\}]*)\\})?");
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
            }else{
                quantity = 1;
            }

            Ingredient ingredient;
            if (unit == null || unit.isEmpty()) {
                ingredient = new Ingredient(name, quantity);
            } else {
                ingredient = new Ingredient(name, quantity, unit);
            }
            addIngredient(ingredient);
        }
    }

    private void extractCookingTools(String description) {
        Pattern cookingToolPattern = Pattern.compile("#([α-ωΑ-Ωa-zA-Zά-ώ]+(?: [α-ωΑ-Ωa-zA-Zά-ώ]+)*)(?:\\{\\})?");
        Matcher matcher = cookingToolPattern.matcher(description);
    
        while (matcher.find()) {
            fixCookingTool(matcher.group());
        }
    }
    private void extractStep(String description) {
        String[] steps = description.split("\\n\\s*\\n");
        for(String s : steps ){
            Pattern timePattern = Pattern.compile("~\\{([^%\\}]+)%([^\\}]+)\\}");
            Matcher matcher = timePattern.matcher(s);

            if (matcher.find()) {
                String timeStr = matcher.group(1);
                String timeUnit = matcher.group(2);
            
                try {
                    double timeDuration = Double.parseDouble(timeStr);
                    Step step = new Step(s, timeDuration, timeUnit);
                    addStep(step);
                } catch (NumberFormatException e) {
                    // Χειρισμός εξαίρεσης
                }
            }else{
                Step step = new Step(s);
                addStep(step);
            }
        }
    }


    public String getName(){
        return name;
    }

    public Set<CookingTool> getCookingToolList() {
        return Collections.unmodifiableSet(cookingToolList);
    }

    public Map<String, Ingredient> getIngredientList() {
        return ingredientList;
    }

    public List<Step> getStepList() {
        return Collections.unmodifiableList(stepList);
    }

    private void fixCookingTool (String match){
        if (!(match.contains("{") && match.contains("}")))
            match = match.split(" ")[0];
            
        match  = match.substring(1);
        if (match.contains("{}")){
            match = match.replace("{}", " ");
        }
        CookingTool cookingTool = new CookingTool(match);
            addCookingTool(cookingTool);
    }

    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Υλικά: \n");
        for(Map.Entry<String, Ingredient> entry : ingredientList.entrySet()){
            sb.append(" ").append(entry.getValue().toString()).append("\n");
        }
        sb.append("\n");
        sb.append("Σκεύη: \n");
        for(CookingTool c : cookingToolList){
            sb.append(" ").append(c.toString()).append("\n");
        }
        sb.append("\n");

        sb.append("Συνολική ώρα: \n");
        double totalTimeDuration = calculateTotalTimeDuration();
        if (totalTimeDuration == (int) totalTimeDuration) {
            sb.append(" ").append((int) totalTimeDuration).append(" min").append("\n");
        } else {
            sb.append(" ").append(totalTimeDuration).append(" min").append("\n");
        }   
        sb.append("\n");
        
        sb.append("Βήματα: \n");
        int counter = 1;
        for(Step s : stepList){
            sb.append(" ").append(counter).append(". ").append(s.toString()).append("\n").append("\n");
            counter ++;
        }
        return sb.toString();
    }
    
    
    
}
