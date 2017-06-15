
import java.awt.Color;
import java.util.Collections;
import java.util.Locale;
import java.util.Vector;
import javax.swing.UIManager;
import java.awt.event.ActionListener;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author CASA
 */
public class InterfazServidor extends javax.swing.JDialog {

    /**
     * Creates new form InterfazServidor
     */
    public InterfazServidor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabRojos = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaTicketsPendientes = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        infoTicketsPendientes = new javax.swing.JTextArea();
        botonEnviarCola = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        colaRojos = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane5 = new javax.swing.JScrollPane();
        colaAmarillos = new javax.swing.JList<>();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        colaVerdes = new javax.swing.JList<>();
        jPanel2 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tabRojos.setBorder(javax.swing.BorderFactory.createCompoundBorder());

        listaTicketsPendientes.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaTicketsPendientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaTicketsPendientesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(listaTicketsPendientes);

        infoTicketsPendientes.setEditable(false);
        infoTicketsPendientes.setColumns(20);
        infoTicketsPendientes.setRows(5);
        jScrollPane2.setViewportView(infoTicketsPendientes);

        botonEnviarCola.setText("Enviar a cola");
        botonEnviarCola.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                botonEnviarColaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonEnviarCola)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(botonEnviarCola))
        );

        tabRojos.addTab("INBOX", jPanel1);

        jScrollPane3.setViewportView(colaRojos);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(192, Short.MAX_VALUE))
        );

        tabRojos.addTab("Rojos", jPanel3);

        jScrollPane5.setViewportView(colaAmarillos);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        tabRojos.addTab("Amarillos", jPanel4);

        jScrollPane6.setViewportView(colaVerdes);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        tabRojos.addTab("Verdes", jPanel5);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47))
            .addComponent(tabRojos)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(tabRojos, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabRojos.getAccessibleContext().setAccessibleName("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void listaTicketsPendientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaTicketsPendientesMouseClicked
        // TODO add your handling code here:
        listaTicketsPendientes.getSelectedIndices();
        listaTicketsPendientes.getSelectedIndex();
        listaTicketsPendientes.getMaxSelectionIndex();
        listaTicketsPendientes.isSelectedIndex(WIDTH);
        
        //String ticketSeleccionado = listaTicketsPendientes.getSelectedValuesList().get(listaTicketsPendientes.getMaxSelectionIndex());
        String ticketSeleccionado = listaTicketsPendientes.getSelectedValue();
        infoTicketsPendientes.setText(ticketSeleccionado);
        
        //Object value;
        //getListCellRendererComponent( listaTicketsPendientes, value, index, isSelected, cellHasFocus );
        //listaTicketsPendientes.getSelectedValuesList().get(0)
       // listaTicketsPendientes.getComponent(0).setBackground(Color.black);
       if (listaTicketsPendientes.getSelectionBackground() == UIManager.getColor("Panel.background")){
            listaTicketsPendientes.setSelectionBackground(Color.GREEN);
            botonEnviarCola.setText("Enviar a cola verde");
        }
       else if (listaTicketsPendientes.getSelectionBackground() == Color.GREEN){
            listaTicketsPendientes.setSelectionBackground(Color.yellow);
            botonEnviarCola.setText("Enviar a cola amarilla");
        }
        else if (listaTicketsPendientes.getSelectionBackground() == Color.yellow){
            listaTicketsPendientes.setSelectionBackground(Color.red);
            botonEnviarCola.setText("Enviar a cola roja");
        }
        else if (listaTicketsPendientes.getSelectionBackground() == Color.yellow){
            listaTicketsPendientes.setSelectionBackground(Color.green);
        }
        else{
            listaTicketsPendientes.setSelectionBackground(UIManager.getColor("Panel.background"));
        }
        
        
    }//GEN-LAST:event_listaTicketsPendientesMouseClicked
    void addListener(ActionListener listener){
        
    }
    private void botonEnviarColaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_botonEnviarColaMouseClicked
        // TODO add your handling code here:
        //listaTicketsPendientes.getSelectionModel().
        java.util.List<String> listaRevista = listaTicketsPendientes.getSelectedValuesList();
        botonEnviarCola.addActionListener(l);
        
        javax.swing.DefaultListModel<String> listaLibrosString =  new javax.swing.DefaultListModel<String>();
        
        int largo = listaRevista.size();
        if (largo>0) {
            for (int i = 0; i < largo; i++) {
               listaLibrosString.addElement(listaRevista.get(i));
                
            }
        }
        
        
        if (botonEnviarCola.getText() == "Enviar a cola verde"){
        colaVerdes.setModel(listaLibrosString);
        }
        else if (botonEnviarCola.getText() == "Enviar a cola amarilla"){
        colaAmarillos.setModel(listaLibrosString);
        }
        else if (botonEnviarCola.getText() == "Enviar a cola roja"){
        colaRojos.setModel(listaLibrosString);
        }
       
        
    }//GEN-LAST:event_botonEnviarColaMouseClicked

    /**
     * @param args the command line arguments
     */
  /* public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InterfazServidor dialog = new InterfazServidor(new javax.swing.JFrame(), true);
                Server server = new Server();
        server.initServer();
        SocketThread socket = new SocketThread(dialog, server);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
        
        
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonEnviarCola;
    private javax.swing.JList<String> colaAmarillos;
    private javax.swing.JList<String> colaRojos;
    private javax.swing.JList<String> colaVerdes;
    private javax.swing.JTextArea infoTicketsPendientes;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    public javax.swing.JList<String> listaTicketsPendientes;
    private javax.swing.JTabbedPane tabRojos;
    // End of variables declaration//GEN-END:variables
}
