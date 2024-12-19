package org.hua.cookhelp.gui;


import javax.swing.JFrame;

public class MainWindow extends JFrame{

    public static void main(String[] args) {
        RecipePanel recipePanel = new RecipePanel();
        
        // JFrame frame = new JFrame("CookHelp");
        // frame.setSize(500, 300);
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // JButton uploadButton = new JButton("Upload File");
        // JLabel fileNameLabel = new JLabel("No file selected.");
        // fileNameLabel.setPreferredSize(new Dimension(300, 30));


        // frame.add(uploadButton);
        // frame.add(fileNameLabel);

        // JPanel leftPanel = new JPanel(new BorderLayout());
        // JPanel fileButtonPanel = new JPanel();
        // leftPanel.add(new JLabel("Recipe Files", SwingConstants.CENTER), BorderLayout.NORTH);
        // leftPanel.add(fileButtonPanel, BorderLayout.CENTER);

        // JPanel rightPanel = new JPanel(new BorderLayout());
        // JTextArea recipeContentArea = new JTextArea();
        // recipeContentArea.setEditable(false);
        // rightPanel.add(new JScrollPane(recipeContentArea), BorderLayout.CENTER);

        // JPanel uploadButtonPanel = new JPanel();
        // uploadButtonPanel.add(uploadButton);
        // rightPanel.add(uploadButtonPanel, BorderLayout.CENTER);
        // rightPanel.add(fileNameLabel, BorderLayout.SOUTH);  

        // JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
        // splitPane.setDividerLocation(150); 
        // splitPane.setOneTouchExpandable(true);

        // uploadButton.addActionListener(e -> {
        //     JFileChooser fileChooser = new JFileChooser();

        //     int result = fileChooser.showOpenDialog(frame);

        //     if (result == JFileChooser.APPROVE_OPTION) {
        //         File selectedFile = fileChooser.getSelectedFile();
        //         FileButton fileButton = new FileButton(selectedFile.getAbsolutePath());
        //         fileButton.setVisible(true);
        //         frame.add(fileButton);
        //         fileButtonPanel.add(fileButton);
        //         fileButtonPanel.revalidate();
        //         fileButtonPanel.repaint();
                
                
        //     } else if (result == JFileChooser.CANCEL_OPTION) {
        //         fileNameLabel.setText("No file selected.");
        //     }
        // });

        // frame.setLayout(new BorderLayout());
        // frame.add(splitPane,BorderLayout.CENTER);
        
        // frame.setVisible(true);
        
    }
}    
