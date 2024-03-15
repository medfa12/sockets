import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
public class Server{
public static void main(String[] args) throws Exception {
// démarrage du serveur
 Registry registry = LocateRegistry.createRegistry(1099);
 IServerImp serveur = new IServerImp();
 registry.rebind("Server",serveur);
 System.out.println("Serveur pret");
} } 