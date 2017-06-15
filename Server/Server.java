import java.net.*;

import java.io.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *                          MODEL
 * @author CASA
 */
public class Server {
   final int PUERTO=5000;

    ServerSocket sc;

    Socket so;

    DataOutputStream salida;

    String mensajeRecibido;

    //SERVIDOR

    
    
    
    public void initServer(SocketThread socketserver)

    {

    BufferedReader entrada;

    try

    {

    sc = new ServerSocket(PUERTO );/* crea socket servidor que escuchara en puerto 5000*/

    so=new Socket();

    System.out.println("Esperando una conexión:");
    
    while (true) {
            try {
                so = sc.accept();
                System.out.println("Un cliente se ha conectado.");
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }
            // new threa for a client
            socketserver = new SocketThread(so);
            socketserver.start();
            
            
            //new SocketThread(so).start();
        }
    /*
    so = sc.accept();
    //Inicia el socket, ahora esta esperando una conexión por parte del cliente

    System.out.println("Un cliente se ha conectado.");

    //Canales de entrada y salida de datos

    entrada = new BufferedReader(new InputStreamReader(so.getInputStream()));

    salida = new DataOutputStream(so.getOutputStream());

    System.out.println("Confirmando conexion al cliente....");

    salida.writeUTF("Conexión exitosa...n envia un mensaje :D");

    //Recepcion de mensaje

    mensajeRecibido = entrada.readLine();

    System.out.println(mensajeRecibido);

    salida.writeUTF("Se recibio tu mensaje.n Terminando conexion...");

    salida.writeUTF("Gracias por conectarte, adios!");

    System.out.println("Cerrando conexión...");

    sc.close();//Aqui se cierra la conexión con el cliente
    */
    }catch(Exception e )

    {

    System.out.println("Err000r: "+e.getMessage());

    }

    }
    
        public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterfazServidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InterfazServidor dialog = new InterfazServidor(new javax.swing.JFrame(), true);
                Server server = new Server();
                SocketThread socket = new SocketThread(dialog, server);
                server.initServer(socket);
                
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        
        
    }
}
