import java.io.*;
import java.net.*;

public class ServeurUDP {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        DatagramSocket socketServeur = new DatagramSocket(5000);
        System.out.println("Serveur en écoute sur le port 5000.");
        while (true) {
            byte[] bufferReception = new byte[1024];
            DatagramPacket paquetRecu = new DatagramPacket(bufferReception, bufferReception.length);
            socketServeur.receive(paquetRecu);
            ByteArrayInputStream bais = new ByteArrayInputStream(bufferReception);
            ObjectInputStream ois = new ObjectInputStream(bais);
            voiture voitureRecue = (voiture) ois.readObject();
            voitureRecue.setCarburant(150);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(voitureRecue);
            byte[] donneesVoitureMiseAJour = baos.toByteArray();
            DatagramPacket paquetReponse = new DatagramPacket(donneesVoitureMiseAJour, donneesVoitureMiseAJour.length, paquetRecu.getAddress(), paquetRecu.getPort());
            socketServeur.send(paquetReponse);
        }
    }
}
