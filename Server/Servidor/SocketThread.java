package Servidor;
import java.awt.event.ActionEvent;
import java.net.*;

import java.io.*;
import java.awt.event.ActionListener;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CASA
 */
public class SocketThread extends Thread{
    protected Socket socket;
    DataOutputStream mensaje;
    InterfazServidor serverGUI;
    Server server;
    ServerSocket serverSocket;
    public SocketThread(Socket clientSocket) {
        this.socket = clientSocket;
        
    }
    public SocketThread(InterfazServidor serverGUI, Server server, Socket clientSocket, ServerSocket serverSocket) {
        this.server = server;
        this.serverGUI = serverGUI;
        this.socket = clientSocket;
        this.serverSocket = serverSocket;
        
        this.serverGUI.addListener(new SocketThreadListener());
    }
    
    class SocketThreadListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            
            //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
    }
    @Override
    public void run() {
        System.out.println("JEJE");
        DataInputStream entrada;
        BufferedReader brinp = null;
        DataOutputStream out = null;
        
        //InterfazServidor.listaTicketsPendientes.
      /*  try {
            //entrada = socket.getInputStream();
            entrada = new DataInputStream(this.socket.getInputStream());
            //brinp = new BufferedReader(new InputStreamReader(entrada));
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Errorsh");
            return;
        }*/
        try {
                this.socket = this.serverSocket.accept();
                System.out.println("Un cliente se ha conectado.");
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
        String line;
        while (true) {
            try {
                mensaje = new DataOutputStream(this.socket.getOutputStream());

                mensaje.writeUTF("Conectado");
                
                entrada = new DataInputStream(this.socket.getInputStream());
                line = entrada.readUTF();
                
                //line = brinp.readLine();
                System.out.println(line);

            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
           
        }
    }
}
