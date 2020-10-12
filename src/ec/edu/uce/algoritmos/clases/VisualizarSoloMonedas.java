package ec.edu.uce.algoritmos.clases;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.StringTokenizer;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de
 * monedas
 *
 * Clase con interfaz gráfica que permite al usuario cambiar un billete en
 * monedas FECHA:10-FEB-2017
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 */
public class VisualizarSoloMonedas extends javax.swing.JFrame {

    float global;
    float denominacion[] = {100F, 50F, 20F, 10F, 5F, 1F, 0.50F, 0.25F, 0.10F, 0.05F, 0.01F};
    int[] cambio;
    int[] queda;

    /**
     * Constructor de la Clase VisualizarSoloMonedas
     */
    public VisualizarSoloMonedas() {

        initComponents();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
         setIconImage(new ImageIcon(getClass().getResource("/img/star.jpg")).getImage());
        setTitle("Modo: Solo Monedas");
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
        float dinero[] = this.denominacion;
        int cantidadinicial[] = this.Extraer();
        int cantidad[] = this.Extraer();
        int cam[] = new int[11]; 

        System.out.println("Cantidad inicial de billetes:");
        for (int i = 0; i < dinero.length; i++) {
            System.out.println("De $" + dinero[i] + " = " + cantidadinicial[i] + " billetes");
        }

        float c = d;
        float cambiar = c;
        int cont = 5, k = 0;
        while (dinero[k] != c) {
            k++;
        }
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

        System.out.println("El cambio de monedas es:");
        for (int i = 0; i < dinero.length; i++) {
            cam[i] = cantidadinicial[i] - cantidad[i];
            System.out.println("De $" + dinero[i] + " = " + cam[i] + " billetes");
        }
        this.txtUno.setText(String.valueOf(cam[5]));
        this.txtCincuenta.setText(String.valueOf(cam[6]));
        this.txtVCinco.setText(String.valueOf(cam[7]));
        this.txtDiez.setText(String.valueOf(cam[8]));
        this.txtCinco.setText(String.valueOf(cam[9]));
        this.txtUnCent.setText(String.valueOf(cam[10]));
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
           // Arrays.sort(mon);
            String dato = mon[j] + " " + hourFormat.format(dat) + " " + dateFormat.format(dat);
            bw.write(dato);
            bw.newLine();

            bw.close();
            fw.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void abrirArchivo(String archivo) {

        try {

            File objetofile = new File(archivo);
            Desktop.getDesktop().open(objetofile);

        } catch (IOException ex) {

            System.out.println(ex);

        }

    }

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        GrupoSMonedas = new javax.swing.ButtonGroup();
        txtInfo = new javax.swing.JLabel();
        RbCinco = new javax.swing.JRadioButton();
        RbDiez = new javax.swing.JRadioButton();
        RbVeinte = new javax.swing.JRadioButton();
        RbCincuenta = new javax.swing.JRadioButton();
        txtUnCent = new javax.swing.JLabel();
        txtUno = new javax.swing.JLabel();
        txtCincuenta = new javax.swing.JLabel();
        txtVCinco = new javax.swing.JLabel();
        txtDiez = new javax.swing.JLabel();
        txtCinco = new javax.swing.JLabel();
        RbCien = new javax.swing.JRadioButton();
        btnIns = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtInfo1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jToolBar1 = new javax.swing.JToolBar();
        jButton11 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        MBPrincipal = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem4 = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuItem6 = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem3 = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(670, 530));
        setResizable(false);
        getContentPane().setLayout(null);

        txtInfo.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtInfo);
        txtInfo.setBounds(460, 400, 180, 20);

        GrupoSMonedas.add(RbCinco);
        RbCinco.setMnemonic(5);
        RbCinco.setContentAreaFilled(false);
        RbCinco.setRolloverEnabled(false);
        RbCinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbCincoActionPerformed(evt);
            }
        });
        getContentPane().add(RbCinco);
        RbCinco.setBounds(140, 130, 20, 21);

        GrupoSMonedas.add(RbDiez);
        RbDiez.setMnemonic(10);
        RbDiez.setContentAreaFilled(false);
        RbDiez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbDiezActionPerformed(evt);
            }
        });
        getContentPane().add(RbDiez);
        RbDiez.setBounds(140, 180, 20, 21);

        GrupoSMonedas.add(RbVeinte);
        RbVeinte.setMnemonic(20);
        RbVeinte.setContentAreaFilled(false);
        RbVeinte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbVeinteActionPerformed(evt);
            }
        });
        getContentPane().add(RbVeinte);
        RbVeinte.setBounds(140, 240, 20, 21);

        GrupoSMonedas.add(RbCincuenta);
        RbCincuenta.setMnemonic(50);
        RbCincuenta.setContentAreaFilled(false);
        RbCincuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbCincuentaActionPerformed(evt);
            }
        });
        getContentPane().add(RbCincuenta);
        RbCincuenta.setBounds(140, 290, 20, 21);

        txtUnCent.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtUnCent);
        txtUnCent.setBounds(580, 350, 50, 21);

        txtUno.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtUno);
        txtUno.setBounds(470, 180, 50, 21);

        txtCincuenta.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCincuenta);
        txtCincuenta.setBounds(580, 180, 50, 21);

        txtVCinco.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtVCinco);
        txtVCinco.setBounds(470, 270, 50, 21);

        txtDiez.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtDiez);
        txtDiez.setBounds(580, 270, 50, 21);

        txtCinco.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtCinco);
        txtCinco.setBounds(470, 350, 50, 21);

        GrupoSMonedas.add(RbCien);
        RbCien.setMnemonic(100);
        RbCien.setContentAreaFilled(false);
        RbCien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RbCienActionPerformed(evt);
            }
        });
        getContentPane().add(RbCien);
        RbCien.setBounds(140, 350, 20, 21);

        btnIns.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnIns.setForeground(new java.awt.Color(255, 255, 255));
        btnIns.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        btnIns.setText("Inserte el Billete");
        btnIns.setBorderPainted(false);
        btnIns.setContentAreaFilled(false);
        btnIns.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnIns.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnIns.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        btnIns.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        btnIns.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsActionPerformed(evt);
            }
        });
        getContentPane().add(btnIns);
        btnIns.setBounds(260, 400, 160, 30);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/atm.png"))); // NOI18N
        getContentPane().add(jLabel9);
        jLabel9.setBounds(160, 90, 300, 380);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Bienvenido al  Modo: Solo Monedas");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(230, 80, 210, 14);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1 d.png"))); // NOI18N
        getContentPane().add(jLabel3);
        jLabel3.setBounds(450, 120, 55, 55);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1 ctv.png"))); // NOI18N
        getContentPane().add(jLabel4);
        jLabel4.setBounds(570, 300, 46, 46);

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/50 ctv.png"))); // NOI18N
        getContentPane().add(jLabel5);
        jLabel5.setBounds(560, 120, 60, 60);

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/25 ctv.png"))); // NOI18N
        getContentPane().add(jLabel6);
        jLabel6.setBounds(450, 210, 51, 51);

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10 ctv.png"))); // NOI18N
        jLabel7.setText("jLabel3");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(570, 210, 42, 42);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5 ctv.png"))); // NOI18N
        getContentPane().add(jLabel8);
        jLabel8.setBounds(450, 300, 46, 46);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Seleccione el Billete a Cambiar");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(20, 80, 200, 14);

        txtInfo1.setForeground(new java.awt.Color(255, 255, 255));
        getContentPane().add(txtInfo1);
        txtInfo1.setBounds(440, 80, 200, 20);

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

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/black70 copia.png"))); // NOI18N
        jLabel2.setText("jLabel2");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(0, -6, 670, 50);

        jToolBar1.setBackground(new java.awt.Color(153, 153, 153));
        jToolBar1.setRollover(true);
        jToolBar1.setAutoscrolls(true);
        jToolBar1.setEnabled(false);
        jToolBar1.setOpaque(false);

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/monedas-billetes.png"))); // NOI18N
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
        jToolBar1.setBounds(0, 0, 670, 40);

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sfondo.png"))); // NOI18N
        jLabel13.setText("jLabel13");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(20, 60, 630, 410);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo1.jpg"))); // NOI18N
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 670, 490);

        jMenu1.setText("Opciones");

        jMenuItem1.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_M, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem1.setText("Modo: Billetes y Monedas");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem1);
        jMenu1.add(jSeparator3);

        jMenuItem4.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem4.setText("Modo: Adminitrador");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);
        jMenu1.add(jSeparator4);

        jMenuItem6.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem6.setText("Salir");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem6);
        jMenu1.add(jSeparator5);

        MBPrincipal.add(jMenu1);

        jMenu2.setText("Ayuda");

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        jMenuItem2.setText("Información General");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem2);
        jMenu2.add(jSeparator1);

        jMenuItem3.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        jMenuItem3.setText("Acerca de........");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);
        jMenu2.add(jSeparator2);

        MBPrincipal.add(jMenu2);

        setJMenuBar(MBPrincipal);

        pack();
    }// </editor-fold>//GEN-END:initComponents

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

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        abrirArchivo("Manual.pdf");
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        new AcercaDe(this, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        new VisualizarMonedasBilletes(null, rootPaneCheckingEnabled).setVisible(true);
        dispose();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        new LoginAdministrador(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new LoginAdministrador(null, rootPaneCheckingEnabled).setVisible(true);
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        new VisualizarMonedasBilletes(this, rootPaneCheckingEnabled).setVisible(true);
        dispose();
    }//GEN-LAST:event_jButton11ActionPerformed

    private void btnInsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsActionPerformed
        if (RbVeinte.isSelected() || RbCincuenta.isSelected() || RbCinco.isSelected() || RbDiez.isSelected() || RbCien.isSelected()) {
            global = GrupoSMonedas.getSelection().getMnemonic();
            int c[] = this.Extraer();
            float suma = 0;

            float f = global;
            System.out.println(f);

            for (int i = 5; i < 11; i++) {
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

            txtInfo1.setText("Retire su cambio de " + (int) global + " Dólares");
            txtInfo.setText("! Gracias por preferirnos ¡");

            GrupoSMonedas.clearSelection();
        } else {
            JOptionPane.showMessageDialog(null, "Primero elija un billete a cambiar.");
        }


    }//GEN-LAST:event_btnInsActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup GrupoSMonedas;
    private javax.swing.JMenuBar MBPrincipal;
    private javax.swing.JRadioButton RbCien;
    private javax.swing.JRadioButton RbCinco;
    private javax.swing.JRadioButton RbCincuenta;
    private javax.swing.JRadioButton RbDiez;
    private javax.swing.JRadioButton RbVeinte;
    private javax.swing.JButton btnIns;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel txtCinco;
    private javax.swing.JLabel txtCincuenta;
    private javax.swing.JLabel txtDiez;
    private javax.swing.JLabel txtInfo;
    private javax.swing.JLabel txtInfo1;
    private javax.swing.JLabel txtUnCent;
    private javax.swing.JLabel txtUno;
    private javax.swing.JLabel txtVCinco;
    // End of variables declaration//GEN-END:variables
}
