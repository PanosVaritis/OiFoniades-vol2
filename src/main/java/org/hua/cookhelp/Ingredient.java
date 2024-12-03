package org.hua.cookhelp;

public class Ingredient{
    
    private String name;
    private double quantity;
    private String unit;

    public Ingredient(String name, double quantity){
        this.name = name;
        this.quantity = quantity;
    }

    public Ingredient(){
        
    }

    public String getName(){
        return name;
    }

    public String getUnit(){
        return unit;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public double getQuantity(){
        return quantity;
    }

    public void addQuantity(double quantity) {
        this.quantity += quantity;
    }

    public void convertMeasurements(){
        //TODO
    }
}
