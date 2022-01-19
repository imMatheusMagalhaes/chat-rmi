import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.ArrayList;

public class Servidor implements ServidorChat {
	public Servidor() {
		try {
			//Registry registry = LocateRegistry.createRegistry(1098);
			ServidorChat server = new ServidorChatImpl();
			Naming.rebind("//127.0.0.1/ServidorChat", server);

		} catch (Exception e) {
			System.out.println("Trouble: " + e);
		}

	}

	public static void main(String args[]) {
		new Servidor();
	}

	@Override
	public void enviarMensagem(String mensagem) throws RemoteException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<String> lerMensagem() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

}