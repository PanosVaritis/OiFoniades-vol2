
package org.hua.testregex;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author panos
 */
public class TestRegex {

    public static void main(String[] args) {
        System.out.println ("Welcome to the coooking machine!!!");
        
        List <String> list = new ArrayList<>();
        
        String text = """
                      Το @βούτυρο λιώνεται σε ένα μεγάλο τηγάνι. Στην συνέχεια προσθέστε το @αλάτι{5}
                      Ανακατέψτε τον @ζωμό λαχανικών{3%lt} και τέλος προσθέστε λίγο @πιπέρι ο οπίο φροντίζω να τρίψω καλά
                      Παίρνουμε τις @χυλοπίτες και τις βράζουμε για 20 λεπτά σε μία κατσαρόλα
                      Αφήνουμε τις @χοιρινές μπιρζόλες λαιμού{1%kg} να ξεκουραστούν αφού πρώτα τις μαρινάρουμε με @μουστάρδα{1%κουταλιά}
                      @μέλι θυμαρίσιο{200%gr} και @παρθένο ελαιόλαδο{120%ml}. Τέλος κόβουμε @πατάτες αιγίνης{10} σε κύβους και τις αφήνουμε να ξεκουραστούν
                      Δεν ξεχνώ να κόψω @βασιλικό{2%κλονάρια} και @πορτοκάλια για να σερβίρω 
                      Προκειμένου να γινέι το γλυκό χρειάζομαι @ξινά μήλα{} @λεμόνια και επίσης θα ήταν καλό να έχω @μαστίχα{1%φύλο}
                      Θα χρειαστεί επίσης @εβαπορέ γάλα{1%lt} και @ξερή μαγία{}. Η @σαντιγι για το σερβίρισμα θα ήταν χρήσιμη 
                      Επίσης θα χρειαστεί @κανέλα κειλάνης{} και @φυστίκι αιγίνης κελυφωτό{} ενώ αν δεν έχεις μπορέις να βάλεις @ψίχα αμυγδάλου ιταλίας{10%κούπες του νερού}
                      @αμυγδαλωτά{2%κουταλάκια τις σούπας}. Η @παρμεζανα θα πρέπει να αποθηκευτει σε 
                      ξηρό χώρο προκειμένου να μη χαλάσει απο τις συνθήκς
                      που επικρατούν στην 
                      ατμόσφαιρα σήμερα επίσης το @λαδι{1} να είναι σε σκιλα
                  """;
//        String text = """
//                      Το @βούτυρο λιώνεται σε ένα μεγάλο τηγάνι. Στην συνέχεια προσθέστε το @αλάτι{5}.
//                      Ανακατέψτε τον @ζωμό λαχανικών{3%lt} και τέλος προσθέστε λίγο @πιπέρι ο οπίο φροντίζω να τρίψω καλά.
//                      Παίρνουμε τις @χυλοπίτες και τις βράζουμε για 20 λεπτά σε μία κατσαρόλα.
//                      Αφήνουμε τις @χοιρινές μπιρζόλες λαιμού{1%kg} να ξεκουραστούν αφού πρώτα τις μαρινάρουμε με @μουστάρδα{1%κουταλιά}.
//                      @μέλι θυμαρίσιο{200%gr} και @παρθένο ελαιόλαδο{120%ml}. Τέλος κόβουμε @πατάτες αιγίνης{10} σε κύβους και τις αφήνουμε να ξεκουραστούν
//                      Δεν ξεχνώ να κόψω @βασιλικό{2%κλονάρια} και @πορτοκάλια για να σερβίρω .
//                      Προκειμένου να γινέι το γλυκό χρειάζομαι @ξινά μήλα{} @λεμόνια και επίσης θα ήταν καλό να έχω @μαστίχα{1%φύλο}.
//                      Θα χρειαστεί επίσης @εβαπορέ γάλα{1%lt} και @ξερή μαγία{}. Η @σαντιγι για το σερβίρισμα θα ήταν χρήσιμη .
//                      Επίσης θα χρειαστεί @κανέλα κειλάνης{} και @φυστίκι αιγίνης κελυφωτό{} ενώ αν δεν έχεις μπορέις να βάλεις @ψίχα αμυγδάλου ιταλίας{10%κούπες του νερού}
//                      @αμυγδαλωτά{2%κουταλάκια τις σούπας}. Η @παρμεζανα θα πρέπει να αποθηκευτει σε .
//                      ξηρό χώρο προκειμένου να μη χαλάσει απο τις συνθήκς.
//                      που επικρατούν στην .
//                      ατμόσφαιρα σήμερα επίσης το @λαδι{1} να είναι σε σκιλα.
//                  """;
//        
        

//        String pattern = "@([a-zA-Zά-ώΑ-Ω]+)(\\s|,|\\.|\\{|$)(\\{(\\d+)(%[a-zA-Zά-ώΑ-Ω]+)?\\})?";
//        String pattern = "@([a-zA-Zά-ώΑ-Ω]+(?:\\s[a-zA-Zά-ώΑ-Ω]+)*)(\\{(\\d+)(%[a-zA-Zά-ώΑ-Ω]+)?\\})?";
//        String pattern = "@([a-zA-Zά-ώΑ-Ω]+|[a-zA-Zά-ώΑ-Ω]+(?:\\s[a-zA-Zά-ώΑ-Ω]+)*)(\\{(\\d+)(%[a-zA-Zά-ώΑ-Ω]+)?\\})?";
//        String pattern = "@([a-zA-Zά-ώΑ-Ω]+(?:\\s[a-zA-Zά-ώΑ-Ω]+)*)(\\{(\\d+)(%[a-zA-Zά-ώΑ-Ω]+)?\\})?(?=\\s|\\.|,|$)";


        /**
         * I can't find the correct regex (in case  i have a single word ingredient it returns more info)
         *  if the text is: @butter to the pot and... it will match the whole sentence
         * After i place the ingredients in the list i search again with a regex and keep if the string in the list
         * is like: @butter ..... without {} i keep only the first word @butter and cut the rest
         */

        String pattern = "@([a-zA-Zά-ώΑ-Ω\\s]+)(\\{(\\d+)?(%[a-zA-Zά-ώΑ-Ω\\s]+)?\\})?";
        Pattern regex = Pattern.compile(pattern);
        Matcher myMatch = regex.matcher(text);
        

        while (myMatch.find()){
                list.add(myMatch.group());
        }

        
        System.out.println ("\nBefore we combine the lines: ");
        for (int i = 0;i < list.size();i++){
            System.out.println ((i+1)+". "+list.get(i));
            
        }
        
        /**
         * In case that the text has no . and it changes lines a problem will occur and some ingredients
         * will be stored in more that one line, So with this loop i makes sure that each string of the lsit 
         * requires only one line
         */
        for (int i = 0;i < list.size();i++){
            list.set(i, list.get(i).replace("\n", " "));
        }
        
        //Here only for testing purspose's i present the ingredients with the form that they were matched from the text
        System.out.println ("\nIngredients as shown inside the list: (after we combine the lines)");
        for (int i = 0;i < list.size();i++){
            System.out.println ((i+1)+". "+list.get(i));
            
        }
        
        
        
       //In this part i correct the form of the ingredients 
        
       String regex1 = "^(@\\S+)(\\s.*)?$";
        
       Pattern newPattern = Pattern.compile(regex1);
       

       for(int i = 0;i < list.size();i++){
           String item = list.get(i);
           
           if (!(item.contains("{") && item.contains("}"))){
               
               Matcher newMatcher = newPattern.matcher(item);
               if (newMatcher.find())
                   list.set(i, newMatcher.group(1));
           }
           
       }
        
        
        
        
        
        //Here i print the ingredients with their correct form
        System.out.println ("\n\nIngredients after a minor fix:");
        for (int i = 0;i < list.size();i++){
            System.out.println ((i+1)+". "+list.get(i));
            
        }
        
//        
        for (int i = 0;i < list.size();i++){
            list.set(i, list.get(i).substring(1));
            list.set(i, list.get(i).replace("{"," "));
            list.set(i, list.get(i).replace("}"," "));
            list.set(i, list.get(i).replace("%", " "));
            
        }
        
        
        System.out.println ("Final form of the ingredients: ");
         for (int i = 0;i < list.size();i++){
            System.out.println ((i+1)+". "+list.get(i));
            
        }
        
//        
        
        
        
        
    }
}
