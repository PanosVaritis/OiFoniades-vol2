
package org.hua.cookhelp;

/**
 *
 * @author panos
 */
public class App {

    public static void main(String[] args) {
        System.out.println ("Welcome to the cook helping machine!!!\n");
        
        
        if (args.length == 0)
            throw new IllegalArgumentException ("Command line parameters are nesecasry");
         
        
        if (args[0].trim().equals("-list")){
            
            Recipe s = new  ShowShoppingList();
            s.readRecipe(args);
        
        }else if (args[0].endsWith(".cook")){
            
            Recipe s = new ShowRecipe();
            s.readRecipe(args);
            s.printRecipe();
        
        }else {
            
            throw new IllegalArgumentException ("Incorrect command line arguments were passed.....");
        
        }
        
        
        
        
    }
}
