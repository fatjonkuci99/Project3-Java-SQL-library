package com.mycompany.java_project_3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.DatabaseMetaData;
import javax.swing.JOptionPane;

/*
Librari e ruajtur ne database, se bashktu me nje log in form
Authors: Fatjon Kuci, Florjan Hysaj, Isarda Kuci, Zllatan Keraj 
Viti II 
Infromatike Java 
02.05.2021 
Fillimisht nje perdorues i ri duhet te regjistrohet dhe te krijoje nje llogari 
(te gjitha llogarite ruhen ne nje database), ose ne rast te nje perdoruesi 
ekzistues te dhenat e tij/saj i japin akses nje librarie, ne te cilen mund te 
shtosh ose te modifikosh librat ekzistues.
 */
public class Login extends javax.swing.JFrame {

    static final String DB1="jdbc:mysql://127.0.0.1:3306/perdor";
    static final String User="root";
    static final String Pass="Java1234";
    static final String DB="jdbc:mysql://127.0.0.1:3306/JavaProject";
    Connection conn=null;
    Connection conn1=null;
    Statement stm=null;
    Statement stm2=null;
    Statement stm3=null;
    Statement stm4=null;
    PreparedStatement pst=null;
    ResultSet rs=null;
    boolean db_exist=false;
    int clicked=0;
    
    static Connection Connector(){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connec=DriverManager.getConnection(DB, User, Pass);
        return connec;
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
            return null;
        }     
    }
    
    private void datacreate(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn=DriverManager.getConnection(DB1, User, Pass);
            rs=conn.getMetaData().getCatalogs();
            while(rs.next()){
                String dbname=rs.getString(1);
                if(dbname.equals("javaproject")){
                    db_exist=true;
                }
            }
            if(!db_exist){
                    String sql="create database JavaProject;";
                    stm=conn.createStatement();
                    stm.executeUpdate(sql);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try { rs.close(); } catch (Exception e) { /* Ignored */ }
            try { stm.close(); } catch (Exception e) { /* Ignored */ }
            try { conn.close(); } catch (Exception e) { /* Ignored */ }
        }
    }
    
    private void datacreate2(){
        try{
            conn=Connector();
            DatabaseMetaData dbm = conn.getMetaData();
            ResultSet table1 = dbm.getTables(null, null, "perdorues", null);
            if (!table1.next()) {
                String sql="CREATE TABLE `perdorues` (\n" 
                +"  `username` varchar(30) NOT NULL,\n" 
                +"  `password` varchar(30) DEFAULT NULL,\n" 
                +"  PRIMARY KEY (`username`)\n" 
                +") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
                stm=conn.createStatement();
                stm.executeUpdate(sql);
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try { stm.close(); } catch (Exception e) { /* Ignored */ }
            try { conn.close(); } catch (Exception e) { /* Ignored */ }
        }
    }
    
    private void datacreate3(){
        try{
            conn=Connector();
            DatabaseMetaData dbm2 = conn.getMetaData();
            ResultSet table2 = dbm2.getTables(null, null, "librari", null);
            if (!table2.next()) {
                String sql="CREATE TABLE `librari` (\n" 
                +"  `ID` int NOT NULL,\n" 
                +"  `Titulli` varchar(70) DEFAULT NULL,\n" 
                +"  `ISBN` int DEFAULT NULL,\n" 
                +"  `Autori` varchar(70) DEFAULT NULL,\n" 
                +"  `Vit_botimit` int DEFAULT NULL,\n" 
                +"  `Shtepia_botuese` varchar(40) DEFAULT NULL,\n" 
                +"  `Cmimi` int DEFAULT NULL,\n" 
                +"  `Numri_kopjeve` int DEFAULT NULL,\n" 
                +"  PRIMARY KEY (`ID`)\n" +") ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;";
                String sql2="insert into librari values (1,'The Brothers Karamazov',14044924,'Fyodor Dostoyevsky',1880,'Penguin',8,4), (2,'The Great Gatsby',908312703,'Francis Fitzgerald',1925,'Harper Collins',6,4), (3,'To Kill A Mockingbird',312578865,'Harper Lee',1966,'Klett',9,4), (4,'The Catcher in the Rye',24198475,'J.D. Salinger',1946,'Penguin',12,5), (5,'1984',373060976,'George Orwell',1949,'Hachette',7,2);";
                stm=conn.createStatement();
                stm2=conn.createStatement();
                stm.executeUpdate(sql);
                stm2.executeUpdate(sql2);
                
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        }
        finally{
            try { stm.close(); } catch (Exception e) { /* Ignored */ }
            try { stm2.close(); } catch (Exception e) { /* Ignored */ }
            try { conn.close(); } catch (Exception e) { /* Ignored */ }
        }
    }

    public Login() {
        initComponents();
        datacreate();
        datacreate2();
        datacreate3();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jPasswordField1 = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Perdoruesi");

        jLabel2.setText("Password");

        jButton1.setText("Hyr");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Rregjistrohu");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addGap(18, 18, 18)
                                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jLabel2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(72, 72, 72)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton2)))
                .addContainerGap(72, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jPasswordField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(54, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try{
            conn=Connector();
            String sql="select username, password from perdorues where username LIKE '%"+jTextField1.getText()+"'";
            pst=conn.prepareStatement(sql);
            rs=pst.executeQuery();
            if(rs.next()){
                String psw=rs.getString("Password").toString().trim();
                String enteredPsw=jPasswordField1.getText().trim();
                if(psw.equals(enteredPsw)){
                    new Librari().setVisible(true);
                    dispose();
                }
                else{
                    clicked++;
                    if(clicked>=3){
                        JOptionPane.showMessageDialog(null,"Gabuat 3 here");
                        dispose();
                    }
                    else{
                       JOptionPane.showMessageDialog(null,"Password i gabuar.");
                    }
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Ky perdorues nuk ekzistion.");
            }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,e);
        } 
        finally{
            try { rs.close(); } catch (Exception e) { /* Ignored */ }
            try { pst.close(); } catch (Exception e) { /* Ignored */ }
            try { conn.close(); } catch (Exception e) { /* Ignored */ }
        }
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        new Register().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField jPasswordField1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
