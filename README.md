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
    mvn clean install
    ```

4. Show recipe:

    ```bash
    java -jar target/cookHelp-1.0-SNAPSHOT.jar [recipe-file.cook]
    ```
4. Show Ingredient Shopping list(multiple arguements are required):

    ```bash
    java -jar target/cookHelp-1.0-SNAPSHOT.jar [recipe-file.cook] [recipe-file2.cook] ....
    ```
