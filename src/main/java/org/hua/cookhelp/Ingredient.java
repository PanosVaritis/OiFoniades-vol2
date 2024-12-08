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

    public Ingredient(String name, double quantity){
        this(name,quantity,null);
    }

    public String getName(){
        return name;
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

    @Override
    public String toString() {
        String formattedQuantity = (quantity % 1 == 0) ? String.valueOf((int) quantity) : String.valueOf(quantity);

        if (unit != null) {       
            return name + " " + formattedQuantity + " " + unit;
        } else {
            return name + " " + formattedQuantity;
        }
    }


}
