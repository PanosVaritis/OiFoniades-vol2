package org.hua.cookhelp.gui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;

import org.hua.cookhelp.Recipe;
import org.hua.cookhelp.parser.RecipeReader;

public class RecipePanel extends JFrame{
    private DefaultListModel<String> fileListModel;
    private JList<String> fileList;
    private JLabel contentLabel;

    public RecipePanel(){
        setTitle("Cook Helper");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        fileListModel = new DefaultListModel<>();
        fileList = new JList<>(fileListModel);
        fileList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        contentLabel = new JLabel("Select a Recipe", SwingConstants.CENTER);
        contentLabel.setVerticalAlignment(SwingConstants.TOP);

        fileList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedFileName = fileList.getSelectedValue();
                if (selectedFileName != null) {
                    displayFileContent(selectedFileName);
                }
            }
        });

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,new JScrollPane(fileList), new JScrollPane(contentLabel));
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);

        add(splitPane, BorderLayout.CENTER);

        JButton addFileButton = new JButton("Add File");
        addFileButton.addActionListener(e -> addFile());
        add(addFileButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void addFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            fileListModel.addElement(selectedFile.getName());
        }
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

    private void displayFileContent(String filePath) {
        Recipe cookFile = showCookFile(filePath);
        if (cookFile != null) {
            String formattedRecipe = cookFile.toString().replace("\n", "<br>");
            contentLabel.setText("<html><body style='padding:10px;'>" +
                    "Recipe: <b>" + filePath + "</b><br><br>" +
                    formattedRecipe +
                    "</body></html>");
        } else {
            contentLabel.setText("<html><body style='padding:10px;'>" +
                    "Could not load recipe: <b>" + filePath + "</b>" +
                    "</body></html>");
        }
    }
}
