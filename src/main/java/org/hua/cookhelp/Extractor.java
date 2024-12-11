package org.hua.cookhelp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author panos
 */

public class Extractor {

    private static String regexForIngredients = "@([a-zA-Zά-ώΑ-Ω\\s]+)(\\{(\\d+)?(%[a-zA-Zά-ώΑ-Ω\\s]+)?\\})?";
    
    private  static String regexForTime = "~\\{\\d+(\\.\\d+)?%[a-zA-Zά-ώΑ-Ω]+\\}";
    
    private Pattern patternForIngredients;
    
    private Pattern patternForTime;
    
    public Extractor (){
        this.patternForIngredients  = Pattern.compile(regexForIngredients);
        this.patternForTime = Pattern.compile(regexForTime);
    }
    
    
    
    public void readFile (String[] args){
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String line;
            while ((line = br.readLine()) != null){
                
                Matcher matcherIng = this.patternForIngredients.matcher(line);
                
                Matcher matcherTime = this.patternForTime.matcher(line);
                
//                System.out.println ("Line that is been read");
//                System.out.println (line);
//                
//                System.out.println ("Ingredients found!!");
                while(matcherIng.find()){
                    fixIngredients(matcherIng.group());
//                    System.out.println ("Found: "+matcherIng.group());
                }
                
//                System.out.println ("Time found!!");
                while (matcherTime.find()){
                    fixTime (matcherTime.group());
//                    System.out.println ("Found: "+matcherTime.group());
                }
                
            }
        
        }catch (IOException e){
        } 

    }
    
    
    
    private void fixIngredients(String match){


//      First i correct the form of the ingredients 
        
        String regex1 = "^(@\\S+)(\\s.*)?$";
        
        Pattern newPattern = Pattern.compile(regex1);
       
           if (!(match.contains("{") && match.contains("}"))){
               
               Matcher newMatcher = newPattern.matcher(match);
               if (newMatcher.find())
                    match = newMatcher.group(1);
           }
           
           //Here we remove the @ from the beggining
           match = match.substring(1);
       }
        
    
    
    private void fixTime(String match){
        
    }
    
    
    private void fixCookingTool (String match){
        
    }
}

//        /**
//         * In case that the text has no . and it changes lines a problem will occur and some ingredients
//         * will be stored in more that one line, So with this loop i makes sure that each string of the lsit 
//         * requires only one line
//         */
//        for (int i = 0;i < list.size();i++){
//            list.set(i, list.get(i).replace("\n", " "));
//        }
//        
//        //Here only for testing purspose's i present the ingredients with the form that they were matched from the text
//        System.out.println ("\nIngredients as shown inside the list: (after we combine the lines)");
//        for (int i = 0;i < list.size();i++){
//            System.out.println ((i+1)+". "+list.get(i));
//            
//        }
////        
//        for (int i = 0;i < list.size();i++){
//            list.set(i, list.get(i).substring(1));
//            list.set(i, list.get(i).replace("{"," "));
//            list.set(i, list.get(i).replace("}"," "));
//            list.set(i, list.get(i).replace("%", " "));
//            
//        }
//        