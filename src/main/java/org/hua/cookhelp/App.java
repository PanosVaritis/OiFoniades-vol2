
package org.hua.cookhelp;

/**
 *
 * @author panos
 */
public class App {

    public static void main(String[] args) {
        System.out.println ("Welcome to the cook helping machine!!!");
        
        
        if (args.length == 0)
            throw new IllegalArgumentException ("Command line parameters are nesecasry");
         
        
        if (args[0].trim().equals("-list")){
            
            System.out.println ("Very good. No you will be redirected to class ShowShoppingList!!!");
            Recipe s = new  ShowShoppingList();
            s.analyseRecipe(args);
        
        }else if (args[0].endsWith(".cook")){
            
            System.out.println ("Good you will now be redirected to class ShowRecipe");
            Recipe s = new ShowRecipe();
            s.analyseRecipe(args);
        
        }else {
            
            throw new IllegalArgumentException ("Incorrect command line arguments were passed.....");
        
        }
        
        
        
        //for testing purposes
        for (int i = 0;i <args.length;i++){
            System.out.println (args[i]);
        }
        
        
        
    }
}
