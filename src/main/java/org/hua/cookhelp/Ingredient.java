package org.hua.cookhelp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ingredient{

    private HashMap<String, Double> oddQuantities = new HashMap<>();
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

    public String getUnit(){
        return unit;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public double getQuantity(){
        return quantity;
    }

    // public void addQuantity(double quantity, String unit) {
    //     if (unit == null || isStandardUnit(unit)) {
    //         // If the unit is null (bulk) or standard, add to the main quantity
    //         this.quantity += quantity;
    //         if (unit != null) {
    //             this.unit = unit; // Set the unit if it's standard
    //         }
    //     } else if (this.unit == null) {
    //         // If no standard unit has been set, add this odd quantity to the main quantity
    //         // since it will be the first to be added
    //         this.quantity = quantity;
    //         this.unit = unit.toLowerCase();
    //     } else {
    //         // If it's an odd unit and the main unit is already set, merge it with odd quantities
    //         oddQuantities.merge(unit.toLowerCase(), quantity, Double::sum);
    //     }
    // }
    

    public void addQuantity(double quantity, String unit) {
        
        if (isStandardUnit(unit) || this.unit.equalsIgnoreCase(unit)) {
            this.quantity += quantity;
        }else {
            oddQuantities.merge(unit.toLowerCase(), this.quantity, Double::sum);
        }
    }

    private boolean isStandardUnit(String unit) {
        final Set<String> standardUnits = Set.of("ml","gr");
        return standardUnits.contains(unit.toLowerCase());
    }

    

    public void convertMeasurements(){
        final Set<String> lUnits = Set.of("l", "litre", "λιτρα");
        final Set<String> kgUnits = Set.of("kg","kilograms", "κιλό");

        if(lUnits.contains(unit.toLowerCase())){
                setUnit("ml");
                setQuantity(quantity * 1000.0);
        }else if (kgUnits.contains(unit.toLowerCase())){
                setUnit("gr");
                setQuantity(quantity * 1000.0);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        String formattedQuantity = (quantity % 1 == 0) ? String.valueOf((int) quantity) : String.valueOf(quantity);

        sb.append(name).append(" ");
        if (unit != null && isStandardUnit(unit)) {
                sb.append(formattedQuantity).append(" ").append(unit);
        }else{
            sb.append(formattedQuantity);
        }

        if(oddQuantities != null){
            for(Map.Entry<String, Double> entry : oddQuantities.entrySet()){
                sb.append(" και ").append(entry.getValue().toString()).append(" ").append(entry.getKey());
            }
        }

        return sb.toString();
    }
}
