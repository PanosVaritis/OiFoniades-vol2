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
    private JButton skipButton;



    public CountdownButton(String fileName, List<Step> stepList,JLabel stepLabel,JLabel timeLabel,JFrame parentFrame){
        super(fileName);
        this.parentFrame = parentFrame;     
        setVisible(false);

        skipButton = new JButton("Skip Countdown");
        skipButton.setEnabled(false);
        skipButton.setVisible(false);
        parentFrame.add(skipButton);

        addActionListener(e -> continueStep(stepList, stepLabel, timeLabel));
        skipButton.addActionListener(e -> skipCountdown(timeLabel));
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
                skipButton.setEnabled(true);
                currentStep.startCountdown();
                
                countdownTimer = new Timer(0, e -> {
                    skipButton.setVisible(true);
                    timeLabel.setVisible(true);
                    long remaining = currentStep.secondsRemaining();
                    timeLabel.setText("Time left: " + remaining + " seconds");

                    if (remaining <= 0) {
                        ((Timer) e.getSource()).stop();
                        skipButton.setVisible(false);
                        skipButton.setEnabled(false);
                        timeLabel.setVisible(false);

                    }
                });

                countdownTimer.start();
                currentStep.addNotifier(getCountdownNotifier());

            }else{
                this.setEnabled(true);
                skipButton.setEnabled(false);
                skipButton.setVisible(false);
                timeLabel.setVisible(false);
            }
            currentStepIndex++;
       }else{
        SwingUtilities.invokeLater(() -> {
            javax.swing.JOptionPane.showMessageDialog(parentFrame, "Recipe has finished");
            parentFrame.dispose();
        });
       }
    }

    private void skipCountdown(JLabel timeLabel) {
        if (countdownTimer != null && countdownTimer.isRunning()) {
            countdownTimer.stop();
            timeLabel.setVisible(false);
            skipButton.setEnabled(false);
            skipButton.setVisible(false);
            currentStep.stopCountdown();

            javax.swing.JOptionPane.showMessageDialog(parentFrame, "Countdown skipped, you may proceed to the next step");
            this.setEnabled(true);
        }
    }

    public JButton getSkipCountdownButton(){
        return skipButton;
    }

    private Notifier getCountdownNotifier() {
        return new CountdownNotifier();
    }

    private class CountdownNotifier implements Notifier {
        @Override
        public void finished(Countdown countdown) {
            System.out.println("Countdown: " + countdown.getName() + " finished");

            SwingUtilities.invokeLater(() -> {
                javax.swing.JOptionPane.showMessageDialog(parentFrame, "Finished, you may proceed to the next step");
                CountdownButton.this.setEnabled(true);
            });
        }
    }
    
}
