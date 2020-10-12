
package ec.edu.uce.algoritmos.clases;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de monedas
 * 
 * Clase con interfaz gráfica que muestra la presentación del equipo de desarrollo de esta aplicación.
 * FECHA:10-FEB-2017
 * 
 * @author: 
 * 1725149502 David Morales
 * 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 */

public class AcercaDe extends javax.swing.JDialog {

  /**
   * Constructor de la Clase Acerca de...
   * @param parent
   * @param modal 
   */
    public AcercaDe(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Acerca de");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(470, 620));
        setMinimumSize(new java.awt.Dimension(470, 620));
        setPreferredSize(new java.awt.Dimension(470, 620));
        setResizable(false);
        getContentPane().setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("EDM APP'S PRESENTA:");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(160, 30, 140, 14);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/Acercad.png"))); // NOI18N
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 70, 320, 270);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("cantidad de monedas posibles.");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(50, 380, 360, 20);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("SISTEMA DE CAMBIO DE MONEDA");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(220, 50, 200, 20);

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Es un sistema que le permite al cliente cambiar sus billetes, ");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(50, 340, 360, 20);

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("ya sean de 5, 10, 20, 50 o 100 dólares, con la mínima ");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(50, 360, 370, 20);

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Monospaced", 3, 13)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setText("Versión del Producto: Cambio Moneda 1.0\nVersión Java: 1.8.0\nDesarrollado por: EDM APP'S\nIntegrantes: Michael, Stefy, David.");
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(60, 430, 350, 90);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/f3.png"))); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(20, 20, 430, 520);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/77506782d130c09bdd5bf91562a7f3d4.jpg"))); // NOI18N
        getContentPane().add(jLabel7);
        jLabel7.setBounds(0, 0, 480, 590);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables
}
