
package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author panos
 */

public class ShowRecipe implements Recipe {
    
    
    private List<String> ingredients;
    private List<String> tools;
    private List<String> steps;
    private List<String> time;
    
    
    public ShowRecipe(){
        ingredients = new ArrayList<>();
        tools = new ArrayList<>();
        steps = new ArrayList<>();
        time = new ArrayList<>();
    }


    @Override
    public void printRecipe() {
        
        System.out.println ("Ingredients:");
        
        for (int i = 0;i < ingredients.size();i++)
            System.out.println ("  "+ingredients.get(i));
        
        
        System.out.println ("\nCooking tools:");
        
        for (int i = 0;i < tools.size();i++)
            System.out.println ("  "+tools.get(i));
        
        convertToMinutes();
        System.out.println ("\nTotal time:\n  "+calculateTotalTime()+" minutes\n");
        
//        concatSteps();
//        System.out.println ("Steps: ");
//        for (int i = 0;i < steps.size();i++)
//            System.out.println ((i+1)+". "+steps.get(i));
        
    }
    
    @Override
    public void readRecipe(String[] array){
        
        if (array.length != 1)
            throw new IllegalArgumentException ("Waited only one file took more!!!");
        
          //In this part the file will be read and it's content will be stored in the appropriate array
        //From this part and down we are sure, that the user has given a SINGLE commnad line argument with extension .cook
        
          
        try (BufferedReader br = new BufferedReader(new FileReader(array[0]))){
            String line;
            while ((line = br.readLine()) != null){
                
                steps.add(line);
                
                analyseIngredient(line);

                analyseTime(line);

                analyseCookTool(line);
            }
        
        }catch (IOException e){
        } 

        
    }
    
    
    private void analyseIngredient (String line){
     
          String regexForIngredients = "@([a-zA-Zά-ώΑ-Ω\\s]+)(\\{(\\d+)?(%[a-zA-Zά-ώΑ-Ω\\s]+)?\\})?";
          Pattern patternForIngredients = Pattern.compile(regexForIngredients);
          
          Matcher matcherIng = patternForIngredients.matcher(line);
          
          while (matcherIng.find()){
               fixIngredients(matcherIng.group());
          }
        
    }
    
    
    private void analyseTime (String line){
        
        String regexForTime = "~\\{\\d+(\\.\\d+)?%[a-zA-Zά-ώΑ-Ω]+\\}";
        Pattern patternForTime = Pattern.compile(regexForTime);

        Matcher matcherTime = patternForTime.matcher(line);

        while (matcherTime.find()){
            fixTime(matcherTime.group());

        }
    
    }
    
    
    
    private void analyseCookTool(String line){
        
        String regexForTool = "#([α-ωΑ-Ωa-zA-Zά-ώ]+(?: [α-ωΑ-Ωa-zA-Zά-ώ]+)*)(?:\\{\\})?";

        Pattern patternForTool  = Pattern.compile(regexForTool);
        
        Matcher matcherTool = patternForTool.matcher(line);
        
        while (matcherTool.find()){
            fixCookTool(matcherTool.group());
            
        }

    }


    private void fixIngredients (String match){


        String regex1 = "^(@\\S+)(\\s.*)?$";
        
        Pattern newPattern = Pattern.compile(regex1);
       
           if (!(match.contains("{") && match.contains("}"))){
               
               Matcher newMatcher = newPattern.matcher(match);
               if (newMatcher.find())
                   match = newMatcher.group(1);
           }
         
           
            match = match.substring(1);
           
            if (match.contains("{") && match.contains("}")){
                match = match.replace("{", " ");
                match = match.replace("}", " ");
                match = match.replace("%", " ");
            }
            
            ingredients.add(match);
       }


    private void fixTime (String match){
        
        match = match.substring(1);
        match = match.replace("{", " ");
        match = match.replace("}", " ");
        match = match.replace("%", " ");
        
        time.add(match);
    }
    
    
    private void fixCookTool(String match){
        
        if (!(match.contains("{") && match.contains("}")))
            match = match.split(" ")[0];
        
        match  = match.substring(1);
        
        if (match.contains("{}")){
            match = match.replace("{}", " ");
        }
            
        tools.add(match);
    }
    
    
    
    private double calculateTotalTime (){
        
        for (int i = 0;i < time.size();i++){
             time.set(i, time.get(i).replace(" minutes",""));
            }
        
        
        
        double total = 0.0;
        for (int i = 0;i < time.size();i++)
            total += Double.parseDouble(time.get(i));


        return total;
    }
    
   
    private void convertToMinutes(){
        
        for (int i = 0;i < time.size();i++){
            String item = time.get(i);
            
            if (time.contains("hours")){
                String newTime = item.replace(" hours", "").trim();
                double hours = Double.parseDouble(newTime);
                
                int minutes = (int)(hours * 60);
                
                time.set(i, minutes + " minutes");
            }
        }
        
    }
    
    private void concatSteps(){
        StringBuilder concat = new StringBuilder();
        
        for (int i = 0;i < steps.size();i++){
            String str = steps.get(i);
            
            if (str.isEmpty()){
                if (concat.length() > 0){
                    steps.set(i - 1, concat.toString());
                    concat.setLength(0);
                }
            }else {
                if (concat.length() > 0){
                    concat.append(" ");
                }
                concat.append(str);
            }
        }
        if (concat.length() > 0)
            steps.set(steps.size() - 1, concat.toString());
        
    }


    
}


 