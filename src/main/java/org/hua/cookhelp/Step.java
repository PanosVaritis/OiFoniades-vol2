package org.hua.cookhelp;

public class Step {
    private double timeDuration;
    private String timeUnit;
    private String description;

    public Step (String description,double timeDuration, String timeUnit){
        this.description = description;
        this.timeDuration = timeDuration;
        this.timeUnit = timeUnit;
    }

    public double getTimeDuration() {
        return timeDuration;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public String getDescription(){
        return description;
    }

    @Override
    public String toString(){
        return description;
    }

}
