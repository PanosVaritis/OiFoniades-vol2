package org.hua.cookhelp.gui;

import javax.swing.JButton;

public class ExecuteRecipeButton extends JButton {
    String fileName;

    public ExecuteRecipeButton(String fileName){
        super(fileName);
        this.fileName = fileName;
        setVisible(false);
        // addActionListener(e -> showCookFile(fileName));
    }

}
