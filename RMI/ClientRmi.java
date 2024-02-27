import java.rmi.Naming;

public class ClientRMI {
    public static void main(String[] args) {
        try {
            MonService service = (MonService) Naming.lookup("rmi://localhost/MonService");
            System.out.println(service.maMethode());
        } catch (Exception e) {
            System.out.println("Erreur: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
