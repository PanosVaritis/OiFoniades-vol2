package org.hua.cookhelp;

import java.util.Set;

import javax.swing.Timer;

import gr.hua.dit.oop2.countdown.Countdown;
import gr.hua.dit.oop2.countdown.CountdownFactory;
import gr.hua.dit.oop2.countdown.Notifier;

public class Step {
    private Timer countdownTimer;
    private Countdown countdown;
    private long timeDuration;
    private String timeUnit;
    private String description;

    public Step (String description,long timeDuration, String timeUnit){
        this.description = description;
        this.timeDuration = timeDuration;
        this.timeUnit = timeUnit;
        addCountdown();
    }

    public Step(String description){
        this.description = description;
        this.timeDuration = 0;
        this.timeUnit = null;
    }

    public void convertToSeconds(){
        final Set<String> minuteUnits = Set.of("min","mins","minute","minutes","minute/minutes:","λεπτά","λεπτα","λεπτό","λεπτο","λ");
        final Set<String> hourUnits = Set.of("h","hr","hrs","hour","hours","hour/hours","ώρες","ωρες","ώρα","ωρα","ω");
        if(minuteUnits.contains(timeUnit.toLowerCase())){
            setTimeUnit("δευτερόλεπτα");
            setTimeDuration(timeDuration * 60);
        }else if (hourUnits.contains(timeUnit.toLowerCase())){
            setTimeUnit("δευτερόλεπτα");
            setTimeDuration(timeDuration * 3600);
        }
    }

    private void addCountdown(){
        if (timeDuration > 0 || timeUnit != null ){
            convertToSeconds();
            countdown = CountdownFactory.countdown(description,timeDuration);
        }else {
            System.out.println("Invalid time duration. Countdown not added.");
        }
    }

    public void addNotifier(Notifier notifier){
        if (countdown != null) {
            countdown.addNotifier(notifier);
        }
    }
    public void startCountdown() {
        if (countdown != null) {
            countdown.start();
        }else {
            System.out.println("No countdown has been added to this step.");
        }
    }

    public void stopCountdown() {
        if (countdown != null) {
            countdown.stop();
        }else {
            System.out.println("No countdown has been added to this step.");
        }
    }

    public long secondsRemaining() {
        if (countdown != null) {
            return countdown.secondsRemaining();
        }
        return -1;
    }

    public Countdown getCountdown() {
        return countdown;
    }

    public double getTimeDuration() {
        return timeDuration;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public void setTimeDuration(long timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getDescription(){
        return description;
    }


    // private class CountdownNotifier implements Notifier {
    //     @Override
    //     public void finished(Countdown countdown) {
    //         System.out.println("Countdown " + countdown.getName() + " finished!");
    //     }
    // }

    @Override
    public String toString(){
        return description;
    }
}
