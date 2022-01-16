import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("serial")
public class Client extends UnicastRemoteObject implements CRClient {
	public Client() throws RemoteException {
		super();
	}

	public static void main(String[] args) {
		CRServer conn;
		try {
			conn = (CRServer) Naming.lookup("//127.0.0.1/Server1");
			try {

				String login = JOptionPane.showInputDialog(null, "Insira um nickname");
				Naming.rebind(login, new Client());
				Player player = new Player();
				player.setLogin(login);
				conn.connect(player);
				JFrame frame = new JFrame("Chat-IFG!");
				JPanel meuPainel = new JPanel();
				JTextField msg = new JTextField("Digite uma mensagem");
				JButton button = new JButton("Enviar");
				button.addActionListener(
						new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent e) {
								try {
									conn.sendMessage(player, msg.getText());
								} catch (RemoteException e1) {
									// TODO Auto-generated catch block
									e1.printStackTrace();
								}
							}
						});
				button.setSize(125, 20);
				msg.setSize(250, 20);
				msg.setAlignmentY(500);
				msg.getText();
				meuPainel.add(msg);
				meuPainel.add(button);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(meuPainel);
				frame.setSize(1024, 720);
				if (login != null) {
					frame.setVisible(true);
				}
				//conn.sendMessage(player, JOptionPane.showInputDialog(null, "Digite uma mensagem"));
			} catch (Exception e) {
				System.out.println("Erro: " + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			System.out.println("Client exception: " + e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public void receiveMessage(String msg) throws RemoteException {
		System.out.println(msg);
	}
}