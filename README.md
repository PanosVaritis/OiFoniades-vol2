1. Clone the repository to your local machine:

    ```bash
    git clone https://github.com/yourusername/cookHelp.git
    ```

2. Navigate to the project directory:

    ```bash
    cd OiFoniades-vol2
    ```

3. Build the project using Maven:

    ```bash
    mvn clean package
    ```

4. Show recipe:

    ```bash
    java -jar target/cookHelp-1.0-SNAPSHOT.jar [recipe-file.cook]
    ```
4. Show Ingredient Shopping list(multiple arguements are required):

    ```bash
    java -jar target/cookHelp-1.0-SNAPSHOT.jar -list [recipe-file.cook] [recipe-file2.cook] ....
    ```
Requires special files called .cook example below:

    ```bash
    Σπάστε τα @αυγά{2} σε ένα #μπλέντερ και χτυπήστε καλά.

    Προσθέστε το @γάλα{250%ml} και το @αλεύρι{125%gr}, και συνεχίστε το 
    χτύπημα.

    Αφήστε το μείγμα να ξεκουραστεί σε ένα #μπολ για ~{10%λεπτά}.

    Λιώστε το @βούτυρο{50%gr} σε ένα #μεγάλο αντικολλητικό τηγάνι{} σε μέτρια 
    φωτιά.

    Ρίξτε μια κουταλιά από το μείγμα στο τηγάνι, γυρίστε το όταν ροδίσει και 
    μαγειρέψτε για ~{2%λεπτά} ακόμα.

    Σερβίρετε με την αγαπημένη σας επικάλυψη.

    ```
    
