package org.hua.cookhelp.gui;

import java.awt.FlowLayout;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.hua.cookhelp.Recipe;
import org.hua.cookhelp.Step;
import org.hua.cookhelp.parser.RecipeReader;

public class ExecuteRecipeButton extends JButton {
    private String fileName;
    private boolean continueStep = false;

    public ExecuteRecipeButton(String fileName,String filePath){
        super(fileName);
        setVisible(false);        
        addActionListener(e -> displayRecipeSteps(filePath));
    }
 

    private Recipe createRecipe(String filePath){
        RecipeReader recipeReader = new RecipeReader();
        String fileContent = recipeReader.readCookFile(filePath);
        Recipe recipe = new Recipe(filePath,fileContent);
        return recipe;
    }

    private void displayRecipeSteps(String filePath){
        Recipe recipe = createRecipe(filePath);
        List<Step> stepList = recipe.getStepList();
        JLabel stepLabel = new JLabel();
        JLabel timeLabel = new JLabel();


        JFrame recipeStepsWindow = new JFrame("Recipe Steps");
        recipeStepsWindow.setSize(800, 300);
        recipeStepsWindow.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        CountdownButton countdownButton = new CountdownButton("Start", stepList, stepLabel,timeLabel,recipeStepsWindow);

        JButton startButton = new JButton("Resume Countdown");
        startButton.addActionListener(e -> countdownButton.startCountdown());
    
        JButton stopButton = new JButton("Stop Countdown");
        stopButton.addActionListener(e -> countdownButton.stopCountdown());

        JPanel buttonPanel = new JPanel(new FlowLayout(BoxLayout.Y_AXIS));

        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(countdownButton);
        
        recipeStepsWindow.setLayout(new BoxLayout(recipeStepsWindow.getContentPane(),BoxLayout.Y_AXIS));
        recipeStepsWindow.add(stepLabel);
        recipeStepsWindow.add(timeLabel);
        recipeStepsWindow.add(buttonPanel);

        countdownButton.setVisible(true);
        recipeStepsWindow.setVisible(true);
    }

}
