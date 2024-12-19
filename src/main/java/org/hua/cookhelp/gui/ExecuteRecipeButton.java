package org.hua.cookhelp.gui;

import javax.swing.JButton;

import org.hua.cookhelp.Recipe;
import org.hua.cookhelp.parser.RecipeReader;

public class ExecuteRecipeButton extends JButton {
    String fileName;

    public ExecuteRecipeButton(String fileName){
        super(fileName);
        this.fileName = fileName;
        setVisible(false);
        addActionListener(e -> showCookFile(fileName));
    }

    public Recipe showCookFile(String fileName){
        if (fileName != null) {
            RecipeReader recipeReader = new RecipeReader();
            String fileContent = recipeReader.readCookFile(fileName);
            return new Recipe(fileName, fileContent);
        } else {
            System.err.println("File name is not set.");
            return null;
        }
    }
}
