import java.net.*;

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
    final String HOST = "localhost";

final int PUERTO=5000;

Socket sc;

DataOutputStream mensaje;

DataInputStream entrada;
String buffer;
//Cliente

public void initClient() /*ejecuta este metodo para correr el cliente */

{

try

{

sc = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/
entrada = new DataInputStream(sc.getInputStream());
buffer = entrada.readUTF();
System.out.println(buffer);
/*
//creamos el flujo de datos por el que se enviara un mensaje
entrada = new DataInputStream(sc.getInputStream());
buffer = entrada.readUTF();
System.out.println(buffer);
mensaje = new DataOutputStream(sc.getOutputStream());

//enviamos el mensaje

mensaje.writeUTF("Hola");

//cerramos la conexión

sc.close();*/

}catch(Exception e )

{

System.out.println("Error: "+e.getMessage());

}

}

public void send() /*ejecuta este metodo para correr el cliente */
    {

    try
    {

    //creamos el flujo de datos por el que se enviara un mensaje
    //entrada = new DataInputStream(sc.getInputStream());
    //buffer = entrada.readUTF();
    ////System.out.println(buffer);
    mensaje = new DataOutputStream(sc.getOutputStream());

    mensaje.writeUTF("Hola");

    //cerramos la conexión

    //sc.close();

    }catch(Exception e )

    {

    System.out.println("Error: "+e.getMessage());

    }

    }
    }
