import voiture.*;
import java.io.*;
import java.net.*;
public class ClientUDP {

    public static void main(String[] args) throws IOException {

        DatagramSocket clientSocket = new DatagramSocket();


        InetAddress serverAddress = InetAddress.getLocalHost();
        int serverPort = 5000;
        voiture maVoiture = new voiture("Essence", "Citroën C3");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(maVoiture);
        oos.flush();
        byte[] data = baos.toByteArray();
        DatagramPacket sendPacket = new DatagramPacket(data, data.length, serverAddress, serverPort);
        clientSocket.send(sendPacket);
        byte[] receiveData = new byte[1024];
        DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
        clientSocket.receive(receivePacket);

        ByteArrayInputStream bais = new ByteArrayInputStream(receiveData);
        ObjectInputStream ois = new ObjectInputStream(bais);
        int newCarburant = (int) ois.readObject();


        System.out.println("Nouveau niveau de carburant : " + newCarburant);


        clientSocket.close();
    }
}
