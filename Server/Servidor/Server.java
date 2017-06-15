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

/**
 * Created by Sergio Hidalgo Fonseca on 14/6/2017.
 */
public class Server {

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
}
