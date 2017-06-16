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
    final String HOST = "127.0.0.1";
    final int PUERTO=5000;

Socket sc;

//DataOutputStream mensaje;
DataOutputStream mensaje;
DataInputStream entrada;
//DataInputStream buffer;
String buffer;
//Cliente

public void initClient() /*ejecuta este metodo para correr el cliente */{
    try{

        sc = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/
        entrada = new DataInputStream(sc.getInputStream());
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF("rojassegniniroberto@gmail.com");
        mensaje.writeUTF("2016139072");
        
        //buffer = entrada.readUTF();
        //System.out.println(buffer);
    }catch(Exception e ){
        System.out.println("Error: "+e.getMessage());
    }

}

public void send(){
    try{
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF("Hola");
    }catch(Exception e ){
        System.out.println("Error: "+e.getMessage());
    }
    }

public String requestTickets() /*ejecuta este metodo para correr el cliente */{
    String ticketsRojos = "";
    try{
        mensaje = new DataOutputStream(sc.getOutputStream());
        mensaje.writeUTF("colaRojos");
        //mensaje.flush();
   
        //bufferObject = new ObjectInputStream(sc.getInputStream());
        //bufferObject.defaultReadObject();
        ticketsRojos = entrada.readUTF();
        //bufferObject.defaultReadObject();
        //Object AA = bufferObject.readObject();
       
    }catch(Exception e ){
        System.out.println("Error: "+e.getMessage());
    }
    return ticketsRojos;
    }
}


