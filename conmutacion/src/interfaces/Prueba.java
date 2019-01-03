/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import clases.Conectar;
import clases.Consulta;
import clases.SSHConnector;
import clases.User;
import java.io.*;
import com.jcraft.jsch.JSchException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author HOME
 */
public class Prueba extends javax.swing.JFrame {

    public static ArrayList<User> usuariosBD = new ArrayList<User>();
    Conectar con;
    Consulta consulta;
    SSHConnector sshConnector;
    SSHform sshform;
    String IP_ROUTER;
    String nameRouter;

    /**
     * Creates new form Prueba
     */
    public Prueba() {
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

        jButton1 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(400, 100, 800, 540));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(400, 100));
        setMaximizedBounds(new java.awt.Rectangle(400, 100, 800, 540));
        setMinimumSize(new java.awt.Dimension(800, 540));
        setResizable(false);
        setSize(new java.awt.Dimension(800, 540));
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Algerian", 0, 24)); // NOI18N
        jButton1.setText("Conectar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(290, 350, 190, 50);

        jLabel1.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel1.setForeground(java.awt.Color.cyan);
        jLabel1.setText("ROUTER");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 100, 190, 60);

        jLabel2.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel2.setForeground(java.awt.Color.cyan);
        jLabel2.setText("Contraseña");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(160, 240, 227, 48);

        jTextField2.setFont(new java.awt.Font("Bookshelf Symbol 7", 0, 30)); // NOI18N
        getContentPane().add(jTextField2);
        jTextField2.setBounds(440, 240, 210, 50);

        jLabel4.setFont(new java.awt.Font("Algerian", 0, 36)); // NOI18N
        jLabel4.setForeground(java.awt.Color.cyan);
        jLabel4.setText("Usuario");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(160, 170, 190, 60);

        jTextField1.setFont(new java.awt.Font("Times New Roman", 0, 30)); // NOI18N
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField1);
        jTextField1.setBounds(440, 170, 210, 50);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccione", "P", "PE-UIO", "PE-CUE" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox1);
        jComboBox1.setBounds(440, 100, 210, 50);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        if (!jComboBox1.getSelectedItem().toString().equals("Seleccione")) {
            if (jTextField1.getText().equals("") || jTextField2.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Falta información, inténtelo de nuevo");
            } else {
                try {
                    con = new Conectar();
                    Connection reg = con.getConnection();
                    consulta = new Consulta();
                    consulta.ejecutarConsulta(reg);

                    reg.close();
                    User x = validaUser();

                    if (x != null) {
                        sshform = new SSHform();

                        try {
                            sshConnector = new SSHConnector();
                            sshConnector.connect(x, IP_ROUTER, 22);
                            sshform.setUser(x);
                            sshform.setIpRouter(IP_ROUTER);
                            sshform.setSSHConnector(sshConnector);
                            sshform.getRouterText().setEditable(false);

                            
                            //String result = sshConnector.executeCommand("show run\n       ");
                            //sshform.getJTextArea().setText(result);
                            sshConnector.disconnect();

                            //System.out.println(result);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            System.out.println(ex.getMessage());
                        } 
//                        catch (IllegalAccessException ex) {
//                            ex.printStackTrace();
//
//                            System.out.println(ex.getMessage());
//                        } catch (IOException ex) {
//                            ex.printStackTrace();
//
//                            System.out.println(ex.getMessage());
//                        }
                        //Obtener la hora y fecha actual
                        //Fecha
                        Date sistFecha = new Date();
                        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd-MMM-YYYY");
                        //Hora
                        Date sistHora = new Date();
                        SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm:ss a");
                        Calendar hoy = Calendar.getInstance();

                        //Guarda el registro de ingreso del usuario
                        try {
                            File archivo;
                            FileWriter escribir;
                            PrintWriter linea;

                            archivo = new File("Registro.txt");
                            if (!archivo.exists()) {
                                archivo.createNewFile();
                            }
                            escribir = new FileWriter(archivo, true);
                            linea = new PrintWriter(escribir);
                            //escribir fecha y hora
                            linea.println(formatoFecha.format(sistFecha) + "  " + String.format(formatoHora.format(sistHora), hoy));
                            //escribir usuario que ingreso al sistema
                            linea.println("Ingreso del usuario:  " + jTextField1.getText());

                            linea.close();
                            escribir.close();

                        } catch (Exception e) {
                            JOptionPane.showMessageDialog(null, "Error al guaradar el registro");
                        }

                        sshform.setVisible(true);
                        this.dispose();
                    }
                    sshform.setVisible(true);
                    this.setVisible(false);

                } catch (SQLException ex) {
                    Logger.getLogger(Prueba.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione el router");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        if (jComboBox1.getSelectedItem().toString().equals("P")) {
            IP_ROUTER = "172.16.0.9";
            nameRouter = "P";
        } else if (jComboBox1.getSelectedItem().toString().equals("PE-UIO")) {
            IP_ROUTER = "172.16.0.2";
            nameRouter = "PE-UIO";
        } else if (jComboBox1.getSelectedItem().toString().equals("PE-CUE")) {
            IP_ROUTER = "172.16.0.6";
            nameRouter = "PE-CUE";
        } else {
            IP_ROUTER = "";
            nameRouter = "";
        }


    }//GEN-LAST:event_jComboBox1ActionPerformed

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
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Prueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Prueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

    public User validaUser() {

        for (int i = 0; i < usuariosBD.size(); i++) {

            if (jTextField1.getText().equals(usuariosBD.get(i).getUsuario())) {
                if (jTextField2.getText().equals(usuariosBD.get(i).getContrasena())) {
                    JOptionPane.showMessageDialog(null, "Ingresando");
                    return usuariosBD.get(i);

                } else {
                    JOptionPane.showMessageDialog(null, "El usuario y contraseña son incorrectos");
                    return null;
                }
            }
        }
        JOptionPane.showMessageDialog(null, "No existe Usuario");
        return null;
    }

}
