package Servidor;
import java.awt.event.ActionEvent;
import java.net.*;

import java.io.*;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.JOptionPane;


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
                
                
                String password = "";
                String user = "";
                //entrada = new ObjectInputStream(this.socket.getInputStream());
                user = (String)entrada.readUTF();  //Usuario
                System.out.println(user);
                
                
                password = (String)entrada.readUTF(); //Contraseña
                System.out.println(password);
                
                if (server.getEmployeeByEmail(user).successfulLogin(user, password)){         
                    System.out.println("Un cliente se ha conectado.");
                }
                else{
                this.socket.close();
                }
                
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
                    salida.writeUTF(server.getREDticketString());      
                }
                else if (buffer.equals("colaAmarillos")){
                    System.out.println("Yellows");
                    //salidaObjeto.defaultWriteObject();
                    salida.writeUTF(server.getYELLOWticketString());      
                }
                else if (buffer.equals("colaVerdes")){
                    System.out.println("Verdes");
                    //salidaObjeto.defaultWriteObject();
                    salida.writeUTF(server.getGREENticketString());      
                }
                else if (buffer.equals("atender")){
                    System.out.println("atender");
                    buffer = (String)entrada.readUTF(); 
                    JOptionPane.showMessageDialog(serverGUI, "Un empleado ha comenzado a atender el ticket con el subject: " + buffer);
                    server.getTicketWString(buffer).setTicketStatus("En Atencion");
                }
                else if (buffer.equals("listo")){
                    System.out.println("listo");
                    buffer = (String)entrada.readUTF(); 
                    String[] tokens = buffer.split("\\;");
                    int _employeeiD=0;int _ticketID=0; String _complain=""; 
                    int _secondsSpen=0;
                    String _resolvedComment=""; Date _dateResolved = new java.util.Date();;
                    int cont=0;
                    for (String token : tokens) {
                        //System.out.println(token);
                        if (cont == 0){
                         _employeeiD = server.getEmployeeByEmail(token).getID();
                        }
                        else if (cont == 1){
                           _ticketID = server.getTicketWString(token).getTicketID();
                        }
                        else if (cont == 2){
                           _complain = token;
                        }
                        else if (cont == 3){
                           _secondsSpen = Integer.parseInt(token);
                        }
                        else if (cont == 4){
                           _resolvedComment = token;
                        }
                        else if (cont == 5){
                            java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd");
                            java.util.Date date = new java.util.Date();
                            dateFormat.format(date);
                            _dateResolved = date;
                        }
                        
                        cont++;
                        
                    }
                    server.updateTicket(_employeeiD, _ticketID, _complain, _secondsSpen, _resolvedComment, _dateResolved);
                    //server.getTicketWString(buffer).setTicketStatus("Atendido");
                }
                else if (buffer.equals("no_listo")){
                    System.out.println("no_listo");
                    buffer = (String)entrada.readUTF(); 
                    String[] tokens = buffer.split("\\;");
                    int _employeeiD=0;int _ticketID=0; String _complain=""; 
                    int _secondsSpen=0;
                    String _resolvedComment=""; Date _dateResolved = new java.util.Date();;
                    int cont=0;
                    for (String token : tokens) {
                        //System.out.println(token);
                        if (cont == 0){
                         _employeeiD = server.getEmployeeByEmail(token).getID();
                        }
                        else if (cont == 1){
                           _ticketID = server.getTicketWString(token).getTicketID();
                        }
                        else if (cont == 2){
                           _complain = token;
                        }
                        else if (cont == 3){
                           _secondsSpen = Integer.parseInt(token);
                        }
                        else if (cont == 4){
                           _resolvedComment = token;
                        }
                        else if (cont == 5){
                            java.text.DateFormat dateFormat = new java.text.SimpleDateFormat("yyyy/MM/dd");
                            java.util.Date date = new java.util.Date();
                            dateFormat.format(date);
                            _dateResolved = date;
                        }
                        
                        cont++;
                        
                    }
                    server.updateUnresolvedTicket(_employeeiD, _ticketID, _complain, _secondsSpen, _resolvedComment, _dateResolved);
                    //server.getTicketWString(buffer).setTicketStatus("Atendido");
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
