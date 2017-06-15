package Servidor;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import java.net.*;
import java.io.*;
/**
 * Created by Sergio Hidalgo Fonseca on 14/6/2017.
 */
public class Server {
    //Sockets
    
    
    final int PUERTO=5000;
    ServerSocket sc;
    Socket so;
    DataOutputStream salida;
    String mensajeRecibido;
    
    public void initServer(InterfazServidor gui){
    BufferedReader entrada;
    try{
    sc = new ServerSocket(PUERTO );/* crea socket servidor que escuchara en puerto 5000*/
    so=new Socket();
    System.out.println("Esperando una conexión:");
    //while (true) {
            /*try {
                so = sc.accept();
                System.out.println("Un cliente se ha conectado.");
            } catch (IOException e) {
                System.out.println("I/O error: " + e);
            }*/
            // new threa for a client
            //Thread socketServer = new Thread(new Runnable());
            SocketThread socketserver = new SocketThread(gui, this, so, sc);
            socketserver.start();         
            //new SocketThread(so).start();
        //}
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
    }catch(Exception e ){
        System.out.println("Err000r: "+e.getMessage());
        }
    }
    //Fin Sockets
    
    private Vector<Ticket> ticketsList =  new Vector<>();
    private Vector<Ticket> RedTickestList =  new Vector<Ticket>();
    private Vector<Ticket> GreenTicketsList =  new Vector<Ticket>();
    private Vector<Ticket> YellowTicketsList =  new Vector<Ticket>();
    
    private static Server INSTANCE = null;
    
    private Server() {}
    
    private synchronized static void createInstance() {
        if (INSTANCE == null)
            INSTANCE = new Server();
    }
    
    public static Server getInstance() {
        createInstance();
        return INSTANCE;
    }



    public void readExcel() {
        try {
            final JFileChooser fc = new JFileChooser();
            fc.showOpenDialog(fc);

            java.io.File file = fc.getSelectedFile();
            String path = fc.getCurrentDirectory().getAbsolutePath();
            System.out.println(path);
            String Filename = fc.getName(file);

            FileInputStream fileInputStream = new FileInputStream(path + "/" + Filename);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheetAt(0);


            int cont = 1;
            while (true){
                XSSFRow row1 = worksheet.getRow(cont);

                XSSFCell cellB1 = row1.getCell((short) 1);
                double b1Val = cellB1.getNumericCellValue();

                XSSFCell cellC1 = row1.getCell((short) 2);
                String c1Val = cellC1.getStringCellValue();

                int b1 = (int) b1Val;

                System.out.println("B1: " + b1Val);
                System.out.println("C1: " + c1Val);
                cont++;

                Ticket nuevo = new Ticket(b1, c1Val);

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //} catch (NullPointerException e){
            //      System.out.println("Yap");
        }
    }
void addTicket(int _clientID, String _subjet, int _ticketID) {
        Ticket newTicket = new Ticket( _clientID,  _subjet, _ticketID);
        this.ticketsList.add(newTicket);
        
    }
    
    /**
     * Method returns the List of Tickets in a period of time
     * 
     * @param _date1i Date 1
     * @param _date2f Date 2
     * @return 
     */
    public Vector<Ticket> getTicketsInDates(Date _date1i, Date _date2f) {
        
        Vector<Ticket> newListTickets =  new Vector<>();
        Ticket ticket;
        for (int i = 0; i < this.ticketsList.size(); i++) {
            ticket = this.ticketsList.get(i);
            if (_date1i.compareTo(ticket.getDateReceived()) >= 0 && 
                    _date2f.compareTo(ticket.getDateReceived()) <= 0) {
                newListTickets.add(ticket);
            }    
        }       
        return newListTickets;
    }
    
    public void ticketDetails(int _ticketID, String _complain, int _secondsSpent, 
            String _resolvedComment, Date _dateResolved) {
        
        Ticket ticket;
        int timeSpent = 0;
        for (int i = 0; i < this.ticketsList.size(); i++) {
            ticket = this.ticketsList.get(i);
            if (ticket.getTicketID()== _ticketID) {
                ticket.setComentary(_resolvedComment);
                timeSpent = ticket.getAttentionMetric(ticket.getTicketCategory(), timeSpent);
                ticket.setTimeSolved(_secondsSpent);
                ticket.setDateResolved(_dateResolved);
                ticket.setComplain(_complain);
                
            }
            
        }    
        
            
    }
    
    public void updateTicket(int _employeeiD,int _ticketID, String _complain, int _secondsSpent, 
            String _resolvedComment, Date _dateResolved) {
    
        this.ticketDetails(_ticketID, _complain, _secondsSpent, _resolvedComment,
                _dateResolved);
        
        
        Ticket ticket;
        for (int i = 0; i < this.ticketsList.size(); i++) {
            ticket = this.ticketsList.get(i);
            if (ticket.getTicketID()== _ticketID) {
                ticket.setTicketStatus("Atendido");
                ticket.setEmployeeID(_employeeiD);
                this.addTicket2Employee(_employeeiD, ticket);
            }
        }  
    }
    
    public Vector<Ticket> getREDticketList() {
        return this.RedTickestList;
    
    }
    
    public Vector<Ticket> getYELLOWticketList() {
        return this.YellowTicketsList;
    
    }
    
    public Vector<Ticket> getGREENticketList() {
        return this.GreenTicketsList;
    }
    
    public Vector<Ticket> getAllTickets() {
        return this.ticketsList;
    
    }
    
    public void addEmployee(int _employeeID,String _email, String _password, String _fullName) {
        Employee newEmployee = new Employee(_employeeID, _email,  _password,  _fullName);
        this.employeesList.add(newEmployee);
    }
    
    public Vector<Employee> getEmployeeList() {return this.employeesList;}
    
    public void addTicket2Employee(int _employeeID,Ticket _ticket) {
        Employee employee;
        
        for (int i = 0; i < this.employeesList.size(); i++) {
            employee = this.employeesList.get(i);
            if (employee.getID() == _employeeID) {
                employee.addTicket(_ticket);
            }
            
        } 
    }
    
    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    //////////////////////////////////////////////////
    
    public void connectEmployee(int _employeeID) {
        Employee employee;
        for (int i = 0; i < this.employeesList.size(); i++) {
            employee = this.employeesList.get(i);
            if (employee.getID() == _employeeID) {
                employee.connect();
            }
        }
    }
    
    public void disconnectEmployee(int _employeeID) {
        Employee employee;
        for (int i = 0; i < this.employeesList.size(); i++) {
            employee = this.employeesList.get(i);
            if (employee.getID() == _employeeID) {
                employee.disconnect();
            }
        }
    }
    
    public boolean reserveTicket(int _ticketID, int _employeeID) {
        Ticket ticket;
        TicketStatus status = TicketStatus.PENDING;
        for (int i = 0; i < this.ticketsList.size(); i++) {
            ticket = this.ticketsList.get(i);
            if (ticket.getTicketID()== _ticketID) {
                if (ticket.getTicketStatus() == status) {
                    ticket.setTicketStatus("En Atencion");
                    Employee employee = this.getEmployee(_employeeID);
                    employee.addTicketReceived(ticket);
                    return true;
                } else {
                    return false;
                
                }
            }
        }
        return false;
    }
    
    public Employee getEmployee(int _employeeID) {
        Employee employee = new Employee();
        
        for (int i = 0;i < this.employeesList.size();i ++) {
            employee = this.employeesList.get(i);
            if (_employeeID == employee.getID() ) {
                break;
            }
        }
        return employee;
    }
    
    public void releaseTicket(int _ticketID) {
        Ticket ticket;
        for (int i = 0; i < this.ticketsList.size(); i++) {
            ticket = this.ticketsList.get(i);
            if (ticket.getTicketID()== _ticketID) {
                ticket.setTicketStatus("Pendiente");
            }
        }
    }
    
    /**
     * Average time spent by an employee
     * 
     * @param _employeeID receives an Employee ID
     * @return returns the average time spent by that Employee solving tickets
     */
    public int averageTimeByEmployee(int _employeeID) {
        Employee employee;
        int timeSpent = 0;
        
        for (int i = 0; i < this.employeesList.size(); i++) {
            employee = this.employeesList.get(i);
            if (employee.getID() == _employeeID) {
                timeSpent = employee.getAverageTime();
                break;
            }
        }
        return timeSpent;
    }
    
    /**
     * Average time spent resolving tickets
     * 
     * @return returns an int (seconds) of the average time spent processing
     * tickets
     */
    public int averateTimeTickets() {
        int count = 0;
        int totalSecs = 0;
        Ticket ticket;
        TicketStatus resolved = TicketStatus.RESOLVED;
        for (int i = 0; i < this.ticketsList.size(); i++) {
            ticket = this.ticketsList.get(i);
            if (ticket.getTicketStatus() == resolved) {
                totalSecs += ticket.getTime();
                count++;
            }
        }
        
        return totalSecs/count;
    }
    
    public Vector<Ticket> getTicketsSolvedByEmployee(int _employeeID) {
        Employee employee;
        Vector<Ticket> nullVector = new Vector<Ticket>();
        for (int i = 0; i < this.employeesList.size(); i++) {
            employee = this.employeesList.get(i);
            if (employee.getID() == _employeeID) {
                return employee.getTicketsResolved();
            }
        }
        
        return nullVector;
    }
    
    public String getEmployeeReport(String _email, String _password, Date _date) {
        Employee employee;
        String msg = "";
        for (int i = 0; i < this.employeesList.size(); i++) {
            employee = this.employeesList.get(i);
            if (employee.successfulLogin(_email, _password)) {
                Vector<Ticket> ticketsListInDateSolved = new Vector<Ticket>();
                Vector<Ticket> ticketsListInDateReceived = new Vector<Ticket>();
                ticketsListInDateSolved = employee.getTicketsInDateSolved(_date);
                ticketsListInDateReceived = employee.getTicketsInDateReceived(_date);
                
                int ticketsSolved = ticketsListInDateSolved.size();
                int ticketsReceived = ticketsListInDateReceived.size();
                int average = (ticketsSolved/ticketsReceived) *100;
                
                String ticketsSolv = Integer.toString(ticketsSolved);
                String ticketsRec = Integer.toString(ticketsReceived);
                String avg = Integer.toString(average);
                
                msg += "La cantidad de tickets resueltos es: " + ticketsSolv +
                        ", la cantidad de tickets recibidos es: " + ticketsRec +
                        ". El promedio de de resolucion es: " + avg;
                return msg;
            }
        }
        msg = "Usuario y/o contrasenha errone@";
        return msg;
    
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
            @Override
            public void run() {
                InterfazServidor dialog = new InterfazServidor(new javax.swing.JFrame(), true);
                Server server = Server.getInstance();
                
                //SocketThread socket = new SocketThread(dialog, server);
                //server.initServer(socket);
                System.out.println("AAAAAAAAAAAAH");
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.LogInServer.setVisible(true);
            }
        });
        
        
    }

    


}
