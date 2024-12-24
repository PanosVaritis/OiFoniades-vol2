package org.hua.cookhelp.gui;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;

import org.hua.cookhelp.Recipe;
import org.hua.cookhelp.ShoppingList;
import org.hua.cookhelp.parser.RecipeReader;

public class ShoppingListButton extends JButton{

    public ShoppingListButton(String fileName,DefaultListModel<String> fileListModel){
        super(fileName);
        setVisible(false);
        addActionListener(e -> displayShoppingList(fileListModel));
    }

    private ShoppingList createShoppingList(DefaultListModel<String> fileListModel){
        ShoppingList shoppingList = new ShoppingList();
        RecipeReader recipeReader = new RecipeReader();

        for (int i = 0; i < fileListModel.getSize(); i++) {
            String fileContent = recipeReader.readCookFile(fileListModel.get(i));
            Recipe recipe = new Recipe(fileListModel.get(i), fileContent);
            shoppingList.addRecipe(recipe);
        }

        shoppingList.calculateIngredients();
        return shoppingList;
    }

    private void displayShoppingList(DefaultListModel<String> fileListModel) {
        ShoppingList shoppingList = createShoppingList(fileListModel);
        JFrame shoppingListWindow = new JFrame("Shopping List");
        shoppingListWindow.setSize(400, 300);
        shoppingListWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel shoppingListLabel = new JLabel();
        shoppingListLabel.setVerticalAlignment(JLabel.TOP);
        shoppingListLabel.setHorizontalAlignment(JLabel.LEFT);
        shoppingListLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        StringBuilder contentBuilder = new StringBuilder();
        if (shoppingList != null) {
            contentBuilder.append("<html><body style='padding:10px; font-size:12px;'>");
            contentBuilder.append("Recipes: <b><br>");
            for (int i = 0; i < fileListModel.size(); i++) {
                String recipeName = fileListModel.get(i);
                contentBuilder.append(recipeName).append("</b><br>");
            }

            String formattedRecipe = shoppingList.toString().replace("\n", "<br>");
            contentBuilder.append("<br>").append(formattedRecipe);
            contentBuilder.append("</body></html>");
            shoppingListLabel.setText(contentBuilder.toString());

        } else {
            shoppingListLabel.setText("<html><body style='padding:10px; font-size:12px;'>" +
                    "Could not load the shopping list." +
                    "</body></html>");
        }

        JScrollPane scrollPane = new JScrollPane(shoppingListLabel);
        shoppingListWindow.add(scrollPane);
        shoppingListWindow.setVisible(true);
    }
}
