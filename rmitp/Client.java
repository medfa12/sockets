import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class Client {

    private Client() {}

    public static void main(String[] args) {
        try {

            Registry registry = LocateRegistry.getRegistry(null);

            Calculatrice stub = (Calculatrice) registry.lookup("Calculatrice");
            
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Entrez l'opération (add, sub, mul, div) et deux nombres :");
                String operation = scanner.next();
                double a = scanner.nextDouble();
                double b = scanner.nextDouble();
                
                double resultat = 0;
                switch (operation) {
                    case "add":
                        resultat = stub.addition(a, b);
                        break;
                    case "sub":
                        resultat = stub.soustraction(a, b);
                        break;
                    case "mul":
                        resultat = stub.multiplication(a, b);
                        break;
                    case "div":
                        resultat = stub.division(a, b);
                        break;
                    default:
                        System.out.println("Opération inconnue.");
                        continue;
                }
                
                System.out.println("Résultat: " + resultat);
                System.out.println("Voulez-vous continuer? (y/n)");
                if(scanner.next().equalsIgnoreCase("n")) break;
            }
        } catch (Exception e) {
            System.err.println("Exception côté client: " + e.toString());
            e.printStackTrace();
        }
    }
}
