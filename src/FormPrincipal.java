
import java.io.BufferedReader;
import java.io.StringReader;
import Analizadores.Haskell.HaskellLexico;
import Analizadores.Haskell.HaskellSintactico;
import Ast.Nodo;
import Simbolos.LlenadoTablaHaskell;
import Simbolos.TablaSimbolosHaskell;
import java.awt.Color;
import java.io.ByteArrayInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JEditorPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import org.fife.ui.rsyntaxtextarea.AbstractTokenMakerFactory;
import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;
import org.fife.ui.rsyntaxtextarea.SyntaxScheme;
import org.fife.ui.rsyntaxtextarea.Token;
import org.fife.ui.rsyntaxtextarea.TokenMakerFactory;
import org.fife.ui.rtextarea.RTextScrollPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    int contadorPestañas =0;
    public FormPrincipal() {
        initComponents();
       // configurarEditor();
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
        jTabbedPane1 = new javax.swing.JTabbedPane();
        btnNueva = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jLabel1 = new javax.swing.JLabel();
        btnEjecutar = new javax.swing.JButton();
        lblColumna = new javax.swing.JLabel();

        jMenu1.setText("jMenu1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Graphik y Haskell++");
        setBackground(new java.awt.Color(0, 153, 153));
        setMinimumSize(new java.awt.Dimension(990, 466));
        setPreferredSize(new java.awt.Dimension(971, 650));

        btnNueva.setText("Nuevo Documento");
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 801, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnNueva)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(btnEjecutar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblColumna)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(filler1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 455, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNueva)
                        .addGap(7, 7, 7)
                        .addComponent(btnEjecutar)
                        .addGap(377, 377, 377)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(lblColumna)))))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

     public RSyntaxTextArea rsta;
     
    private void btnNuevaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevaActionPerformed
        String nombre = "";
        NuevoDocumento ventana = new NuevoDocumento();
        ventana.setVisible(true);
    }//GEN-LAST:event_btnNuevaActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        
        int actual = jTabbedPane1.getSelectedIndex();
        String a = listaPestañas.get(actual).getTextArea().getText();
            if(a.isEmpty()){
                System.err.println("No es posible evaluar una cadena en blanco.");
                return;
            }
            try { 
                //viene haskell
                
                if(listaPestañas.get(actual).getTextArea().getName().equals(".hk")){
                    
                    HaskellLexico scan = new HaskellLexico(new BufferedReader( new StringReader(a)));
                    HaskellSintactico parser = new HaskellSintactico(scan);
                    parser.parse();
                    Graficar(recorrido(HaskellSintactico.raiz),"AstHaskell");
                    LlenadoTablaHaskell.Llenar(HaskellSintactico.raiz);
                    
                }
                //viene graphik    
                else if(listaPestañas.get(actual).getTextArea().getName().equals(".gk")){
                    System.out.println("entro a graphik");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        
    }//GEN-LAST:event_btnEjecutarActionPerformed

    public void Graficar(String cadena,String cad){
        FileWriter fichero = null;
        PrintWriter pw = null;
        String nombre=cad;
        String archivo=nombre+".dot";
        try {
            fichero = new FileWriter(archivo);
            pw = new PrintWriter(fichero);
            pw.println("digraph G {node[shape=box, style=filled]; edge[color=red]");
            pw.println(cadena);
            pw.println("\n}");
            fichero.close();
        } catch (Exception e) {
            System.out.println(e);
        } 
        try {
            String cmd = "dot.exe -Tpng "+nombre+".dot -o AstHaskell.png"; 
            Runtime.getRuntime().exec(cmd); 
        } catch (IOException ioe) {
                System.out.println (ioe);
        }
    }
    
    
    String recorrido(Nodo raiz){
     
        String r= ""; 
        if(raiz!=null){
            r="node" + raiz.hashCode()+ "[label=\""+raiz.valor+  "\"];";
            for (int i=0;i<raiz.hijos.size();i++){
                r+="\n node"+ raiz.hashCode() + "->"+"node"+raiz.hijos.get(i).hashCode() + ";";
                r+=recorrido(raiz.hijos.get(i));  
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnNueva;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    public static javax.swing.JTabbedPane jTabbedPane1;
    public static javax.swing.JLabel lblColumna;
    // End of variables declaration//GEN-END:variables
}
