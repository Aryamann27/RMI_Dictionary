import java.rmi.Naming;
import java.util.Scanner;

public class DictionaryClient {
    public static void main(String[] args) {
        try {
            DictionaryService service = (DictionaryService) Naming.lookup("rmi://localhost/DictionaryService");
            
            // Create a Scanner to read user input
            Scanner scanner = new Scanner(System.in);
            
            // Prompt the user for input
            System.out.print("Enter a word to look up in the dictionary: ");
            String word = scanner.nextLine();

            // Call the remote method with the user's input
            String definition = service.lookup(word);
            
            // Display the result
            System.out.println("Definition of '" + word + "': " + definition);

            // Close the scanner
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
