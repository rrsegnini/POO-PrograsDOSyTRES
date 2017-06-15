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

//Cliente

public void initClient() /*ejecuta este metodo para correr el cliente */

{

try

{

sc = new Socket( HOST , PUERTO ); /*conectar a un servidor en localhost con puerto 5000*/

//creamos el flujo de datos por el que se enviara un mensaje

mensaje = new DataOutputStream(sc.getOutputStream());

//enviamos el mensaje

mensaje.writeUTF("Hola");

//cerramos la conexión

sc.close();

}catch(Exception e )

{

System.out.println("Error: "+e.getMessage());

}

}
}
