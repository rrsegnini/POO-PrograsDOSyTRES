/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

/**
 *
 * @author danielalvarado
 */
public class Employee {
    
    private String email;
    private String password;
    private String fullName;
    private  EmployeeStatus status;
    
    public Employee (String _email, String _password, String _fullName) {
        
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

}
