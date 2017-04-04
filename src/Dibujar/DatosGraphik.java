/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dibujar;

import Interprete.Valor;
import java.util.ArrayList;
import java.util.Vector;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MishaPks
 */
public class DatosGraphik {

    static Datos datos = new Datos();
    static DefaultTableModel dtm;

    public void recibirCadena(String cadena, JTable tabla) {
        String columnas = "";
        String filas = "";
        ArrayList<Object> colu = new ArrayList();
        ArrayList fila = new ArrayList();
        int contC = 0;
        int contF = 0;
        int texto = 0;
        for (int i = 0; i < cadena.length(); i++) {
            if (cadena.charAt(i) == '{') {
                break;
            } else {
                texto++;
            }
        }
        for (int i = 0; i < texto; i++) {
            if (cadena.charAt(i) == ',') {
                colu.add(columnas.replace("\"", "").replace("[", "").replace("]", ""));
                columnas = "";
            } else {
                columnas += cadena.charAt(i);
            }
        }
        if (!".".equals(columnas) && !"\n".equals(columnas) && !"]".equals(columnas) && !"".equals(columnas) && columnas.length() > 1
                && !"{".equals(columnas) && !"\"".equals(columnas) && !",".equals(columnas) && columnas != null) {
            colu.add(columnas.replace("\"", "").replace("[", "").replace("]", ""));
        }

        cadena = cadena.substring(texto);
        for (int i = 0; i < cadena.length(); i++) {
            if ((cadena.charAt(i) == ',' || cadena.charAt(i) == '.' || cadena.charAt(i) == '\n') && cadena.charAt(i) != '"') {
                if (filas != "") {
                    fila.add(filas.replace("\"", "").trim());
                    filas = "";
                }
            } else if (cadena.charAt(i) != '"' && cadena.charAt(i) != '\n') {
                filas += cadena.charAt(i);
            }
        }
        if (!".".equals(filas) && !"\n".equals(filas) && !"}".equals(filas)
                && !",".equals(filas) && !"\"".equals(filas) && !"".equals(filas)) {
            fila.add(filas.replace("\"", "").trim());
        }

        ConstruirTablaInicial(tabla, colu, fila);

    }
//---------------------------------------------TABLA INICIAL--------------------------------

    public void ConstruirTablaInicial(JTable tabla, ArrayList col, ArrayList fil) {

        dtm = new DefaultTableModel();
        for (int i = 0; i < col.size(); i++) {
            Object columnas = col.get(i);
            dtm.addColumn(columnas);
        }
        int cont = 0;
        for (int i = 0; i < fil.size() / col.size(); i++) {
            dtm.addRow(new Object[i]);
            for (int j = 0; j < col.size(); j++) {
                try {
                    dtm.setValueAt(fil.get(cont).toString().replace("{", "").replace("}", ""), i, j);
                    cont++;
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null,
                            "Las Columnas no coinciden con las filas!!",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                }
            }

        }
        tabla.setModel(dtm);
    }
//-------------------------------------------------------------------CONSTRUCCION DE TABLAS-------------------------------------------

    public void resultadoDonde(JTable tabla, ArrayList Resultados, String filtro) {

        Object a = Resultados.get(0);
        dtm = new DefaultTableModel();
        dtm.addColumn("Donde");
        dtm.addColumn("Funcion");
        if (!Resultados.isEmpty()) {
            for (int i = 0; i < Resultados.size(); i++) {
                dtm.addRow(new Object[2]);
                dtm.setValueAt(filtro, i, 0);
                dtm.setValueAt(Resultados.get(i).toString(), i, 1);

            }
        }
        tabla.setModel(dtm);
        datos.setVisible(true);
    }

    public void resultadoDondeCada(JTable tabla, ArrayList Resultados, int Columna, ArrayList<Vector> filtros) {

       
        dtm = new DefaultTableModel();
        dtm.addColumn("DondeCada");
        dtm.addColumn("Funcion");
        if (!Resultados.isEmpty()) {
            for (int i = 0; i < Resultados.size(); i++) {
                String filtro = filtros.get(i).get(Columna - 1).toString();
                dtm.addRow(new Object[i]);
                dtm.setValueAt(filtro, i, 0);
                dtm.setValueAt(Resultados.get(i).toString(), i, 1);

            }
        }
        tabla.setModel(dtm);
        datos.setVisible(true);
    }

    public void resultadoDondeTodo(JTable tabla, ArrayList Resultados, int Columna, ArrayList<Vector> filtros) {
        int resultado = 0;
        if (!Resultados.isEmpty()) {
            for (int i = 0; i < Resultados.size(); i++) {
                resultado = resultado + Integer.parseInt(Resultados.get(i).toString());
            }
            dtm = new DefaultTableModel();
            dtm.addColumn("DondeTodo");
            dtm.addColumn("Funcion");
            dtm.addRow(new Object[2]);
            dtm.setValueAt("Todo", 0, 0);
            dtm.setValueAt(""+resultado, 0, 1);
        }

        tabla.setModel(dtm);
        datos.setVisible(true);
    }

    //-----------------------------------------------BUSQUEDAS-------------------------------------------
    public Object filtroDonde(JTable tabla, int columna, String filtro) {

        ArrayList<Vector> found = new ArrayList();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Vector encontrado = (Vector) dtm.getDataVector().elementAt(i);
            String v = (String) encontrado.get(columna - 1);
            if (v.equals(filtro.replace("\"", ""))) {
                found.add(encontrado);
            }
        }
        return found;
    }

    public Object filtroDondeCada(JTable tabla) {

        ArrayList<Vector> found = new ArrayList();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Vector encontrado = (Vector) dtm.getDataVector().elementAt(i);
            found.add(encontrado);
        }
        return found;
    }

    public Object filtroDondeTodo(JTable tabla) {

        ArrayList<Vector> found = new ArrayList();
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Vector encontrado = (Vector) dtm.getDataVector().elementAt(i);
            found.add(encontrado);
        }
        return found;
    }
}
