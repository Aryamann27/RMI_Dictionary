import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DictionaryService extends Remote {
    String lookup(String word) throws RemoteException;
}
