
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Serveur {

    public static void main(String[] args) {
        try {

            CalculatriceImpl calculatrice = new CalculatriceImpl();
            

            Registry registry = LocateRegistry.createRegistry(1099);
            

            registry.bind("Calculatrice", calculatrice);
            
            System.out.println("Serveur RMI prêt.");
        } catch (Exception e) {
            System.err.println("Exception côté serveur: " + e.toString());
            e.printStackTrace();
        }
    }
}
