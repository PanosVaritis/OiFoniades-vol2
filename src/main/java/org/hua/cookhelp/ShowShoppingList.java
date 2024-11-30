
package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author panos
 */
public class ShowShoppingList implements Recipe{

    
    private List<String> ingredients;
    
    
    public ShowShoppingList (){
        ingredients = new ArrayList<>();
    }
    
    @Override
    public void readRecipe(String[] array) {
        
        if (array.length == 1)
            throw new IllegalArgumentException ("Waited more parameters took only one");

        for (int i = 1;i < array.length;i++){
            if (!(array[i].endsWith(".cook")))
                throw new IllegalArgumentException ("The sufxix of all the command line parameters must be the same!!");
        }
        
        //From this part and down, we are positive that:
        //A)The first command line argument is the -list
        //B) The user provided more that one command line argument
        //C) Any other command line argument provided has the extension .cook
        
        
    }

    @Override
    public void printRecipe() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
}
