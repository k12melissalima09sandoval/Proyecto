
import Dibujar.Datos;
import java.io.BufferedReader;
import java.io.StringReader;
import Analizadores.Haskell.HaskellLexico;
import Analizadores.Haskell.HaskellSintactico;
import Analizadores.Consola.ConsolaLexico;
import Analizadores.Consola.ConsolaSintactico;
import Analizadores.Graphik.GraphikLexico;
import Analizadores.Graphik.GraphikSintactico;
import Analizadores.Imprimir;
import Dibujar.DatosGraphik;
import Dibujar.Nodo;
import Interprete.Graphik.Als;
import Interprete.Graphik.Ejecucion;
import Interprete.Graphik.ExpresionGraphik;
import Interprete.Valor;
import Interprete.Graphik.PrimeraPasada;
import Interprete.Graphik.SegundaPasada;
import Interprete.Haskell.RecorreHaskell;
import Interprete.Parametros;
import Simbolos.TablaSimbolosGraphik;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import org.fife.ui.rtextarea.RTextScrollPane;

/**
 *
 * @author MishaPks
 */
public class FormPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FormPrincipal
     */
    public static ArrayList<RTextScrollPane> listaPestañas = new ArrayList();
    public static int nuevo;
    public static String rutaPestaña;

    static RecorreHaskell consola = new RecorreHaskell();
    int contadorPestañas = 0;
    String anterior;

    public FormPrincipal() {
        initComponents();

        //PARA LA CONSOLA
        this.txtEntradaConsola.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int tecla = e.getKeyCode();
                if (tecla == KeyEvent.VK_ENTER) {
                    try {
                        ConsolaLexico scan = new ConsolaLexico(new BufferedReader(new StringReader(txtEntradaConsola.getText())));
                        ConsolaSintactico parser = new ConsolaSintactico(scan);
                        parser.parse();
                        Graficar(recorrido(ConsolaSintactico.raiz), "AstConsola");
                        anterior = txtSalidaConsola.getText();
                        txtSalidaConsola.setText(anterior + ">" + txtEntradaConsola.getText() + "\n");
                        txtEntradaConsola.setText("$ $");

                        Valor v = (Valor) RecorreHaskell.Consola(ConsolaSintactico.raiz, "");

                        anterior = txtSalidaConsola.getText();
                        txtSalidaConsola.setText("");
                        txtSalidaConsola.setText(anterior + ">" + v.valor.toString().replace(".0", "") + "\n");

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,
                                "No hay cadena para analizar",
                                "Warning",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            }
        });

        //this.txtEntradaConsola.addKeyListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jFileChooser1 = new javax.swing.JFileChooser();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        btnNueva = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        btnEjecutar = new javax.swing.JButton();
        lblColumna = new javax.swing.JLabel();
        txtEntradaConsola = new javax.swing.JTextField();
        btnCerrarPestaña = new javax.swing.JButton();
        jTabedPane2 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtSalidaConsola = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtErrores = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtSimbolos = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtConsolaGraphik = new javax.swing.JTextArea();
        btnGuardar = new javax.swing.JButton();
        btnAbrir = new javax.swing.JButton();
        btnCargarDatos = new javax.swing.JButton();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Graphik y Haskell++");
        setBackground(new java.awt.Color(255, 255, 204));
        setMinimumSize(new java.awt.Dimension(950, 500));
        setPreferredSize(new java.awt.Dimension(935, 710));

        btnNueva.setText("Nuevo");
        btnNueva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevaActionPerformed(evt);
            }
        });

        jLabel1.setText("Columna: ");

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        lblColumna.setText("----");

        txtEntradaConsola.setText("$ $");

        btnCerrarPestaña.setText("Cerrar Actual");
        btnCerrarPestaña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarPestañaActionPerformed(evt);
            }
        });

        txtSalidaConsola.setBackground(new java.awt.Color(249, 249, 132));
        txtSalidaConsola.setColumns(20);
        txtSalidaConsola.setLineWrap(true);
        txtSalidaConsola.setRows(5);
        txtSalidaConsola.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(246, 126, 16)));
        jScrollPane1.setViewportView(txtSalidaConsola);

        jTabedPane2.addTab("Consola", jScrollPane1);

        txtErrores.setBackground(new java.awt.Color(2, 203, 203));
        txtErrores.setColumns(20);
        txtErrores.setForeground(new java.awt.Color(0, 0, 0));
        txtErrores.setLineWrap(true);
        txtErrores.setRows(5);
        txtErrores.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 51, 255)));
        jScrollPane2.setViewportView(txtErrores);

        jTabedPane2.addTab("Errores", jScrollPane2);

        txtSimbolos.setBackground(new java.awt.Color(230, 157, 255));
        txtSimbolos.setColumns(20);
        txtSimbolos.setLineWrap(true);
        txtSimbolos.setRows(5);
        txtSimbolos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 153)));
        jScrollPane3.setViewportView(txtSimbolos);

        jTabedPane2.addTab("Tabla de Simbolos", jScrollPane3);

        txtConsolaGraphik.setBackground(new java.awt.Color(204, 255, 204));
        txtConsolaGraphik.setColumns(20);
        txtConsolaGraphik.setLineWrap(true);
        txtConsolaGraphik.setRows(5);
        txtConsolaGraphik.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 204, 51)));
        jScrollPane4.setViewportView(txtConsolaGraphik);

        jTabedPane2.addTab("Consola Graphik", jScrollPane4);

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnCargarDatos.setText("Cargar Datos");
        btnCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarDatosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTabedPane2)
                            .addComponent(txtEntradaConsola)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnNueva, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblColumna))
                                    .addComponent(btnCerrarPestaña, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                                    .addComponent(btnEjecutar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnAbrir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnCargarDatos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 21, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNueva)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEjecutar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAbrir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnCargarDatos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnCerrarPestaña)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblColumna)))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 383, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEntradaConsola, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(51, Short.MAX_VALUE))
        );

        getAccessibleContext().setAccessibleDescription("Melissa Lima");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        String nombre = "";
        NuevoDocumento ventana = new NuevoDocumento();
        ventana.setVisible(true);
    }//GEN-LAST:event_btnNuevaActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        try {
            int actual = jTabbedPane1.getSelectedIndex();
            String a = listaPestañas.get(actual).getTextArea().getText();
            if (a.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No hay cadena para analizar!!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                //viene haskell

                if (listaPestañas.get(actual).getTextArea().getName().equals(".hk")) {
                    try {
                        HaskellLexico scan = new HaskellLexico(new BufferedReader(new StringReader(a)));
                        HaskellSintactico parser = new HaskellSintactico(scan);
                        parser.parse();
                        Graficar(recorrido(HaskellSintactico.raiz), "AstHaskell");
                        RecorreHaskell.Recorrido(HaskellSintactico.raiz);
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(null,
                                "Hay errores en la entrada Haskell!!", "",
                                JOptionPane.WARNING_MESSAGE);
                    }
                } //viene graphik    
                else if (listaPestañas.get(actual).getTextArea().getName().equals(".gk")) {
                    try {
                        //vacio las estaticas
                        TablaSimbolosGraphik.listaAls.removeAll(TablaSimbolosGraphik.listaAls);
                        TablaSimbolosGraphik.errorSemantico.removeAll(TablaSimbolosGraphik.errorSemantico);
                        Imprimir.imprimir.removeAll(Imprimir.imprimir);
                        SegundaPasada.contTemp=0;
                        SegundaPasada.pila.removeAllElements();
                        ExpresionGraphik.Columnas.clear();
                        //
                        Ejecucion ejecuta = new Ejecucion();
                        GraphikLexico scan = new GraphikLexico(new BufferedReader(new StringReader(a)));
                        GraphikSintactico parser = new GraphikSintactico(scan);
                        parser.parse();
                        Graficar(recorrido(GraphikSintactico.raiz), "AstGraphik");
                        ejecuta.Ejecucion(GraphikSintactico.raiz);
                        
                        String textoImprimir ="";
                        for (int i = 0; i < Imprimir.imprimir.size(); i++) {
                            textoImprimir+=">> "+Imprimir.imprimir.get(i);
                        }
                        txtConsolaGraphik.setText(textoImprimir);
                        String textoErrores = "";
                        for (int i = 0; i < TablaSimbolosGraphik.errorSemantico.size(); i++) {
                            textoErrores += TablaSimbolosGraphik.errorSemantico.get(i).tipo + "->"
                                    + TablaSimbolosGraphik.errorSemantico.get(i).texto + "\n";
                            textoErrores += "----------------------------------------------------------------------------\n";
                        }
                        txtErrores.setText(textoErrores);
                        ImprimirTabla();

                    } catch (Exception e) {
                        String textoImprimir ="";
                        for (int i = 0; i < Imprimir.imprimir.size(); i++) {
                            textoImprimir+=">> "+Imprimir.imprimir.get(i);
                        }
                        txtConsolaGraphik.setText(textoImprimir);
                        String textoErrores = "";
                        for (int i = 0; i < TablaSimbolosGraphik.errorSemantico.size(); i++) {
                            textoErrores += TablaSimbolosGraphik.errorSemantico.get(i).tipo + "->"
                                    + TablaSimbolosGraphik.errorSemantico.get(i).texto + "\n";
                            textoErrores += "----------------------------------------------------------------------------\n";
                        }
                        txtErrores.setText(textoErrores);
                        ImprimirTabla();
                        JOptionPane.showMessageDialog(null,
                                "Algo ha ido mal",
                                "",
                                JOptionPane.WARNING_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Ups... Algo a salido mal!!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No hay ningun archivo para analizar!!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed

    public void ImprimirTabla() {
        String simbolos = "";
        for (int i = 0; i < TablaSimbolosGraphik.listaAls.size(); i++) {
            simbolos += "☼ Nombre Als: " + TablaSimbolosGraphik.listaAls.get(i).nombre + " \n"
                    + "\t →Visibilidad:" + TablaSimbolosGraphik.listaAls.get(i).visibilidad + "\n";
            if (!TablaSimbolosGraphik.listaAls.get(i).hereda.isEmpty()) {
                simbolos += "\t →Hereda: " + TablaSimbolosGraphik.listaAls.get(i).hereda.get(0).nombre + "\n";
                String temp = (String) ConcatenaImportas(TablaSimbolosGraphik.listaAls.get(i).hereda);
                simbolos += temp;
            }
            if (!TablaSimbolosGraphik.listaAls.get(i).importa.isEmpty()) {
                simbolos += "\t →Importaciones: \n";
                for (int j = 0; j < TablaSimbolosGraphik.listaAls.get(i).importa.size(); j++) {
                    String temp = (String) ConcatenaImportas(TablaSimbolosGraphik.listaAls.get(i).importa);
                    simbolos += temp;
                }
            }
            if (!TablaSimbolosGraphik.listaAls.get(i).incluye.isEmpty()) {
                simbolos += "\t →Incluye: \n";
                for (int j = 0; j < TablaSimbolosGraphik.listaAls.get(i).incluye.size(); j++) {
                    simbolos += "\t ├" + TablaSimbolosGraphik.listaAls.get(i).incluye.get(j).getNombre() + "\n";
                }
            }
            if (!TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.isEmpty()) {
                simbolos += "\t →Variables Globales: \n";
                for (int j = 0; j < TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.size(); j++) {
                    simbolos += "\t\t └Tipo: " + TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.get(j).tipo
                            + " ─ Visibilidad: "
                            + TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.get(j).visibilidad + " ─ Nombre: "
                            + TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.get(j).nombre + " ─ Valor: ";
                    if (TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.get(j).instancia) {
                        simbolos += "(instancia)";
                        simbolos += TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.get(j).tipo + "\n";
                    } else {
                        simbolos += TablaSimbolosGraphik.listaAls.get(i).VarsGlobales.get(j).valor + "\n";
                    }
                }
            }
            if (!TablaSimbolosGraphik.listaAls.get(i).Metodos.isEmpty()) {
                simbolos += "\t →Metodos: \n";
                for (int j = 0; j < TablaSimbolosGraphik.listaAls.get(i).Metodos.size(); j++) {
                    simbolos += "\t\t └Tipo: " + TablaSimbolosGraphik.listaAls.get(i).Metodos.get(j).tipo
                            + " ─ Visibilidad: "
                            + TablaSimbolosGraphik.listaAls.get(i).Metodos.get(j).visibilidad + " ─ Nombre: "
                            + TablaSimbolosGraphik.listaAls.get(i).Metodos.get(j).nombre + " \n";
                    ArrayList<Parametros> param = TablaSimbolosGraphik.listaAls.get(i).Metodos.get(j).getParametros();
                    if (!param.isEmpty()) {
                        simbolos += "\t\t →Parametros: \n";
                        for (int k = 0; k < param.size(); k++) {
                            simbolos += "\t\t ├ Tipo: " + param.get(k).tipo + " ─ Nombre: " + param.get(k).nombre + "\n";
                        }
                    }
                }
            }
            simbolos += "----------------------------------------------------------------"
                    + "--------------------------------------------------------------------------------------------"
                    + "------------------------------------------- \n";
        }
        txtSimbolos.setText(simbolos);
    }

    public String ConcatenaImportas(ArrayList<Als> als) {
        String simbolos = "";
        for (int i = 0; i < als.size(); i++) {
            simbolos += "\t\t » Nombre Als: " + als.get(i).nombre + " \n"
                    + "\t\t\t →Visibilidad:" + als.get(i).visibilidad + "\n";
            if (!als.get(i).incluye.isEmpty()) {
                simbolos += "\t\t\t →Incluye: \n";
                for (int j = 0; j < als.get(i).incluye.size(); j++) {
                    simbolos += "\t\t\t ├" + als.get(i).incluye.get(j).getNombre() + "\n";
                }
            }
            if (!als.get(i).VarsGlobales.isEmpty()) {
                simbolos += "\t\t\t →Variables Globales: \n";
                for (int j = 0; j < als.get(i).VarsGlobales.size(); j++) {
                    simbolos += "\t\t\t\t └Tipo: " + als.get(i).VarsGlobales.get(j).tipo + " ─ Visibilidad: "
                            + als.get(i).VarsGlobales.get(j).visibilidad + " ─ Nombre: "
                            + als.get(i).VarsGlobales.get(j).nombre + " ─ Valor: ";
                    if (als.get(i).VarsGlobales.get(j).instancia) {
                        simbolos += "(instancia)";
                        simbolos += als.get(i).VarsGlobales.get(j).tipo + "\n";
                    } else {
                        simbolos += als.get(i).VarsGlobales.get(j).valor + "\n";
                    }
                }
            }
            if (!als.get(i).Metodos.isEmpty()) {
                simbolos += "\t\t\t →Metodos: \n";
                for (int j = 0; j < als.get(i).Metodos.size(); j++) {
                    simbolos += "\t\t\t\t └Tipo: " + als.get(i).Metodos.get(j).tipo + " ─ Visibilidad: "
                            + als.get(i).Metodos.get(j).visibilidad + " ─ Nombre: "
                            + als.get(i).Metodos.get(j).nombre + " \n";
                    ArrayList<Parametros> param = als.get(i).Metodos.get(j).getParametros();
                    if (!param.isEmpty()) {
                        simbolos += "\t\t\t\t\t →Parametros: \n";
                        for (int k = 0; k < param.size(); k++) {
                            simbolos += "\t\t\t\t\t ├Tipo: " + param.get(k).tipo + " ─ Nombre: "
                                    + param.get(k).nombre + "\n";
                        }
                    }
                }
            }
            if (!als.get(i).hereda.isEmpty()) {
                simbolos += "\t\t\t →Hereda: " + als.get(i).hereda.get(0).nombre + "\n";
                String temp = (String) ConcatenaImportas(als.get(i).hereda);
                simbolos += temp;
            }
            if (!als.get(i).importa.isEmpty()) {
                simbolos += "\t\t\t →Importaciones: \n";
                for (int j = 0; j < als.get(i).importa.size(); j++) {
                    String temp = (String) ConcatenaImportas(als.get(i).importa);
                    simbolos += temp;
                }
            }

        }
        return simbolos;
    }

    private void btnCerrarPestañaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarPestañaActionPerformed
        try {
            int actual = jTabbedPane1.getSelectedIndex();
            jTabbedPane1.remove(actual);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No hay pestañas abiertas", "Abrir", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnCerrarPestañaActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        int actual = jTabbedPane1.getSelectedIndex();
        int select = jFileChooser1.showSaveDialog(this);
        String a = listaPestañas.get(actual).getTextArea().getText();

        if (select == javax.swing.JFileChooser.APPROVE_OPTION) {

            try {
                fichero = new java.io.File(jFileChooser1.getSelectedFile().getPath());
                escribir = new java.io.FileWriter(fichero, false);
                pintar = new java.io.PrintWriter(escribir);
                pintar.write(a);
                ruta = fichero.getAbsolutePath();

            } catch (IOException ex) {
                //Logger.getLogger(this.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (escribir != null) {
                    try {
                        escribir.close();

                    } catch (IOException ex) {
                        //Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed

        try {
            int actual = jTabbedPane1.getSelectedIndex();
            if (JFileChooserDialog()) {
                String texto = Abierto();
                if (texto != null) {
                    listaPestañas.get(actual).getTextArea().setText(texto);
                    String b = jFileChooser1.getSelectedFile().getAbsolutePath();
                    listaPestañas.get(actual).setName(b);
                    PrimeraPasada.ruta = b;
                } else {
                    JOptionPane.showMessageDialog(null, "No se pudo Abrir", "Abrir", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Por favor, cree una nueva pestaña primero", "Abrir", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarDatosActionPerformed
        try {
            int actual = jTabbedPane1.getSelectedIndex();
            String a = listaPestañas.get(actual).getTextArea().getText();
            if (a.isEmpty()) {
                JOptionPane.showMessageDialog(null,
                        "No hay cadena para analizar!!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            try {
                Datos tabla = new Datos();
                DatosGraphik datos = new DatosGraphik();
                datos.recibirCadena(a,Datos.jTable1);
                
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,
                        "Ups... Algo a salido mal!!",
                        "Warning",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,
                    "No hay ningun archivo para analizar!!",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnCargarDatosActionPerformed

    private boolean JFileChooserDialog() {
        int seleccion = jFileChooser1.showDialog(this, "Selecionar archivo ...");

        if (seleccion == javax.swing.JFileChooser.APPROVE_OPTION) {
            fichero = jFileChooser1.getSelectedFile();
            return true;
        }
        return false;
    }

    java.io.File fichero;
    java.io.FileWriter escribir;
    java.io.PrintWriter pintar;
    java.io.BufferedReader leer;
    java.io.FileReader leerFichero;
    String ruta;

    public String Abierto() {
        try {
            String archivo = "", linea;
            leerFichero = new java.io.FileReader(fichero);
            leer = new java.io.BufferedReader(leerFichero);
            ruta = fichero.getAbsolutePath();
            linea = leer.readLine();
            while (linea != null) {
                archivo += linea;
                archivo += System.getProperty("line.separator");
                linea = leer.readLine();
            }

            return archivo;

        } catch (FileNotFoundException ex) {
            //Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            //Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (leerFichero != null) {
                try {
                    leerFichero.close();
                } catch (IOException ex) {
                    // Logger.getLogger(ManejoArchivos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    public void Graficar(String cadena, String cad) {
        FileWriter fichero2;
        PrintWriter pw;
        String nombre = "ImagenesAst/" + cad;
        String archivo = nombre + ".dot";
        try {
            fichero2 = new FileWriter(archivo);
            pw = new PrintWriter(fichero2);
            pw.println("digraph G {node[shape=box, style=filled]; edge[color=red]");
            pw.println(cadena);
            pw.println("\n}");
            fichero2.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            String cmd = "dot.exe -Tpng " + nombre + ".dot -o " + nombre + ".png";
            Runtime.getRuntime().exec(cmd);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    String recorrido(Nodo raiz) {

        String r = "";
        if (raiz != null) {
            r = "node" + raiz.hashCode() + "[label=\"" + raiz.valor + "\"];";
            for (int i = 0; i < raiz.hijos.size(); i++) {
                r += "\n node" + raiz.hashCode() + "->" + "node" + raiz.hijos.get(i).hashCode() + ";";
                r += recorrido(raiz.hijos.get(i));
            }
        }
        return r;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCargarDatos;
    private javax.swing.JButton btnCerrarPestaña;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnNueva;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    public static javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabedPane2;
    public static javax.swing.JLabel lblColumna;
    private javax.swing.JTextArea txtConsolaGraphik;
    private javax.swing.JTextField txtEntradaConsola;
    public static javax.swing.JTextArea txtErrores;
    private javax.swing.JTextArea txtSalidaConsola;
    private javax.swing.JTextArea txtSimbolos;
    // End of variables declaration//GEN-END:variables

}
