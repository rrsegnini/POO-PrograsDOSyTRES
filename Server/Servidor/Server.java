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
    private Vector<Employee> employeesList = new Vector<>();
    private Vector<Ticket> RedTickestList =  new Vector<>();
    private Vector<Ticket> GreenTicketsList =  new Vector<>();
    private Vector<Ticket> YellowTicketsList =  new Vector<>();
    private String excelFileName = "";
    private String excelFilePath = "";
    
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
            excelFilePath = path;
            System.out.println(path);
            String Filename = fc.getName(file);
            excelFileName = Filename;

            FileInputStream fileInputStream = new FileInputStream(path + "/" + Filename);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheetAt(0);


            int cont = 1;
            while (true){
                XSSFRow row = worksheet.getRow(cont);

                if(row != null) {
                    XSSFCell cellB1 = row.getCell((short) 1);
                    double b1Val = cellB1.getNumericCellValue();

                    XSSFCell cellC1 = row.getCell((short) 2);
                    String c1Val = cellC1.getStringCellValue();

                    int b1 = (int) b1Val;

                    System.out.println("B1: " + b1Val);
                    System.out.println("C1: " + c1Val);
                    cont++;

                    addTicket(b1, c1Val);
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //} catch (NullPointerException e){
            //      System.out.println("Yap");
        }
    }

    public void saveExcel(Employee _employee) {
        try {
            FileInputStream fileInputStream = new FileInputStream(excelFilePath + "/" + excelFileName);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet worksheet = workbook.getSheetAt(0);


            int cont = 1;
            for(int index = 0; RedTickestList.get(index)!=null; index++){
                Ticket _ticket = ticketsList.get(index);
                XSSFRow row = worksheet.getRow(cont);

                if(_ticket.getTicketStatus() == TicketStatus.RESOLVED){
                    XSSFCell cellA1 = row.getCell((short) 0);
                    cellA1.setCellValue(_ticket.getDateReceivedString() + " - " + _ticket.getTimeReceived());

                    XSSFCell cellB1 = row.getCell((short) 1);
                    cellB1.setCellValue(_ticket.getClientID());

                    XSSFCell cellC1 = row.getCell((short) 2);
                    cellC1.setCellValue(_ticket.getSubject());

                    XSSFCell cellD1 = row.getCell((short) 3);
                    cellD1.setCellValue(_ticket.getTicketID());

                    XSSFCell cellE1 = row.getCell((short) 4);
                    cellE1.setCellValue(_ticket.getTicketCategoryStr());

                    XSSFCell cellF1 = row.getCell((short) 5);
                    cellF1.setCellValue(_ticket.getEmployeeID());

                    XSSFCell cellG1 = row.getCell((short) 6);
                    cellG1.setCellValue(_ticket.getDateResolvedString() + " - " + _ticket.getTimeResolvedString());

                    XSSFCell cellH1 = row.getCell((short) 7);
                    cellH1.setCellValue(_ticket.getTime());

                    XSSFCell cellI1 = row.getCell((short) 8);
                    cellI1.setCellValue(_ticket.getTicketComment());

                    XSSFCell cellJ1 = row.getCell((short) 9);
                    cellJ1.setCellValue(_ticket.getTicketStatusStr());

                    cont++;
                } else {

                    XSSFCell cellA1 = row.getCell((short) 1);
                    cellA1.setCellValue(_ticket.getDateReceivedString() + " - " + _ticket.getTimeReceived());

                    XSSFCell cellB1 = row.getCell((short) 1);
                    cellB1.setCellValue(_ticket.getClientID());

                    XSSFCell cellC1 = row.getCell((short) 2);
                    cellC1.setCellValue(_ticket.getSubject());

                    cont++;
                }

            }

            for(int index1 = 0; YellowTicketsList.get(index1)!=null; index1++){
                Ticket _ticket = ticketsList.get(index1);
                XSSFRow row = worksheet.getRow(cont);

                if(_ticket.getTicketStatus() == TicketStatus.RESOLVED){
                    XSSFCell cellA1 = row.getCell((short) 0);
                    cellA1.setCellValue(_ticket.getDateReceivedString() + " - " + _ticket.getTimeReceived());

                    XSSFCell cellB1 = row.getCell((short) 1);
                    cellB1.setCellValue(_ticket.getClientID());

                    XSSFCell cellC1 = row.getCell((short) 2);
                    cellC1.setCellValue(_ticket.getSubject());

                    XSSFCell cellD1 = row.getCell((short) 3);
                    cellD1.setCellValue(_ticket.getTicketID());

                    XSSFCell cellE1 = row.getCell((short) 4);
                    cellE1.setCellValue(_ticket.getTicketCategoryStr());

                    XSSFCell cellF1 = row.getCell((short) 5);
                    cellF1.setCellValue(_ticket.getEmployeeID());

                    XSSFCell cellG1 = row.getCell((short) 6);
                    cellG1.setCellValue(_ticket.getDateResolvedString() + " - " + _ticket.getTimeResolvedString());

                    XSSFCell cellH1 = row.getCell((short) 7);
                    cellH1.setCellValue(_ticket.getTime());

                    XSSFCell cellI1 = row.getCell((short) 8);
                    cellI1.setCellValue(_ticket.getTicketComment());

                    XSSFCell cellJ1 = row.getCell((short) 9);
                    cellJ1.setCellValue(_ticket.getTicketStatusStr());

                    cont++;

                } else {

                    XSSFCell cellA1 = row.getCell((short) 1);
                    cellA1.setCellValue(_ticket.getDateReceivedString() + " - " + _ticket.getTimeReceived());

                    XSSFCell cellB1 = row.getCell((short) 1);
                    cellB1.setCellValue(_ticket.getClientID());

                    XSSFCell cellC1 = row.getCell((short) 2);
                    cellC1.setCellValue(_ticket.getSubject());

                    cont++;

                }
            }

            for(int index2 = 0; GreenTicketsList.get(index2)!=null; index2++){
                Ticket _ticket = ticketsList.get(index2);
                XSSFRow row = worksheet.getRow(cont);

                if(_ticket.getTicketStatus() == TicketStatus.RESOLVED){
                    XSSFCell cellA1 = row.getCell((short) 0);
                    cellA1.setCellValue(_ticket.getDateReceivedString() + " - " + _ticket.getTimeReceived());

                    XSSFCell cellB1 = row.getCell((short) 1);
                    cellB1.setCellValue(_ticket.getClientID());

                    XSSFCell cellC1 = row.getCell((short) 2);
                    cellC1.setCellValue(_ticket.getSubject());

                    XSSFCell cellD1 = row.getCell((short) 3);
                    cellD1.setCellValue(_ticket.getTicketID());

                    XSSFCell cellE1 = row.getCell((short) 4);
                    cellE1.setCellValue(_ticket.getTicketCategoryStr());

                    XSSFCell cellF1 = row.getCell((short) 5);
                    cellF1.setCellValue(_ticket.getEmployeeID());

                    XSSFCell cellG1 = row.getCell((short) 6);
                    cellG1.setCellValue(_ticket.getDateResolvedString() + " - " + _ticket.getTimeResolvedString());

                    XSSFCell cellH1 = row.getCell((short) 7);
                    cellH1.setCellValue(_ticket.getTime());

                    XSSFCell cellI1 = row.getCell((short) 8);
                    cellI1.setCellValue(_ticket.getTicketComment());

                    XSSFCell cellJ1 = row.getCell((short) 9);
                    cellJ1.setCellValue(_ticket.getTicketStatusStr());

                    cont++;

                } else {

                    XSSFCell cellA1 = row.getCell((short) 1);
                    cellA1.setCellValue(_ticket.getDateReceivedString() + " - " + _ticket.getTimeReceived());

                    XSSFCell cellB1 = row.getCell((short) 1);
                    cellB1.setCellValue(_ticket.getClientID());

                    XSSFCell cellC1 = row.getCell((short) 2);
                    cellC1.setCellValue(_ticket.getSubject());

                    cont++;

                }
            }

            for(int index3 = 0; ticketsList.get(index3)!=null; index3++){
                Ticket _ticket = ticketsList.get(index3);
                XSSFRow row = worksheet.getRow(cont);

                XSSFCell cellA1 = row.getCell((short) 1);
                cellA1.setCellValue(_ticket.getDateReceivedString() + " - " + _ticket.getTimeReceived());

                XSSFCell cellB1 = row.getCell((short) 1);
                cellB1.setCellValue(_ticket.getClientID());

                XSSFCell cellC1 = row.getCell((short) 2);
                cellC1.setCellValue(_ticket.getSubject());

                cont++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            //} catch (NullPointerException e){
            //      System.out.println("Yap");
        }
    }




        void addTicket(int _clientID, String _subjet) {
        Ticket newTicket = new Ticket( _clientID,  _subjet);
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


    public void addTicketToCategoryList(Ticket _ticket, TicketCategory _categ) {
        
        if (_categ == TicketCategory.LOW) {
            this.GreenTicketsList.add(_ticket);
        } else if (_categ == TicketCategory.MEDIUM) {
            this.YellowTicketsList.add(_ticket);
        } else if (_categ == TicketCategory.URGENT) {
            this.RedTickestList.add(_ticket);
        }
        
    
    }
    
    public void setTicketCategory(int _ticketID, String _category) {
        Ticket ticket;
        TicketCategory categ = TicketCategory.LOW;
        for (int i = 0;i < this.ticketsList.size();i++) {
            ticket = this.ticketsList.get(i);
            if (ticket.getTicketID() == _ticketID) {
                ticket.setTicketCategory(_category);
                categ = ticket.getTicketCategory();
                this.addTicketToCategoryList(ticket, categ);
            }
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
            @Override
            public void run() {
                InterfazServidor dialog = new InterfazServidor(new javax.swing.JFrame(), true);
                Server server = Server.getInstance();
                server.readExcel();
                
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
