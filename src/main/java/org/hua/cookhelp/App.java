
package org.hua.cookhelp;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

import parser.RecipeReader;

/**
 *
 * @author panos
 */
public class App {

    public static void main(String[] args) {


        String filePath = "pancakes.cook";

        RecipeReader recipeReader = new RecipeReader();

        String fileContent = recipeReader.readCookFile(filePath);
        System.out.println(fileContent);

        Recipe recipe = new Recipe(filePath,fileContent);
        System.out.println(recipe);
        
        // if (args.length == 0)
        //     throw new IllegalArgumentException ("Command line parameters are nesecasry");

        // if (args[0].trim().equals("-list")){

        //     System.out.println ("Very good. No you will be redirected to class ShowShoppingList!!!");
        

        // }else if (args[0].endsWith(".cook")){

        //     System.out.println ("Good you will now be redirected to class ShowRecipe");
            
        //     Extractor ex = new Extractor();
        //     ex.readFile(args);
        // }else {

        //     throw new IllegalArgumentException ("Incorrect command line arguments were passed.....");

        // }

    }
}

//        ArrayList<String[]> matches = new ArrayList<>();
//        String filePath = args[0];
//        
//        List<Recipe> recipes = new ArrayList<>();
//
//        
//        
//
//        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
//            String regex = "@([\\w\\s]+)\\{(\\d+(\\.\\d+)?)(?:%(\\w+))?\\}";
//            Pattern pattern = Pattern.compile(regex);
//
//            int matchCount = 0;
//            String line;
//            while ((line = reader.readLine()) != null) {
//                Matcher matcher = pattern.matcher(line);
//                while (matcher.find()) {
//                    String name = matcher.group(1);
//                    String quantity = matcher.group(2);
//                    String unit = matcher.group(4);
//                    matches.add(new String[]{name, quantity, unit});
//
//                    System.out.println("Name: " + name + ", Quantity: " + quantity + ", Unit: " + unit);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("Total Matches Found: " + matches.size());
//    