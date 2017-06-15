/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servidor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author danielalvarado
 */
public class Ticket implements AttentionMetric {
    private Date dateReceived;
    private int clientID;
    private int ticketID;
    private TicketCategory category;
    private TicketStatus status;
    private String subjet;
    private static int totalCreated;
    
    //After being resolved Data
    
    private Date dateResolved;
    private int timeBeingProcessed;
    private String comment;
    private String complain;
    
    
    
    
    
    /**
     * Ticket constructor method
     * 
     * @param _clientID
     * @param _subjet 
     */
    public Ticket (int _clientID, String _subjet, int _ticketID) {
        
        
        this.clientID = _clientID;
        this.ticketID = _ticketID;
        this.subjet = _subjet;
        TicketStatus _status = TicketStatus.PENDING;
        this.status = _status;
        totalCreated += 1;
    }
    
    int getClientID() {
        return this.clientID;
    }
    
    int getTicketID() { return this.ticketID;}
    
    
    public void setDateReceived() {
        Date date = new Date();
        
    }
    
    public Date getDateReceived() {
        return this.dateReceived;
    }
    
    public String getTimeReceived() {
        String time =  new SimpleDateFormat("HH:mm:ss").format(this.dateReceived);
        return time;   
    }
    
    public String getDateReceivedString() {
        String date =  new SimpleDateFormat("MM/dd/yyyy").format(this.dateReceived);
        return date;  
    }
    
    
    public void setTicketCategory(String _category) {
        
        TicketCategory tCateg = TicketCategory.LOW;
        
        switch(_category){
                case "Urgente":  tCateg = TicketCategory.URGENT;
                break;
                case "Media":  tCateg = TicketCategory.URGENT;
                break;
                case "Leve":  tCateg= TicketCategory.URGENT;
                break;      
                }
        this.category = tCateg;
        
    }
    
    public String getTicketCategory() {
        String categ = " ";
        
        if (this.category == TicketCategory.URGENT) {
            categ = "Urgente";
        } else if (this.category == TicketCategory.MEDIUM) {
            categ = "Media";
        } else if (this.category == TicketCategory.LOW) {
            categ = "Leve";
        } 
        return categ;
        
    }
    
    public void setTicketStatus(String _status) {
        TicketStatus tStatus = TicketStatus.PENDING;
        
        switch(_status){
                case "Pendiente":  tStatus = TicketStatus.PENDING;
                break;
                case "En Atencion":  tStatus = TicketStatus.PROCESSING;
                break;
                case "Atendido":  tStatus = TicketStatus.RESOLVED;
                break;      
                }
        this.status = tStatus; 
    }
    
    public String getTicketStatus() {
        TicketStatus _status = TicketStatus.PENDING;
        String stat =  " ";
        
        if (this.status == TicketStatus.PENDING) {
            stat = "Pendiente";
        }else if (this.status == TicketStatus.PROCESSING) {
            stat = "En Atencion";
        } else if (this.status == TicketStatus.RESOLVED) {
            stat = "Atendido";
        }
       return stat;
    }
    
    
    public String getSubject() {
        return this.subjet;
    }
    
    @Override
    public int getAttentionMetric(TicketCategory _category, int _timeSpent) {
        
        int realTimeSpent = 0;
        
        if (this.category == TicketCategory.URGENT) {
            realTimeSpent = _timeSpent - 10;
        } else if (this.category == TicketCategory.MEDIUM) {
            realTimeSpent = _timeSpent * 10 - 12;
        } else if (this.category == TicketCategory.LOW) {
            realTimeSpent = _timeSpent * 3 - 15;
        } 
        return realTimeSpent;
    }
    
    
   public void setDateResolved(Date _date) {
   
       this.dateResolved = _date;
   }
 
   public Date getDateResolved() {return this.dateResolved;}
   
   public void setTimeSolved( int _seconds) {
       this.timeBeingProcessed = _seconds;
   }
    
   public void setComentary(String _comment) {
       this.comment = _comment;
   }
   
   public String getTicketComment() {
       return this.comment;
   }
   
   public String setComplain(String _complain) {
       this.complain = _complain;
               
   }
   
   
   
   
   
    
}
    
