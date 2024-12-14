
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
            throw new IllegalArgumentException ("Command line arguments are necessary");
        }
        if (args[0].trim().equals("-list")){
            
            if (args.length == 2)
                throw new IllegalArgumentException ("To the run the -list you must provide more that one files");


            for (int i = 1;i < args.length;i++){
                if (!(args[i].endsWith(".cook")))
                    throw new IllegalArgumentException ("The suffix of all the command line parameters must be the same!!");
            }
            
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

            RecipeReader recipeReader = new RecipeReader();
            String fileContent = recipeReader.readCookFile(args[0]);
            Recipe recipe = new Recipe(args[0],fileContent);
            System.out.println(recipe);

        }else if (args.length > 1 && args[0].endsWith(".cook")){
            throw new IllegalArgumentException ("Too many arguments");
        }else{
            throw new IllegalArgumentException ("Incorrect arguments");
        }
    }
}
