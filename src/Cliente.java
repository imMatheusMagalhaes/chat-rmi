import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.event.KeyEvent;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Cliente extends javax.swing.JFrame implements Observer {
    final ServidorChat chat = (ServidorChat) Naming.lookup("//127.0.0.1/ServidorChat");

    public Cliente() throws RemoteException, MalformedURLException, NotBoundException {
        super("Chat Simples em Java");
        initComponents();
        
        mensagemjTextArea.requestFocusInWindow();
    }

    private void envia() throws RemoteException {
        if (!mensagemjTextArea.getText().isEmpty()) {
            chat.enviarMensagem(mensagemjTextArea.getText());
            escreve("Você disse: ");
            mensagemjTextArea.setText("");
        }
    }

    private void escreve(String texto) throws RemoteException {
        
        ArrayList<String> msgsServidor = chat.lerMensagem();
        
        String ultimaMsgs = msgsServidor.get(msgsServidor.size() - 1);

        chatjTextArea.append(texto + ultimaMsgs + "\n");
        // setar
        if (!chatjTextArea.getText().isEmpty() && !chatjTextArea.isFocusOwner()) {
            chatjTextArea.setCaretPosition(chatjTextArea.getText().length() - 1);
        }
    }

    @SuppressWarnings("unchecked")
    //
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        chatjTextArea = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        mensagemjTextArea = new javax.swing.JTextArea();
        enviarjButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        chatjTextArea.setEditable(false);
        chatjTextArea.setColumns(20);
        chatjTextArea.setRows(5);
        jScrollPane1.setViewportView(chatjTextArea);

        mensagemjTextArea.setColumns(20);
        mensagemjTextArea.setRows(5);
        mensagemjTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                try {
                    mensagemjTextAreaKeyReleased(evt);
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
        jScrollPane2.setViewportView(mensagemjTextArea);

        enviarjButton.setText("Enviar");
        enviarjButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    enviarjButtonActionPerformed(evt);
                } catch (RemoteException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 386,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(enviarjButton, javax.swing.GroupLayout.DEFAULT_SIZE, 75,
                                        Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 349,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane2)
                                        .addComponent(enviarjButton, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(0, 0, Short.MAX_VALUE)));

        pack();
    }//

    private void enviarjButtonActionPerformed(java.awt.event.ActionEvent evt) throws RemoteException {
        envia();
    }

    private void mensagemjTextAreaKeyReleased(java.awt.event.KeyEvent evt) throws RemoteException {
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            envia();
        }
    }

    // Variables declaration - do not modify
    private javax.swing.JTextArea chatjTextArea;
    private javax.swing.JButton enviarjButton;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea mensagemjTextArea;
    // End of variables declaration

    @Override
    public void update(Observable o, Object arg) {
        // escreve("teste update");
    }

    public static void main(String args[]) throws RemoteException, MalformedURLException, NotBoundException {
        JFrame ui = new Cliente();
        ui.pack();
        ui.setVisible(true);

        // new Cliente();
    }
}