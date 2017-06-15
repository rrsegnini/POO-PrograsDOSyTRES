/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.util.Vector;

/**
 *
 * @author danielalvarado
 */
public class Employee {
    int employeeID;
    private String email;
    private String password;
    private String fullName;
    private  EmployeeStatus status;
    
    
    private Vector<Ticket> ticketList =  new Vector<Ticket>();
    
    public Employee (int _employeeID,String _email, String _password, String _fullName) {
        
        this.employeeID = _employeeID;
        this.email = _email;
        this.fullName = _fullName;
        this.password = _password;
        EmployeeStatus _status = EmployeeStatus.DISCONNECTED;
        
        this.status = _status;
        
    }
    
    public String getEmail() {return this.email;}
    public String getFullName() {return this.fullName;}
    
    public void connectClient() {
        EmployeeStatus connect = EmployeeStatus.CONNECTED;
        this.status = connect;
    }
    
    public void disconnectClient() {
        EmployeeStatus disconnect = EmployeeStatus.DISCONNECTED;
        this.status = disconnect;
    }

    public void addTicket(Ticket _newTicket) {
        this.ticketList.add(_newTicket);
    }
    
    public Vector<Ticket> getTicketsResolved() {return this.ticketList;}
    
    public int getID() {return this.employeeID;}
    
}
