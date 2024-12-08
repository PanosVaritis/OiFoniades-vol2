
package org.hua.cookhelp;
//
//import java.io.BufferedReader;

import parser.RecipeReader;

//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;

/**
 *
 * @author panos
 */
public class App {

    public static void main(String[] args) {

        if (args.length == 0){
            throw new IllegalArgumentException ("Command line arguments are nesecasry");
        }
        if (args[0].trim().equals("-list")){
            System.out.println ("Redirected to Shopping List Function: ");

            ShoppingList shoppingList = new ShoppingList();
            RecipeReader recipeReader = new RecipeReader();

            for (int i = 1; i < args.length; i++) {
                String fileContent = recipeReader.readCookFile(args[i]);
                Recipe recipe = new Recipe(args[i], fileContent);
                shoppingList.addRecipe(recipe);
            }

            shoppingList.calculateIngredients();
            System.out.println(shoppingList);

        }else if (args.length == 1 && args[0].endsWith(".cook")){
            System.out.println ("Redirected to Recipe Function:");

            RecipeReader recipeReader = new RecipeReader();
            String fileContent = recipeReader.readCookFile(args[0]);
            Recipe recipe = new Recipe(args[0],fileContent);
            System.out.println(recipe);

        }else if (args.length > 1 && args[0].endsWith(".cook")){
            throw new IllegalArgumentException ("Incorrect arguments");
        }


        // String filePath = "pancakes.cook";

        // RecipeReader recipeReader = new RecipeReader();

        // String fileContent = recipeReader.readCookFile(filePath);

        // Recipe recipe = new Recipe(filePath,fileContent);
        // System.out.println(recipe);


    }
}
