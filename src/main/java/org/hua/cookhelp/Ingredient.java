package org.hua.cookhelp;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Ingredient{

    private HashMap<String, Double> oddUnits = new HashMap<>();
    private String name;
    private double quantity;
    private String unit;

    public Ingredient(String name, double quantity, String unit){
        this.name = name;
        this.quantity = quantity;
        this.unit = unit;
    }

    public Ingredient(String name, double quantity){
        this.name = name;
        this.quantity = quantity;
        this.unit = "";
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

    public Map<String, Double> getOddUnits() {
        return oddUnits;
    }

    public void setQuantity(double quantity){
        this.quantity = quantity;
    }

    public double getQuantity(){
        return quantity;
    }

    public void addQuantity(double quantity, String unit) {
        if (unit == null|| isStandardUnit(unit) || this.unit.equalsIgnoreCase(unit)) {
            this.quantity += quantity;
            if (unit != null ) {
                this.unit = unit;
            }
        }else {
           addOddUnits(quantity, unit);
        }
    }

    public void addOddUnits(double quantity, String unit){
        oddUnits.merge(unit.toLowerCase(), quantity, Double::sum);
    }

    public boolean isStandardUnit(String unit) {
        final Set<String> standardUnits = Set.of("ml","gr","");
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

        if(oddUnits != null){
            for(Map.Entry<String, Double> entry : oddUnits.entrySet()){
                sb.append(" και ").append(entry.getValue().toString()).append(" ").append(entry.getKey());
            }
        }

        return sb.toString();
    }
}
