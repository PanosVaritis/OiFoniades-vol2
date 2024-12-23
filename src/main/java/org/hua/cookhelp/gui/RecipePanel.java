package org.hua.cookhelp.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.hua.cookhelp.Recipe;
import org.hua.cookhelp.parser.RecipeReader;

public class RecipePanel extends JFrame{
    private DefaultListModel<String> fileListModel;
    private JList<String> fileList;
    private JLabel label;

    public RecipePanel(){
        setTitle("Cook Helper");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        label = new JLabel("Select a Recipe", SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.TOP);

        fileList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedFileName = fileList.getSelectedValue();
                if (selectedFileName != null) {
                    displayRecipe(selectedFileName);
                }
            }
        });

        // right panel
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(label, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        // execute recipe button
        ExecuteRecipeButton executeRecipeButton = new ExecuteRecipeButton("Execute");
        executeRecipeButton.setVisible(true);

        // add file button
        JButton addFileButton = new JButton("Add File");
        addFileButton.addActionListener(e -> addCookFile());

        // shopping list button
        ShoppingListButton shoppingListButton = new ShoppingListButton("Shopping List",fileListModel);
        shoppingListButton.setVisible(true);

        buttonPanel.add(executeRecipeButton);
        buttonPanel.add(shoppingListButton);
        buttonPanel.add(addFileButton);
        rightPanel.add(buttonPanel, BorderLayout.SOUTH);

        // left scroll pane
        JScrollPane leftScrollPane = new JScrollPane(fileList);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,leftScrollPane,rightPanel);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);

        rightPanel.add(label, BorderLayout.CENTER);
        add(splitPane, BorderLayout.CENTER);

        setVisible(true);
    }

    private void addCookFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileListModel.addElement(selectedFile.getAbsolutePath());
        }
    }

    public Recipe addRecipe(String filePath){
        if (filePath != null) {
            RecipeReader recipeReader = new RecipeReader();
            String fileContent = recipeReader.readCookFile(filePath);
            return new Recipe(filePath, fileContent);
        } else {
            System.err.println("File name is not set.");
            return null;
        }
    }

    private void displayRecipe(String filePath) {
        Recipe cookFile = addRecipe(filePath);
        if (cookFile != null) {
            String formattedRecipe = cookFile.toString().replace("\n", "<br>");
            label.setText("<html><body style='padding:10px;'>" +
                    "Recipe: <b>" + filePath + "</b><br><br>" +
                    formattedRecipe +
                    "</body></html>");
        } else {
            label.setText("<html><body style='padding:10px;'>" +
                    "Could not load recipe: <b>" + filePath + "</b>" +
                    "</body></html>");
        }
    }
}
