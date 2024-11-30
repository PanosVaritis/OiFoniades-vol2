package org.hua.cookhelp;

/**
 * A simple interface that will be implemented by 2 classes
 *
 * @author panos
 */
public interface Recipe {

    /**
     * Takes the array of parameters - files and search for patterns in them
     *
     * @param array
     */
    void readRecipe(String[] array);

    /**
     * Takes the arrays with their content and prints them
     */
    void printRecipe();

}
