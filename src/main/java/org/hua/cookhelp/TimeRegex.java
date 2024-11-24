
package org.hua.cookhelp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author panos
 */
public class TimeRegex {
    
    public static void main (String[] args){
        System.out.println ("Testing to find time units with a specific pattern in a text!!");
  
        List<String> timeUnits = new ArrayList<>();
        
        String text = """
                      Προθερμαίνουμε τον φούρνο στους 180 βαθμούς για ~{15%minutes} ενώ μετά το πέρας αυτού
                      του χρονικού διαστήματος ανεβάζουμε την θερμοκρασία στους200 βαθμούς και τον αφήνουμε για ~{10%minutes}.
                      Μεταφέρουμε την @πανσέτα στον φούρνο και την ψήνουμε για ~{2.5%hours}. Στο ενδίαμεσο διάστημα αναλαμβανουμε,
                      τις υπόλοιπες δουλειες. Τσιγαρίζουμε το @κρεμμύδι σε ένα τηγάνι για ~{1%hours}. Κατόπιν το αφαιρούμε, 
                      και ξεκινάμε να μαγειρέυουμε τα @σπαράγγια στο καυτό τηγάνι για ~{20%minutes}.  Αλατοπιπερόνουμε και προσθέτουμε
                      τα υπόλοιπα υλικά στο τηγάνι για να μαγειρευτούν για ~{7%minutes}. Σερβίρουμε με την σάλτσα. 
                      Για την σάλτσα χρειάζεται να αφήσουνε τα υλικά εκτός ψυγείου για ~{1.5%hours} πρωτού τα βράσουμε στην κατσαρόλα,
                      για ~{12%minutes}. Επίσης ψήσε για ~{10%λεπτά και κάτι} το κρέας. Το ψάρι θέλει ~{30minutes}
                      ενώ το κρέας θέλει ~{4}. Το λαχανκό μπορεί να γίνει και σε {3%minutes} ή σε ~{5minutes}
                      """;
        
        
        /**
         * Based to this regex the pattern is simple
         * the special symbol ~ is mandatory as well as the left and right brackets {}
         * in order the patter to be matched it must contain inside a number the % and a single
         * word that will represent the time unit. The number can be any kind, such as int, float, double..
         * 
         */
        String regex = "~\\{\\d+(\\.\\d+)?%[a-zA-Zά-ώΑ-Ω]+\\}";
        Pattern pattern = Pattern.compile(regex);
        Matcher myMatcher = pattern.matcher(text);
        
        
        System.out.println ("The time units: ");
        while (myMatcher.find()){
            System.out.println (myMatcher.group());
            timeUnits.add(myMatcher.group());
        }
        
        
        
        //formatting the data obtained from the text in order to have the desired format
        for (int i = 0;i < timeUnits.size();i++){
            timeUnits.set(i, timeUnits.get(i).substring(1));
            timeUnits.set(i, timeUnits.get(i).replace("{", " "));
            timeUnits.set(i, timeUnits.get(i).replace("}", " "));
            timeUnits.set(i, timeUnits.get(i).replace("%", " "));
        }
        
        System.out.println ("Time units required for the recipe: ");
        for (int i = 0;i < timeUnits.size();i++){
            System.out.println(" "+timeUnits.get(i));
        }
        
        //Convering all the time units in a specific format
        for (int i = 0;i < timeUnits.size();i++){
            if (timeUnits.get(i).contains("hours")){
                timeUnits.set(i, timeUnits.get(i).replace("hours",""));
            }else if (timeUnits.get(i).contains("minutes")){
                timeUnits.set(i, timeUnits.get(i).replace("minutes",""));
            }
        }
        
        System.out.println ("Time units without their meassurement!!!");
        for (int i = 0; i < timeUnits.size();i++){
            System.out.println (timeUnits.get(i));
        }
        
        
        /**
         * Might be a good idea to use an array list without specifying the type we store
         * and then with typecasting adding the numbers
         */
        
        //An option in order to add the numbers of the list that are stored inside as,
        //string, and get a double result
//        double total = timeUnits.stream()
//                .mapToDouble(Double::parseDouble)
//                .sum();
//                
        
        //Another option
        double total = 0.0;
        for (int i = 0;i < timeUnits.size();i++)
            total += Double.parseDouble(timeUnits.get(i));


        System.out.println ("The total time is: "+total);
        
    }
    
    
}
