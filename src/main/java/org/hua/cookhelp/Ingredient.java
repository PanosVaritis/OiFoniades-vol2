package org.hua.cookhelp;

public class Ingredient{
    
    private String name;
    private double quantity;
    private String unit;

    public Ingredient(String name, double quantity, String unit){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public String getName(){
        return name;
    }

    public String getUnit(){
        return unit;
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
