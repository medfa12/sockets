import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.*;
public class IServerImp extends UnicastRemoteObject implements IServer
{
public IServerImp() throws RemoteException
{
super();
}
public void callMeBack(int time, String param,ICallback callback)
throws RemoteException
{
 Servant servant = new Servant(time, param,callback);
// création du servant
 servant.start();
// démarrage du servant
}
} 