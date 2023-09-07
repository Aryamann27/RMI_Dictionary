import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Map;

public class DictionaryServiceImpl extends UnicastRemoteObject implements DictionaryService {

    private Map<String, String> dictionary;

    public DictionaryServiceImpl() throws RemoteException {
        dictionary = new HashMap<>();
        loadDictionaryFromFile("dictionary.txt"); // Load data from the file
    }

    private void loadDictionaryFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split line into word and definition
                if (parts.length == 2) {
                    String word = parts[0].trim();
                    String definition = parts[1].trim();
                    dictionary.put(word, definition);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String lookup(String word) throws RemoteException {
        return dictionary.getOrDefault(word, "Word not found in the dictionary");
    }

    public static void main(String[] args) {
        try {
            DictionaryService service = new DictionaryServiceImpl();
            java.rmi.registry.LocateRegistry.createRegistry(8080); // Default RMI registry port
            java.rmi.Naming.rebind("DictionaryService", service);
            System.out.println("DictionaryService is running...");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
