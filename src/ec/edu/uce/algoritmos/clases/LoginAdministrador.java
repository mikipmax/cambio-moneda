package ec.edu.uce.algoritmos.clases;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de
 * monedas
 *
 * Clase con interfaz gráfica que muestra el login para que solo el
 * Administrador pueda acceder al sistema administrativo FECHA:10-FEB-2017
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 */
public class LoginAdministrador extends javax.swing.JDialog {

    /**
     * Constructor de la clase LoginAdministrador
     *
     * @param parent
     * @param modal
     */
    public LoginAdministrador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.addKeyListener(new PresionarTecla());
        setLocationRelativeTo(null);
        setTitle("Acceso");

        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();
        msjError = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(289, 280));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("ACCESO AL SISTEMA ADMINISTRATIVO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 40, 230, 14);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(61, 91, 70, 14);

        user.setForeground(new java.awt.Color(255, 255, 255));
        user.setCaretColor(new java.awt.Color(255, 51, 51));
        user.setOpaque(false);
        getContentPane().add(user);
        user.setBounds(149, 88, 77, 20);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Contraseña");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(61, 136, 80, 14);

        pass.setForeground(new java.awt.Color(255, 255, 255));
        pass.setCaretColor(new java.awt.Color(255, 51, 51));
        pass.setOpaque(false);
        getContentPane().add(pass);
        pass.setBounds(147, 133, 77, 20);

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        jButton1.setText("Iniciar Sesión");
        jButton1.setBorder(null);
        jButton1.setBorderPainted(false);
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        jButton1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(110, 200, 135, 25);
        jButton1.getAccessibleContext().setAccessibleDescription("");

        msjError.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(msjError);
        msjError.setBounds(71, 164, 163, 21);

        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        jButton2.setText("Regresar");
        jButton2.setBorder(null);
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        jButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(30, 200, 70, 25);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/f2.png"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(20, 30, 240, 200);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/77506782d130c09bdd5bf91562a7f3d4.jpg"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, 0, 290, 260);

        pack();
    }// </editor-fold>//GEN-END:initComponents
/**
     * Evento del boton Iniciar Sesión Permite ingreasr al modo administrador si
     * la claves y usuarios coinciden con los ya predefinidos.
     *
     * @param evt
     */
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed

        String usu1 = "michaelponce";
        String usu2 = "estefaniaportilla";
        String usu3 = "davidmorales";
        String usu = (user.getText() + pass.getText()).toLowerCase();

        // se compara el usuario y contraseña que sean correcta para acceder al modo Administrador.
        if (usu.equals(usu1) || usu.equals(usu2) || usu.equals(usu3)) {
            dispose();
            new ModoAdministrador(null, rootPaneCheckingEnabled).setVisible(true);

        } else {
            user.setText("");
            pass.setText("");
            msjError.setText("Ingrese unas credenciales válidas");

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        
    }//GEN-LAST:event_formKeyPressed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel msjError;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
public class PresionarTecla extends KeyAdapter {
 
      public void keyPressed(KeyEvent ke) {
          if (ke.getKeyCode() == KeyEvent.VK_ENTER) {
             jButton1ActionPerformed(null);
          }
      }
}
}
