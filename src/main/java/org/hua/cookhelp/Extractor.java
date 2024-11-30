
package org.hua.cookhelp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


/**
 *
 * @author panos
 */
public class Extractor {

    private String regexForIngredients = "";
    
    private String regexForTime = "";
    
    public void readFile (String[] args){
        
        
        try (BufferedReader br = new BufferedReader(new FileReader(args[0]))){
            String line;
            while ((line = br.readLine()) != null){
                System.out.println (line);
            }
        
        }catch (IOException e){
            e.printStackTrace();
        } 

    }
    
}
