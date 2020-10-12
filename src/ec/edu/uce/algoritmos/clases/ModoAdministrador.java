package ec.edu.uce.algoritmos.clases;

import ec.edu.uce.algoritmos.pojos.Moneda;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * PROYECTO: Programa de simulación de un sistema de máquina de cambio de
 * monedas
 *
 * Clase con interfaz gráfica que permite la revisión y supervisión del sistema
 * por parte de un super-usuario o administrador FECHA:10-FEB-2017
 *
 * @author: 1725149502 David Morales 1312960444 Michael Ponce Cevallos
 * 1721468021 Estefania Portilla
 * @version 1.0.0
 */
public class ModoAdministrador extends javax.swing.JDialog {

    float denominacion[] = {100F, 50F, 20F, 10F, 5F, 1F, 0.50F, 0.25F, 0.10F, 0.05F, 0.01F};
    int ch[] = new int[11];
    DefaultTableModel modeloTabla;
    DefaultTableModel modeloTabla1;
    DefaultTableModel modeloTabla2;
    String[] encabesado = {"Denominación", "Hora Salida", "Fecha Salida"};
    String[] encabesado1 = {"Denominación", "Cantidad"};
    String[] encabesado2 = {"$", "Cantidad", "Hora", "Fecha"};
    String[][] data = {};

    Moneda mon;
    Fechas fe;

    /**
     * Constructor de la clase ModoAdministrador
     *
     * @param parent
     * @param modal
     */
    public ModoAdministrador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setTitle("Modo: Administrador");
        setLocationRelativeTo(null);
        setResizable(false);
    }

    /**
     * Método que permite leer de un archivo, las fechas en las que se han
     * realizado todas las transacciones Este método llama al método
     * insertarEnTabla
     */
    void Extraer() {
        String linea;
        //Inicializa el contenido de la tabla de fechas, permitiendo actualizarla cada vez que se llame al método
        modeloTabla = new DefaultTableModel(data, encabesado);
        modeloTabla.setRowCount(0);
        tablaFechas.setModel(modeloTabla);
        tablaFechas.setRowSorter(new TableRowSorter<>(modeloTabla));
        try {
            FileReader fr = new FileReader("información.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                //Extracción de los datos del archivo
                StringTokenizer st = new StringTokenizer(linea, " ");
                float din = Float.parseFloat(st.nextToken());
                String Hora = st.nextToken();
                String Anio = st.nextToken();
                fe = new Fechas(din, Hora, Anio);

                //LLama al método  insertarEnTabla
                insertarEnTabla(fe, modeloTabla);
            }

            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al abrir archivo");

        } catch (java.util.NoSuchElementException e1) {//EXCEPCION DE CUANDO NO HAY NADA ESCRITO EN EL ARCHIVO
        }
    }

    /**
     * Método que permite insertar la hora de las transacciones en la Tabla de
     * Fechas
     *
     * @param f
     * @param modeloTabla
     */
    void insertarEnTabla(Fechas f, DefaultTableModel modeloTabla) {
        //Agrega los datos del objeto en un vector
        Object[] in = {"" + (float) f.getDinero(), "" + f.getHora(), "" + f.getFecha()};
        //Inserta el vector en una nueva fila de la tabla
        modeloTabla.addRow(in);
    }

    /**
     * Método que permite leer de un archivo, la cantidad de monedas existentes
     * Este método llama al método insertarEnTabla1
     */
    void Extraer1() {
        String linea;
        modeloTabla1 = new DefaultTableModel(data, encabesado1);
        TablaActual.setModel(modeloTabla1);
        try {
            FileReader fr = new FileReader("archivo.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                //Extracción de los datos del archivo
                StringTokenizer st = new StringTokenizer(linea, "|");
                float din = Float.parseFloat(st.nextToken());
                int can = Integer.parseInt(st.nextToken());
                //Guarda los datos en una variable de tipo Moneda
                mon = new Moneda(din, can);
                //Inserta los datos obtenidos en una tabla
                insertarEnTabla1(mon, modeloTabla1);
            }

            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al abrir archivo");

        } catch (java.util.NoSuchElementException e1) {//EXCEPCION DE CUANDO NO HAY NADA ESCRITO EN EL ARCHIVO

        }
    }

    /**
     * Método para insertar en la Tabla de Cantidades de Dinero Actual Recibe
     * como parámetro una variable de tipo Moneda otra de tipo DefaultTableModel
     *
     * @param m
     * @param modeloTabla
     */
    void insertarEnTabla1(Moneda m, DefaultTableModel modeloTabla) {
        //Agrega los datos del objeto en un vector
        Object[] in = {"" + (float) m.getDenominacion(), "" + m.getCantidad()};
        //Inserta el vector en una nueva fila de la tabla
        modeloTabla1.addRow(in);
    }

    /**
     * Este método devuelve la cantidad actual de dinero, luego de hacer una
     * recarga Se utiliza en el método AñadirInfo que actualiza los datos en el
     * archivo para luego ser extraidos a la Tabla de Cantidad Actual de Dinero
     *
     * @return
     */
    int[] Extraer2() {
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

    void Recarga() {
        for (int i = 0; i < 11; i++) {
            ch[i] = 0;
        }
        try {
            ch[1] = Integer.parseInt(BCincuenta.getText());
        } catch (NumberFormatException ex) {
        }
        BCincuenta.setText("");
        try {
            ch[2] = Integer.parseInt(BVeinte.getText());
        } catch (NumberFormatException ex) {
        }
        BVeinte.setText("");
        try {
            ch[3] = Integer.parseInt(BDiez.getText());
        } catch (NumberFormatException ex) {
        }
        BDiez.setText("");
        try {
            ch[4] = Integer.parseInt(BCinco.getText());
        } catch (NumberFormatException ex) {
        }
        BCinco.setText("");
        try {
            ch[5] = Integer.parseInt(MUno.getText());
        } catch (NumberFormatException ex) {
        }
        MUno.setText("");
        try {
            ch[6] = Integer.parseInt(MCincuenta.getText());
        } catch (NumberFormatException ex) {
        }
        MCincuenta.setText("");
        try {
            ch[7] = Integer.parseInt(MTicinco.getText());
        } catch (NumberFormatException ex) {
        }
        MTicinco.setText("");
        try {
            ch[8] = Integer.parseInt(MDiez.getText());
        } catch (NumberFormatException ex) {
        }
        MDiez.setText("");
        try {
            ch[9] = Integer.parseInt(MCinco.getText());
        } catch (NumberFormatException ex) {
        }
        MCinco.setText("");
        try {
            ch[10] = Integer.parseInt(Centavo.getText());
        } catch (NumberFormatException ex) {
        }
        Centavo.setText("");

    }

    /**
     * Suma la cantidad actual más la cantidad de dinero ingresada en la recarga
     * y las almacena en archivo
     */
    void AñadirInfo() {
        String d[] = {"100.0", "50.0", "20.0", "10.0", "5.0", "1.0", "0.50", "0.25", "0.10", "0.05", "0.01"};
        int fin[] = this.Extraer2();
        for (int i = 0; i < 11; i++) {
            fin[i] = fin[i] + ch[i];
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

    void Extraer3() {
        String linea;
        modeloTabla2 = new DefaultTableModel(data, encabesado2);
        modeloTabla2.setRowCount(0);
        TablaRecargas.setModel(modeloTabla2);
        TablaRecargas.setRowSorter(new TableRowSorter<>(modeloTabla2));

        try {
            FileReader fr = new FileReader("recargas.txt");
            BufferedReader br = new BufferedReader(fr);
            while ((linea = br.readLine()) != null) {
                //Extracción de los datos del archivo
                StringTokenizer st = new StringTokenizer(linea, " ");
                float din = Float.parseFloat(st.nextToken());
                int Cantidad = Integer.parseInt(st.nextToken());
                String Hora = st.nextToken();
                String Anio = st.nextToken();
                fe = new Fechas(din, Hora, Anio);
                mon = new Moneda(0, Cantidad);
                //Inserta los datos obtenidos en una tabla
                insertarEnTabla2(fe, mon, modeloTabla2);
            }
            br.close();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(rootPane, "Error al abrir archivo");

        } catch (java.util.NoSuchElementException e1) {//EXCEPCION DE CUANDO NO HAY NADA ESCRITO EN EL ARCHIVO

        }
    }

    /**
     * Método para insertar en la Tabla de Cantidades de Dinero Actual Recibe
     * como parámetro una variable de tipo Moneda otra de tipo DefaultTableModel
     *
     * @param f fechas
     * @param m Moneda
     * @param modeloTabla
     */
    void insertarEnTabla2(Fechas f, Moneda m, DefaultTableModel modeloTabla) {
        //Agrega los datos del objeto en un vector
        Object[] in = {"" + (float) f.getDinero(), "" + m.getCantidad(), "" + f.getHora(), "" + f.getFecha()};
        //Inserta el vector en una nueva fila de la tabla
        modeloTabla2.addRow(in);
    }

    void AñadirInfo2() {
        int j = 0;
        Date dat = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        String mon[] = {"100.0", "50.0", "20.0", "10.0", "5.0", "1.0", "0.50", "0.25", "0.10", "0.05", "0.01"};

        try {
            File f = new File("recargas.txt");
            FileWriter fw = new FileWriter(f, true);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < 11; i++) {
                if (ch[i] == 0) {
                    j++;
                } else {
                    String dato = mon[j] + " " + ch[j] + " " + hourFormat.format(dat) + " " + dateFormat.format(dat);
                    bw.write(dato);
                    bw.newLine();
                    j++;
                }
            }

            bw.close();
            fw.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        BDiez = new javax.swing.JTextField();
        BVeinte = new javax.swing.JTextField();
        BCincuenta = new javax.swing.JTextField();
        BCinco = new javax.swing.JTextField();
        MCincuenta = new javax.swing.JTextField();
        MUno = new javax.swing.JTextField();
        MDiez = new javax.swing.JTextField();
        MCinco = new javax.swing.JTextField();
        MTicinco = new javax.swing.JTextField();
        Centavo = new javax.swing.JTextField();
        btnRecargar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btnRecargar1 = new javax.swing.JButton();
        btnRecargar2 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFechas = new javax.swing.JTable();
        btnReporte = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TablaActual = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TablaRecargas = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(770, 555));
        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1 ctv.png"))); // NOI18N
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 300, -1, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5 ctv.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 220, -1, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10 ctv.png"))); // NOI18N
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 150, 50, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/25 ctv.png"))); // NOI18N
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 90, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/50 ctv.png"))); // NOI18N
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 10, -1, -1));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/1 d.png"))); // NOI18N
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 300, -1, -1));

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/5 dólares.jpg"))); // NOI18N
        jButton5.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanel1.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 223, 110, 50));

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/10 dólares.jpg"))); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanel1.add(jButton8, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 153, 110, 50));

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/20 dólares.jpg"))); // NOI18N
        jButton9.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanel1.add(jButton9, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 83, 110, 50));

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/50 dólares.jpg"))); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(110, 50));
        jPanel1.add(jButton10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 110, 50));

        BDiez.setForeground(new java.awt.Color(255, 255, 255));
        BDiez.setCaretColor(new java.awt.Color(255, 0, 0));
        BDiez.setOpaque(false);
        BDiez.setPreferredSize(new java.awt.Dimension(71, 20));
        BDiez.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BDiezKeyTyped(evt);
            }
        });
        jPanel1.add(BDiez, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 160, 71, 20));

        BVeinte.setForeground(new java.awt.Color(255, 255, 255));
        BVeinte.setCaretColor(new java.awt.Color(255, 0, 0));
        BVeinte.setOpaque(false);
        BVeinte.setPreferredSize(new java.awt.Dimension(71, 20));
        BVeinte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BVeinteKeyTyped(evt);
            }
        });
        jPanel1.add(BVeinte, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 71, 20));

        BCincuenta.setForeground(new java.awt.Color(255, 255, 255));
        BCincuenta.setCaretColor(new java.awt.Color(255, 0, 0));
        BCincuenta.setOpaque(false);
        BCincuenta.setPreferredSize(new java.awt.Dimension(71, 20));
        BCincuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BCincuentaKeyTyped(evt);
            }
        });
        jPanel1.add(BCincuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 71, 20));

        BCinco.setForeground(new java.awt.Color(255, 255, 255));
        BCinco.setCaretColor(new java.awt.Color(255, 0, 0));
        BCinco.setOpaque(false);
        BCinco.setPreferredSize(new java.awt.Dimension(71, 20));
        BCinco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                BCincoKeyTyped(evt);
            }
        });
        jPanel1.add(BCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 240, 71, 20));

        MCincuenta.setForeground(new java.awt.Color(255, 255, 255));
        MCincuenta.setCaretColor(new java.awt.Color(255, 0, 0));
        MCincuenta.setOpaque(false);
        MCincuenta.setPreferredSize(new java.awt.Dimension(71, 20));
        MCincuenta.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MCincuentaKeyTyped(evt);
            }
        });
        jPanel1.add(MCincuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 30, 71, 20));

        MUno.setForeground(new java.awt.Color(255, 255, 255));
        MUno.setCaretColor(new java.awt.Color(255, 0, 0));
        MUno.setOpaque(false);
        MUno.setPreferredSize(new java.awt.Dimension(71, 20));
        MUno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MUnoKeyTyped(evt);
            }
        });
        jPanel1.add(MUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 320, 71, 20));

        MDiez.setForeground(new java.awt.Color(255, 255, 255));
        MDiez.setCaretColor(new java.awt.Color(255, 0, 0));
        MDiez.setOpaque(false);
        MDiez.setPreferredSize(new java.awt.Dimension(71, 20));
        MDiez.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MDiezKeyTyped(evt);
            }
        });
        jPanel1.add(MDiez, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 160, 71, 20));

        MCinco.setForeground(new java.awt.Color(255, 255, 255));
        MCinco.setCaretColor(new java.awt.Color(255, 0, 0));
        MCinco.setOpaque(false);
        MCinco.setPreferredSize(new java.awt.Dimension(71, 20));
        MCinco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MCincoKeyTyped(evt);
            }
        });
        jPanel1.add(MCinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 240, 71, 20));

        MTicinco.setForeground(new java.awt.Color(255, 255, 255));
        MTicinco.setCaretColor(new java.awt.Color(255, 0, 0));
        MTicinco.setOpaque(false);
        MTicinco.setPreferredSize(new java.awt.Dimension(71, 20));
        MTicinco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                MTicincoKeyTyped(evt);
            }
        });
        jPanel1.add(MTicinco, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 100, 71, 20));

        Centavo.setForeground(new java.awt.Color(255, 255, 255));
        Centavo.setCaretColor(new java.awt.Color(255, 0, 0));
        Centavo.setOpaque(false);
        Centavo.setPreferredSize(new java.awt.Dimension(71, 20));
        Centavo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                CentavoKeyTyped(evt);
            }
        });
        jPanel1.add(Centavo, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 320, 71, 20));

        btnRecargar.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        btnRecargar.setText("Recargar");
        btnRecargar.setBorderPainted(false);
        btnRecargar.setContentAreaFilled(false);
        btnRecargar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecargar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecargar.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        btnRecargar.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        btnRecargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargarActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 110, 30));

        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        jButton1.setText("Regresar");
        jButton1.setToolTipText("");
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
        jPanel1.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 370, 110, 30));

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/f2.png"))); // NOI18N
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 420, 400));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -6, 450, 430));

        btnRecargar1.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        btnRecargar1.setText("Recargar");
        btnRecargar1.setBorderPainted(false);
        btnRecargar1.setContentAreaFilled(false);
        btnRecargar1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecargar1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecargar1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        btnRecargar1.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        btnRecargar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargar1ActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecargar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 110, 30));

        btnRecargar2.setForeground(new java.awt.Color(255, 255, 255));
        btnRecargar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        btnRecargar2.setText("Recargar");
        btnRecargar2.setBorderPainted(false);
        btnRecargar2.setContentAreaFilled(false);
        btnRecargar2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnRecargar2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnRecargar2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        btnRecargar2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        btnRecargar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecargar2ActionPerformed(evt);
            }
        });
        jPanel1.add(btnRecargar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 370, 110, 30));

        jTabbedPane1.addTab("Recargar Dinero", jPanel1);

        jPanel2.setLayout(null);

        tablaFechas.setBackground(new java.awt.Color(153, 153, 153));
        tablaFechas.setForeground(new java.awt.Color(255, 255, 255));
        tablaFechas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaFechas.setEnabled(false);
        jScrollPane1.setViewportView(tablaFechas);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 23, 404, 335);

        btnReporte.setForeground(new java.awt.Color(255, 255, 255));
        btnReporte.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        btnReporte.setText("Actualizar Reporte");
        btnReporte.setBorderPainted(false);
        btnReporte.setContentAreaFilled(false);
        btnReporte.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReporte.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnReporte.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        btnReporte.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        btnReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReporteActionPerformed(evt);
            }
        });
        jPanel2.add(btnReporte);
        btnReporte.setBounds(220, 370, 140, 30);

        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b1.png"))); // NOI18N
        jButton2.setText("Regresar");
        jButton2.setBorderPainted(false);
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b3.png"))); // NOI18N
        jButton2.setRolloverSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/img/b2.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton2);
        jButton2.setBounds(90, 370, 110, 30);

        jLabel11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo.jpg"))); // NOI18N
        jLabel11.setText("jLabel11");
        jPanel2.add(jLabel11);
        jLabel11.setBounds(0, -10, 445, 440);

        jTabbedPane1.addTab("Reporte Salidas", jPanel2);

        getContentPane().add(jTabbedPane1);
        jTabbedPane1.setBounds(21, 39, 450, 450);

        TablaActual.setBackground(new java.awt.Color(102, 102, 102));
        TablaActual.setForeground(new java.awt.Color(255, 255, 255));
        TablaActual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaActual.setEnabled(false);
        jScrollPane2.setViewportView(TablaActual);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(500, 80, 214, 190);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("CANTIDAD ACTUAL DE DINERO");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(520, 60, 152, 14);

        TablaRecargas.setBackground(new java.awt.Color(102, 102, 102));
        TablaRecargas.setForeground(new java.awt.Color(255, 255, 255));
        TablaRecargas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        TablaRecargas.setEnabled(false);
        jScrollPane3.setViewportView(TablaRecargas);

        getContentPane().add(jScrollPane3);
        jScrollPane3.setBounds(500, 320, 210, 160);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("LISTA DE RECARGAS");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(550, 300, 101, 14);

        jLabel12.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/f2.png"))); // NOI18N
        getContentPane().add(jLabel12);
        jLabel12.setBounds(490, 40, 240, 460);

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/fondo2.jpg"))); // NOI18N
        jLabel9.setText("jLabel9");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(0, 0, 770, 540);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReporteActionPerformed
        //Para tablaFechas->modeloTabla
        this.Extraer();
    }//GEN-LAST:event_btnReporteActionPerformed

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        //Para Tabla de dinero actual->modeloTabla1
        this.Extraer1();
        this.Extraer3();
    }//GEN-LAST:event_formComponentShown

    private void btnRecargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargarActionPerformed

        this.Recarga();
        this.AñadirInfo();
        this.Extraer1();
        this.AñadirInfo2();
        this.Extraer3();
    }//GEN-LAST:event_btnRecargarActionPerformed

    private void btnRecargar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargar1ActionPerformed

    }//GEN-LAST:event_btnRecargar1ActionPerformed

    private void btnRecargar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecargar2ActionPerformed

    }//GEN-LAST:event_btnRecargar2ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void BCincuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BCincuentaKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (BCincuenta.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_BCincuentaKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void BVeinteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BVeinteKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (BVeinte.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_BVeinteKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void BDiezKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BDiezKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (BDiez.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_BDiezKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void BCincoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BCincoKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (BCinco.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_BCincoKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void MUnoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MUnoKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (MUno.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_MUnoKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void MCincuentaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MCincuentaKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (MCincuenta.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_MCincuentaKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void MTicincoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MTicincoKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (MTicinco.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_MTicincoKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void MDiezKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MDiezKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (MDiez.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_MDiezKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void MCincoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MCincoKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (MCinco.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_MCincoKeyTyped
    /**
     * Evento que me permite validar el ingreso de solo números
     *
     * @param evt
     */
    private void CentavoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CentavoKeyTyped
        int k = (int) evt.getKeyChar();
        if (k >= 48 && k <= 57) {
            if (Centavo.getText().length() == 9) {
                evt.consume();
                getToolkit().beep();
            }
        } else {
            evt.setKeyChar((char) KeyEvent.VK_CLEAR);
            evt.consume();
            getToolkit().beep();
        }
    }//GEN-LAST:event_CentavoKeyTyped


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BCinco;
    private javax.swing.JTextField BCincuenta;
    private javax.swing.JTextField BDiez;
    private javax.swing.JTextField BVeinte;
    private javax.swing.JTextField Centavo;
    private javax.swing.JTextField MCinco;
    private javax.swing.JTextField MCincuenta;
    private javax.swing.JTextField MDiez;
    private javax.swing.JTextField MTicinco;
    private javax.swing.JTextField MUno;
    private javax.swing.JTable TablaActual;
    private javax.swing.JTable TablaRecargas;
    private javax.swing.JButton btnRecargar;
    private javax.swing.JButton btnRecargar1;
    private javax.swing.JButton btnRecargar2;
    private javax.swing.JButton btnReporte;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tablaFechas;
    // End of variables declaration//GEN-END:variables
}
