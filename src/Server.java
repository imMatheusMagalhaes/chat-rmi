import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Server extends UnicastRemoteObject implements CRServer {
	private ArrayList<Player> players = new ArrayList<Player>();
	
	public Server() throws RemoteException {super();} 

	public static void main(String args[]) {
		try {
			Naming.rebind("Server1", new Server());
			System.out.println("Servidor Iniciado");
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessage(Player player, String msg) throws RemoteException {
		for(Player playerX : players) {
			playerX.getConn().receiveMessage(player.getLogin() + " diz: " + msg);
		}
	}
	
	@Override
	public void connect(Player player) throws RemoteException {
		players.add(player);
		try {
			player.setConn((CRClient) Naming.lookup("//127.0.0.1/" + player.getLogin()));
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		}
		System.out.println("Player conectou: " + player.getLogin());
	}

	@Override
	public void disconnect(Player player) throws RemoteException {
		players.remove(player);
		System.out.println("Player desconectou: " + player.getLogin());
	}
}
