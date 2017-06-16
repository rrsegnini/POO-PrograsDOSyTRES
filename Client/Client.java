import java.net.*;
import java.util.Vector;
import java.io.*;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CASA
 */
public class Client {
    String HOST = "127.0.0.1";
    int PUERTO=5000;

Socket sc;

//DataOutputStream mensaje;
DataOutputStream mensaje;
DataInputStream entrada;
//DataInputStream buffer;
String buffer;
//Cliente

public void initClient(String user, String password, String host, String port) /*ejecuta este metodo para correr el cliente */{
    try{
        HOST = host;
        PUERTO = Integer.parseInt(port);
        sc = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/
        entrada = new DataInputStream(sc.getInputStream());
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF(user);
        mensaje.writeUTF(password);
        
        //buffer = entrada.readUTF();
        //System.out.println(buffer);
    }catch(Exception e ){
        System.out.println("Error: "+e.getMessage());
    }

}

public void send(String request, String others){
    try{
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF(request);
        if (request.equals("atender")){
            mensaje.writeUTF(others);
        }
        else if (request.equals("listo")){
            mensaje.writeUTF(others);
        }
        else if (request.equals("no_listo")){
            mensaje.writeUTF(others);
        }
        else if (request.equals("reporte")){
            mensaje.writeUTF(others);
        }
    }catch(Exception e ){
        System.out.println("Error: "+e.getMessage());
    }
    }

public String requestTickets(String categoria) /*ejecuta este metodo para correr el cliente */{
    String tickets = "";
    try{
        if (categoria.equals("Urgente")){
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF("colaRojos");
       
        tickets = entrada.readUTF();
        }
        else if (categoria.equals("Media")){
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF("colaAmarillos");
       
        tickets = entrada.readUTF();
        }
        else if (categoria.equals("Leve")){
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF("colaVerdes");
       
        tickets = entrada.readUTF();
        }
        
        //bufferObject.defaultReadObject();
        //Object AA = bufferObject.readObject();
       
    }catch(Exception e ){
        System.out.println("Error: "+e.getMessage());
    }
    return tickets;
    }
}


