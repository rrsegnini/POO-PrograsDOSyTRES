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
    //DataOutputStream mensaje;
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
        DataOutputStream salida;
        BufferedReader brinp = null;
        ///DataOutputStream out = null;
        String buffer;
        
        
        
        try {
            this.socket = this.serverSocket.accept();
            //entrada = socket.getInputStream();
            entrada = new DataInputStream(this.socket.getInputStream());
            //brinp = new BufferedReader(new InputStreamReader(entrada));
            salida = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException e) {
            System.out.println("Errorsh");
            return;
        }
        try {
                
                
                
                //entrada = new ObjectInputStream(this.socket.getInputStream());
                buffer = (String)entrada.readUTF();  //Usuario
                System.out.println(buffer);
                
                
                buffer = (String)entrada.readUTF(); //Contraseña
                System.out.println(buffer);
                
                
                System.out.println("Un cliente se ha conectado.");
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            //}catch (java.lang.ClassNotFoundException e) {
              //  e.printStackTrace();
              //  return;
            }
        
        while (true) {
            try {
                //salida = new DataOutputStream(this.socket.getOutputStream());
               // entrada = new DataInputStream(this.socket.getInputStream());
                buffer = (String)entrada.readUTF(); 
                System.out.println(buffer);
                
                if (buffer.equals("colaRojos")){
                    System.out.println("Rojos");
                    //salidaObjeto.defaultWriteObject();
                    //salida.writeUTF(server.getREDticketList());
                
                            
                }
                //mensaje = new DataOutputStream(this.socket.getOutputStream());

                //mensaje.writeUTF("Conectado");
                
                
                //buffer = entrada.readUTF();
                
                //line = brinp.readLine();
                //System.out.println(buffer);

            } catch (IOException e) {
                e.printStackTrace();
                return;
             } 
           
        }
    }
}
