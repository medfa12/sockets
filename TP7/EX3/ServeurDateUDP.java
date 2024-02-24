
import java.io.*;
import java.net.*;
import java.util.Date;

public class ServeurDateUDP {
    public static void main(String[] args) throws IOException {
        DatagramSocket socketServeur = new DatagramSocket(1250);
        byte[] bufferReception = new byte[1024];
        byte[] bufferEnvoi;

        while (true) {
            DatagramPacket paquetReçu = new DatagramPacket(bufferReception, bufferReception.length);
            socketServeur.receive(paquetReçu);
            
            String dateHeureActuelle = new Date().toString();
            bufferEnvoi = dateHeureActuelle.getBytes();
            
            InetAddress adresseClient = paquetReçu.getAddress();
            int portClient = paquetReçu.getPort();
            DatagramPacket paquetEnvoi = new DatagramPacket(bufferEnvoi, bufferEnvoi.length, adresseClient, portClient);
            
            socketServeur.send(paquetEnvoi);
        }
    }
}
