import java.rmi.Remote;
import java.rmi.RemoteException;

public interface CRClient extends Remote {
	void receiveMessage(String string) throws RemoteException;
}