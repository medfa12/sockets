import java.io.*;
import java.net.*;
import java.util.Scanner;

public class SocketServeur {
    public static void main(String argv[]) {
        int port = 0;
        Scanner keyb = new Scanner(System.in);

        // Demander à l'utilisateur de saisir le port d'écoute du serveur
        System.out.print("Port d'écoute : ");
        try {
            port = keyb.nextInt(); // Lire le port saisi par l'utilisateur
        } catch (NumberFormatException e) {
            System.err.println("Le paramètre n'est pas un entier.");
            System.err.println("Usage : java ServeurUDP port-serveur");
            System.exit(-1);
        }

        try {
            // Création d'un nouveau socket serveur sur le port spécifié
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Serveur démarré et en attente de connexion...");

            // Accepter une connexion client. Cette méthode bloque jusqu'à ce qu'une connexion soit établie.
            Socket socket = serverSocket.accept();
            System.out.println("Connexion établie avec le client.");

            // Initialisation des flux d'entrée et de sortie pour communiquer avec le client
            ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream input = new ObjectInputStream(socket.getInputStream());

            // Lecture d'une chaîne de caractères envoyée par le client
            String chaine = (String) input.readObject();
            System.out.println("Message reçu : " + chaine);

            // Affichage des informations sur la source du message (adresse IP et port du client)
            System.out.println("Message reçu de : " + socket.getInetAddress() + ":" + socket.getPort());

            // Envoi d'une réponse au client pour accuser réception du message
            output.writeObject(new String("Message bien reçu"));

        } catch (Exception e) {
            System.err.println("Erreur : " + e);
        }
    }
}
