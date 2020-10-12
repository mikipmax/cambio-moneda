package ec.edu.uce.algoritmos.clases;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de
 * monedas
 *
 * Clase con interfaz gráfica que permite al usuario cambiar un billete en
 * monedas y si es posible también en billetes FECHA:10-FEB-2017
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 */
public class VisualizarMonedasBilletes extends javax.swing.JFrame {

    float denominacion[] = {100F, 50F, 20F, 10F, 5F, 1F, 0.50F, 0.25F, 0.10F, 0.05F, 0.01F};
    int[] cambio;
    int[] queda;
    
    /**
     * Constructor de la Clase VisualizarMoendasBilletes
     *
     * @param parent
     * @param modal
     */
    public VisualizarMonedasBilletes(java.awt.Frame parent, boolean modal) {
        initComponents();
         setIconImage(new ImageIcon(getClass().getResource("/img/star.jpg")).getImage());
        setTitle("Modo: Monedas y Billetes");
        setLocationRelativeTo(null);
        setResizable(false);
        cambio = new int[11];
        queda = new int[11];
        
    }

    /**
     * Método que permite leer de un archivo, las fechas en las que se han
     * realizado todas las transacciones Este método llama al método
     * insertarEnTabla
     *
     * @return
     */
    int[] Extraer() {
        String linea;
        int arr[] = new int[11];
        try {
            FileReader fr = new FileReader("archivo.txt");
            BufferedReader br = new BufferedReader(fr);
            int i = 0;
            while ((linea = br.readLine()) != null) {
                //Extracción de los datos del archivo
                StringTokenizer st = new StringTokenizer(linea, "|");
                float din = Float.parseFloat(st.nextToken());
                int can = Integer.parseInt(st.nextToken());
                arr[i] = can;
                i++;
            }

            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al abrir archivo");

        } catch (java.util.NoSuchElementException e1) {//EXCEPCION DE CUANDO NO HAY NADA ESCRITO EN EL ARCHIVO

        }
        return arr;
    }

    /**
     * Algoritmo Principal del Cambio de Moneda
     *
     * @param d
     * @return
     */
    int[] algoritmo(float d) {
        //Inicialización de variables
        float dinero[] = this.denominacion;
        int cantidadinicial[] = this.Extraer();
        int cantidad[] = this.Extraer();
        int cam[] = new int[11];
        /*
         System.out.println("Cantidad inicial de billetes:");
         for (int i = 0; i < dinero.length; i++) {
         System.out.println("De $" + dinero[i] + " = " + cantidadinicial[i] + " billetes");
         }
         */
        float c = d;
        float cambiar = c;
        int cont = 0, k = 0;
        while (dinero[k] != c) {
            k++;
        }
        //
        while (cambiar > 0) {
            while ((dinero[cont] >= cambiar) && (cont < (dinero.length - 1))) {
                cont++;
            }
            for (int j = cont; j < dinero.length; j++) {
                if ((cantidad[j] > 0) && (cambiar > ((dinero[j]) - 0.001F))) {
                    cantidad[j] = cantidad[j] - 1;
                    float aux = dinero[j];
                    float resta = cambiar - aux;
                    if (resta >= 0.009) {
                        cambiar = resta;
                    } else {
                        cambiar = 0;
                    }
                }
            }
        }

        for (int i = 0; i < dinero.length; i++) {
            cam[i] = cantidadinicial[i] - cantidad[i];

        }
        txtCCinc.setText(String.valueOf(cam[1]));
        txtCVeinte.setText(String.valueOf(cam[2]));
        txtCDiez.setText(String.valueOf(cam[3]));
        txtCCinco.setText(String.valueOf(cam[4]));
        txtUn2.setText(String.valueOf(cam[5]));
        txtCin1.setText(String.valueOf(cam[6]));
        txtVein1.setText(String.valueOf(cam[7]));
        txtDiez1.setText(String.valueOf(cam[8]));
        txtCinco1.setText(String.valueOf(cam[9]));
        txtUnn.setText(String.valueOf(cam[10]));
        System.out.println("\nCantidad final de billetes:");
        for (int i = 0; i < dinero.length; i++) {
            if (i == k) {
                queda[i] = cantidad[i] + 1;
            } else {
                queda[i] = cantidad[i];
            }
            System.out.println("De $" + dinero[i] + " = " + queda[i] + " billetes");
        }
        this.cambio = cam;
        return cambio;
    }

    /**
     * Suma la cantidad actual más la cantidad de dinero ingresada en la recarga
     * y las almacena en archivo
     */
    void AñadirInfo(int[] mon, float in) {
        int j = 0;
        while (denominacion[j] != in) {
            j++;
        }
        String d[] = {"100.0", "50.0", "20.0", "10.0", "5.0", "1.0", "0.50", "0.25", "0.10", "0.05", "0.01"};
        int ini[] = this.Extraer();
        int fin[] = new int[11];
        for (int i = 0; i < 11; i++) {
            if (i == j) {
                fin[i] = ini[i] - mon[i] + 1;
            } else {
                fin[i] = ini[i] - mon[i];
            }
        }
        try {
            File f = new File("archivo.txt");
            if (f.exists()) {
                f.delete();
                File f2 = new File("archivo.txt");
                FileWriter fw = new FileWriter(f2, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < 11; i++) {
                    String dato = d[i] + "|" + String.valueOf(fin[i]);
                    bw.write(dato);
                    bw.newLine();
                }
                bw.close();
                fw.close();
            } else {
                FileWriter fw = new FileWriter(f, true);
                BufferedWriter bw = new BufferedWriter(fw);
                for (int i = 0; i < 11; i++) {
                    String dato = d[i] + "|" + String.valueOf(fin[i]);
                    bw.write(dato);
                    bw.newLine();
                }
                bw.close();
                fw.close();
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    void AñadirInfo2(float in) {
        int j = 0;
        while (denominacion[j] != in) {
            j++;
        }
        Date dat = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String mon[] = {"100.0", "50.0", "20.0", "10.0", "5.0", "1.0", "0.50", "0.25", "0.10", "0.05", "0.01"};

        try {
            File f = new File("información.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            String dato = mon[j] + " " + hourFormat.format(dat) + " " + dateFormat.format(dat);
            bw.write(dato);
            bw.newLine();

            bw.close();
            fw.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        RbCinco = new javax.swing.JRadioButton();
        RbDiez = new javax.swing.JRadioButton();
        RbVeinte = new javax.swing.JRadioButton();
        RbCincuenta = new javax.swing.JRadioButton();
        txtPresentacion = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        RbCien = new javax.swing.JRadioButton();
        btnIns = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel9 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        txtUn2 = new javax.swing.JLabel();
        txtCCinco = new javax.swing.JLabel();
        txtCDiez = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtCVeinte = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCCinc = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUnn = new javax.swing.JLabel();
        txtCin1 = new javax.swing.JLabel();
        txtVein1 = new javax.swing.JLabel();
        txtDiez1 = new javax.swing.JLabel();
        txtCinco1 = new javax.swing.JLabel();
        txtInfo1 = new javax.swing.JLabel();
        txtInfo2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton11 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem5 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(740, 590));
        getContentPane().setLayout(null);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10 dólares.jpg"))); // NOI18N
        jButton1.setBorderPainted(false);
        jButton1.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton1.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton1.setPreferredSize(new java.awt.Dimension(110, 50));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(30, 170, 110, 50);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/100 dolares.jpg"))); // NOI18N
        jButton2.setBorderPainted(false);
        jButton2.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton2.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton2.setPreferredSize(new java.awt.Dimension(110, 50));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(30, 340, 110, 50);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5 dólares.jpg"))); // NOI18N
        jButton3.setBorderPainted(false);
        jButton3.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton3.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton3.setPreferredSize(new java.awt.Dimension(110, 50));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(30, 110, 110, 50);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/20 dólares.jpg"))); // NOI18N
        jButton4.setBorderPainted(false);
        jButton4.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton4.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton4.setPreferredSize(new java.awt.Dimension(110, 50));
        getContentPane().add(jButton4);
        jButton4.setBounds(30, 230, 110, 50);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Seleccione el Billete a Cambiar");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(20, 80, 190, 14);

        buttonGroup1.add(RbCinco);
        RbCinco.setMnemonic(5);
        RbCinco.setContentAreaFilled(false);
        RbCinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbCincoActionPerformed(evt);
            }
        });
        getContentPane().add(RbCinco);
        RbCinco.setBounds(150, 130, 21, 21);

        buttonGroup1.add(RbDiez);
        RbDiez.setMnemonic(10);
        RbDiez.setContentAreaFilled(false);
        RbDiez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbDiezActionPerformed(evt);
            }
        });
        getContentPane().add(RbDiez);
        RbDiez.setBounds(150, 180, 21, 21);

        buttonGroup1.add(RbVeinte);
        RbVeinte.setMnemonic(20);
        RbVeinte.setContentAreaFilled(false);
        RbVeinte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbVeinteActionPerformed(evt);
            }
        });
        getContentPane().add(RbVeinte);
        RbVeinte.setBounds(150, 240, 21, 21);

        buttonGroup1.add(RbCincuenta);
        RbCincuenta.setMnemonic(50);
        RbCincuenta.setContentAreaFilled(false);
        RbCincuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbCincuentaActionPerformed(evt);
            }
        });
        getContentPane().add(RbCincuenta);
        RbCincuenta.setBounds(150, 290, 21, 21);

        txtPresentacion.setForeground(new java.awt.Color(255, 255, 255));
        txtPresentacion.setText("Bienvenido al Modo: Billetes y Monedas");
        getContentPane().add(txtPresentacion);
        txtPresentacion.setBounds(250, 60, 230, 14);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/50 dólares.jpg"))); // NOI18N
        jButton6.setBorderPainted(false);
        jButton6.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton6.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton6.setPreferredSize(new java.awt.Dimension(110, 50));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(30, 280, 110, 50);

        buttonGroup1.add(RbCien);
        RbCien.setMnemonic(100);
        RbCien.setContentAreaFilled(false);
        RbCien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbCienActionPerformed(evt);
            }
        });
        getContentPane().add(RbCien);
        RbCien.setBounds(150, 350, 21, 21);

        btnIns.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnIns.setForeground(new java.awt.Color(255, 255, 255));
        btnIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        btnIns.setText("Inserte el billete");
        btnIns.setBorderPainted(false);
        btnIns.setContentAreaFilled(false);
        btnIns.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIns.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIns.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        btnIns.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        btnIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsActionPerformed(evt);
            }
        });
        getContentPane().add(btnIns);
        btnIns.setBounds(290, 380, 150, 33);
        getContentPane().add(jSeparator2);
        jSeparator2.setBounds(0, 0, 0, 2);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/atm.png"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(180, 70, 300, 380);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5 dólares.jpg"))); // NOI18N
        jButton5.setBorderPainted(false);
        jButton5.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton5.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton5.setPreferredSize(new java.awt.Dimension(110, 50));
        getContentPane().add(jButton5);
        jButton5.setBounds(590, 180, 110, 50);

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10 dólares.jpg"))); // NOI18N
        jButton8.setBorderPainted(false);
        jButton8.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton8.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton8.setPreferredSize(new java.awt.Dimension(110, 50));
        getContentPane().add(jButton8);
        jButton8.setBounds(470, 180, 110, 50);

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/20 dólares.jpg"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton9.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton9.setPreferredSize(new java.awt.Dimension(110, 50));
        getContentPane().add(jButton9);
        jButton9.setBounds(590, 100, 110, 50);

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/50 dólares.jpg"))); // NOI18N
        jButton10.setBorderPainted(false);
        jButton10.setMaximumSize(new java.awt.Dimension(110, 50));
        jButton10.setMinimumSize(new java.awt.Dimension(110, 50));
        jButton10.setPreferredSize(new java.awt.Dimension(110, 50));
        getContentPane().add(jButton10);
        jButton10.setBounds(470, 100, 110, 50);

        txtUn2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtUn2);
        txtUn2.setBounds(520, 320, 50, 21);

        txtCCinco.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCCinco);
        txtCCinco.setBounds(640, 230, 50, 21);

        txtCDiez.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCDiez);
        txtCDiez.setBounds(520, 230, 50, 21);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1 d.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(500, 260, 55, 55);

        txtCVeinte.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCVeinte);
        txtCVeinte.setBounds(640, 150, 50, 21);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1 ctv.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(620, 440, 46, 46);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/50 ctv.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(610, 260, 60, 60);

        txtCCinc.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCCinc);
        txtCCinc.setBounds(520, 150, 50, 21);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/25 ctv.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(500, 350, 51, 51);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10 ctv.png"))); // NOI18N
        jLabel7.setText("jLabel3");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(620, 350, 42, 42);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5 ctv.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(500, 440, 46, 46);

        txtUnn.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtUnn);
        txtUnn.setBounds(640, 490, 50, 21);

        txtCin1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCin1);
        txtCin1.setBounds(640, 320, 50, 21);

        txtVein1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtVein1);
        txtVein1.setBounds(520, 410, 50, 21);

        txtDiez1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtDiez1);
        txtDiez1.setBounds(640, 410, 50, 21);

        txtCinco1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCinco1);
        txtCinco1.setBounds(520, 490, 50, 21);

        txtInfo1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtInfo1);
        txtInfo1.setBounds(240, 450, 200, 20);

        txtInfo2.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtInfo2);
        txtInfo2.setBounds(500, 70, 200, 20);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/black70 copia.png"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 730, 40);

        jToolBar1.setBackground(new java.awt.Color(153, 153, 153));
        jToolBar1.setRollover(true);
        jToolBar1.setAutoscrolls(true);
        jToolBar1.setEnabled(false);
        jToolBar1.setOpaque(false);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/ModoM.png"))); // NOI18N
        jButton11.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton11.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton11.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton11);

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/admin.png"))); // NOI18N
        jButton7.setMaximumSize(new java.awt.Dimension(30, 30));
        jButton7.setMinimumSize(new java.awt.Dimension(30, 30));
        jButton7.setPreferredSize(new java.awt.Dimension(30, 30));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton7);

        getContentPane().add(jToolBar1);
        jToolBar1.setBounds(0, 0, 750, 40);

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sfondo.png"))); // NOI18N
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 60, 690, 460);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        getContentPane().add(jLabel11);
        jLabel11.setBounds(0, 0, 750, 540);

        jMenu1.setText("Opciones");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Modo: Solo Monedas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator6);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Modo: Administrador");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);
        jMenu1.add(jSeparator5);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Salir");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator1);

        jMenuBar1.add(jMenu1);

        jMenu3.setText("Ayuda");

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem3.setText("Información General");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem3);
        jMenu3.add(jSeparator4);

        jMenuItem5.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem5.setText("Acerca de .......");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem5);
        jMenu3.add(jSeparator3);

        jMenuBar1.add(jMenu3);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

    }//GEN-LAST:event_jButton2ActionPerformed
    /**
     * Evento que simula el ingreso de un Billete a cambiar
     *
     * @param evt
     */
    private void btnInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsActionPerformed
        if (RbVeinte.isSelected() || RbCincuenta.isSelected() || RbCinco.isSelected() || RbDiez.isSelected() || RbCien.isSelected()) {
            int c[] = this.Extraer();
            float suma = 0;

            float f = (float) buttonGroup1.getSelection().getMnemonic();
            System.out.println(f);
            int j = 0;
            while (denominacion[j] != f) {
                j++;
            }
            for (int i = j; i < 11; i++) {
                float aux = c[i] * denominacion[i];
                suma = suma + aux;
            }
            if (suma >= f) {
                this.algoritmo(f);
                this.AñadirInfo(cambio, f);
                this.AñadirInfo2(f);
            } else {
                JOptionPane.showMessageDialog(rootPane, "►Ahorita NO HAY monedas suficientes◄");
            }
            txtInfo2.setText("Retire su cambio de " + (int) f + " Dólares");
            txtInfo1.setText("! Gracias por preferirnos ¡");
            buttonGroup1.clearSelection();
        } else {
            JOptionPane.showMessageDialog(null, "Primero elija un billete a cambiar.");
        }


    }//GEN-LAST:event_btnInsActionPerformed

    private void RbCincuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbCincuentaActionPerformed

    }//GEN-LAST:event_RbCincuentaActionPerformed

    private void RbCienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbCienActionPerformed

    }//GEN-LAST:event_RbCienActionPerformed

    private void RbCincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbCincoActionPerformed

    }//GEN-LAST:event_RbCincoActionPerformed

    private void RbDiezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbDiezActionPerformed

    }//GEN-LAST:event_RbDiezActionPerformed

    private void RbVeinteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RbVeinteActionPerformed

    }//GEN-LAST:event_RbVeinteActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        new AcercaDe(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new VisualizarSoloMonedas().setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        new LoginAdministrador(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new LoginAdministrador(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        new VisualizarSoloMonedas().setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new VisualizarSoloMonedas().abrirArchivo("Manual.pdf");
    }//GEN-LAST:event_jMenuItem3ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton RbCien;
    private javax.swing.JRadioButton RbCinco;
    private javax.swing.JRadioButton RbCincuenta;
    private javax.swing.JRadioButton RbDiez;
    private javax.swing.JRadioButton RbVeinte;
    private javax.swing.JButton btnIns;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel txtCCinc;
    private javax.swing.JLabel txtCCinco;
    private javax.swing.JLabel txtCDiez;
    private javax.swing.JLabel txtCVeinte;
    private javax.swing.JLabel txtCin1;
    private javax.swing.JLabel txtCinco1;
    private javax.swing.JLabel txtDiez1;
    private javax.swing.JLabel txtInfo1;
    private javax.swing.JLabel txtInfo2;
    private javax.swing.JLabel txtPresentacion;
    private javax.swing.JLabel txtUn2;
    private javax.swing.JLabel txtUnn;
    private javax.swing.JLabel txtVein1;
    // End of variables declaration//GEN-END:variables
}
