import java.io.*;
import java.net.*;
import java.util.Scanner;

class SocketClient {
    public static void main(String argv[]) {
        int port = 0;
        String host = "";
        Scanner keyb = new Scanner(System.in);

        // Demander à l'utilisateur de saisir le nom d'hôte (ou adresse IP) et le port du serveur
        System.out.print("Nom du serveur : ");
        host = keyb.next();
        System.out.print("Port du serveur : ");
        try {
            port = keyb.nextInt(); // Lire le port saisi par l'utilisateur
        } catch (NumberFormatException e) {
            System.err.println("Le second paramètre n'est pas un entier.");
            System.exit(-1);
        }

        try {
            // Résolution de l'adresse du serveur à partir du nom d'hôte
            InetAddress adr = InetAddress.getByName(host);

            // Établissement d'une connexion au serveur sur l'adresse et le port spécifiés
            Socket socket = new Socket(adr, port);
            System.out.println("Connexion établie avec le serveur.");

            // Initialisation des flux d'entrée et de sortie pour communiquer avec le serveur
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Envoi d'un message au serveur
            output.writeObject(new String("ma première socket"));
            System.out.println("Message envoyé au serveur.");

            // Réception et affichage de la réponse du serveur
            String chaine = (String) input.readObject();
            System.out.println("Message reçu du serveur : " + chaine);

        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
