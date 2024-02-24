
import java.io.*;
import java.net.*;

public class ServeurUDP {

    public static void main(String[] args) throws IOException {

        DatagramSocket serverSocket = new DatagramSocket(5000);

        while (true) {

            byte[] receiveData = new byte[1024];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
            ObjectInputStream ois = new ObjectInputStream(bais);
            voiture voitureRecu = (voiture) ois.readObject();

            voitureRecu.setCarburant(100);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(voitureRecu);
            oos.flush();
            byte[] data = baos.toByteArray();

            DatagramPacket sendPacket = new DatagramPacket(data, data.length, receivePacket.getAddress(), receivePacket.getPort());
            serverSocket.send(sendPacket);
        }
    }
}
