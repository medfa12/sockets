import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class MonServiceImpl extends UnicastRemoteObject implements MonService {
    protected MonServiceImpl() throws RemoteException {
        super();
    }

    public String maMethode() throws RemoteException {
        return "Hello, world!";
    }

    public static void main(String[] args) {
        try {
            MonService service = new MonServiceImpl();
            Naming.rebind("rmi://localhost/MonService", service);
            System.out.println("Service RMI enregistré");
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}