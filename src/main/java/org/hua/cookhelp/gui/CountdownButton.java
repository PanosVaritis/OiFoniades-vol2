package org.hua.cookhelp.gui;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import org.hua.cookhelp.Step;

import gr.hua.dit.oop2.countdown.Countdown;
import gr.hua.dit.oop2.countdown.Notifier;

public class CountdownButton extends JButton {
    private int currentStepIndex;
    private JFrame parentFrame;
    private Step currentStep;
    private Timer countdownTimer;


    public CountdownButton(String fileName, List<Step> stepList,JLabel stepLabel,JLabel timeLabel,JFrame parentFrame){
        super(fileName);
        this.parentFrame = parentFrame; 
        setVisible(false);
        addActionListener(e -> continueStep(stepList,stepLabel,timeLabel));
    }

    public void continueStep(List<Step> stepList,JLabel stepLabel, JLabel timeLabel){ 
       if (currentStepIndex < stepList.size()){
            currentStep = stepList.get(currentStepIndex);
            stepLabel.setText(currentStep.getDescription());

            if (currentStepIndex == 0) {
                this.setText("Continue");
            }

            if (currentStep.getTimeDuration() > 0){
                this.setEnabled(false);
                currentStep.startCountdown();
                
                countdownTimer = new Timer(0, e -> {
                    timeLabel.setVisible(true);
                    long remaining = currentStep.secondsRemaining();
                    timeLabel.setText("Time left: " + remaining + " seconds");

                    if (remaining <= 0) {
                        ((Timer) e.getSource()).stop();
                        timeLabel.setVisible(false);
                    }
                });

                countdownTimer.start();
                currentStep.addNotifier(getCountdownNotifier());

            }else{
                this.setEnabled(true);
            }
            currentStepIndex++;
       }else{
        SwingUtilities.invokeLater(() -> {
            JFrame finishedFrame = new JFrame();
            javax.swing.JOptionPane.showMessageDialog(finishedFrame, "Recipe has finished");
            parentFrame.dispose();
        });
       }
    }

    private Notifier getCountdownNotifier() {
        return new CountdownNotifier();
    }

    private class CountdownNotifier implements Notifier {
        @Override
        public void finished(Countdown countdown) {
            System.out.println("Countdown: " + countdown.getName() + " finished");

            SwingUtilities.invokeLater(() -> {
                JFrame finishedFrame = new JFrame();
                javax.swing.JOptionPane.showMessageDialog(finishedFrame, "Finished, you may proceed to the next step");
                CountdownButton.this.setEnabled(true);
            });
        }
    }
    
}
