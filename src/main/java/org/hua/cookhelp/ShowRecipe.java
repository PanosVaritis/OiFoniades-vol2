
package org.hua.cookhelp;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author panos
 */

public class ShowRecipe implements Recipe {
    
    
    private List<String> ingredients;
    private List<String> materials;
    private List<String> steps;
    
    public ShowRecipe(){
        ingredients = new ArrayList<>();
        materials = new ArrayList<>();
        steps = new ArrayList<>();
    }

    @Override
    public void analyseRecipe(String[] array) {
        
        if (array.length != 1)
            throw new IllegalArgumentException ("Waited only one file took more!!!");
        
        
        //In this part the file will be read and it's content will be stored in the appropriate array
        //From this part and down we are sure, that the user has given a SINGLE commnad line argument with extension .cook
        
        
    }

    @Override
    public void printRecipe() {
        
        System.out.println ("Ingredients:");
        
        for (int i = 0;i < ingredients.size();i++)
            System.out.println ("\t"+ingredients.get(i));
        
        
        System.out.println ("\nMaterials:");
        
        for (int i = 0;i < materials.size();i++)
            System.out.println ("\t"+materials.get(i));
        
        System.out.println ("\nSteps:");
        
        for (int i = 0;i < steps.size();i++)
            System.out.println ((i+1)+". "+steps.get(i));

    }
    
    
}
