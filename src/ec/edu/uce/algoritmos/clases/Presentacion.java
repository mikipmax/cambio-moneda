package ec.edu.uce.algoritmos.clases;

import java.applet.AudioClip;
import javax.swing.ImageIcon;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de
 * monedas
 *
 * Clase con interfaz gráfica que permite mostrar la Presentación a lo que se
 * inicia la aplicación FECHA:10-FEB-2017
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 */
public class Presentacion extends javax.swing.JFrame {

    /**
     * Constructor de la Claes Presentación
     */
    public Presentacion() {
        initComponents();
        setLocationRelativeTo(null);

        setResizable(false);
        setIconImage(new ImageIcon(getClass().getResource("/img/star.jpg")).getImage());
        AudioClip sonido;
        sonido = java.applet.Applet.newAudioClip(getClass().getResource("/Musica/tolouse.wav"));
        sonido.loop();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        trans = new javax.swing.JLabel();
        fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(332, 325));
        setUndecorated(true);
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Chiller", 0, 48)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 255, 255));
        jLabel1.setText("BIENVENIDOS");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(30, 20, 222, 56);

        jLabel2.setFont(new java.awt.Font("Snap ITC", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("INTEGRANTES:");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(80, 90, 168, 24);

        jLabel4.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(173, 231, 240));
        jLabel4.setText("Michael Ponce");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(120, 130, 132, 25);

        jLabel5.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(173, 231, 240));
        jLabel5.setText("Estefania Portilla");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(150, 210, 154, 25);

        jLabel3.setFont(new java.awt.Font("Kristen ITC", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(173, 231, 240));
        jLabel3.setText("David Morales");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(140, 170, 129, 25);

        trans.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/black70 copia.png"))); // NOI18N
        getContentPane().add(trans);
        trans.setBounds(-20, 0, 350, 300);

        fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/pres.png"))); // NOI18N
        getContentPane().add(fondo);
        fondo.setBounds(0, 0, 340, 300);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        for (int segundos = 100; segundos >= 0; segundos--) {
            float a = segundos * 0.01F;
            try {
                //Devuelve una accion cada segundo
                Thread.sleep(100);
                this.setOpacity(a);
            } catch (InterruptedException e) {
            }
        }

        VisualizarSoloMonedas l = new VisualizarSoloMonedas();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_formComponentShown


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel trans;
    // End of variables declaration//GEN-END:variables
}
