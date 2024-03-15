import java.rmi.*; // interface du serveur
public interface IServer extends Remote {
public void callMeBack(int time, String param,ICallback callback)
throws RemoteException;
}