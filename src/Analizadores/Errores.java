/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import Simbolos.TablaSimbolosGraphik;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;


/**
 *
 * @author MishaPks
 */
public class Errores {
    public String texto;
    public String tipo;
    public String descripcion;
    public int linea;
    public int columna;
    static TablaSimbolosGraphik tabla = new TablaSimbolosGraphik();
    public Errores(){
        
    }
            
    public Errores(String tipo, String texto, int linea,int columna ){
        this.tipo = tipo;
        this.texto = texto;
        this.linea = linea;
        this.columna = columna;
        
    }
    public Errores(String tipo, String descripcion, String texto, int linea,int columna ){
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.texto = texto;
        this.linea = linea;
        this.columna = columna;
        
    }
    
    public static void ErrorSemantico(String texto, int linea, int columna) {

        Errores error = new Errores("Semantico", texto, linea, columna);
        tabla.AgregarErrores(error);
    }
    
    
    public void imprimirErrores(ArrayList<Errores> listaErrores) 
    {
        FileWriter writer=null;
        PrintWriter pw  = null;
        
        try {
            writer = new FileWriter("errores.html");
            pw = new PrintWriter(writer);
            
            String a =GraficarTabla( listaErrores);
            pw.println(a);
            pw.println("\n ");
            pw.close();
            
            
        } catch (Exception ex) {
            
        }finally{
            if(writer!=null){
                try {
                    writer.close();
                    
                } catch (IOException ex) {
                    //Logger.getLogger(TablaSimbolosGraphik.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
     public String GraficarTabla(ArrayList<Errores> listaErrores) {
        String tablasimboloshtml = "";

        Calendar fecha = Calendar.getInstance();
        int año = fecha.get(Calendar.YEAR);
        int mes = fecha.get(Calendar.MONTH) + 1;
        int dia = fecha.get(Calendar.DAY_OF_MONTH);
        int hora = fecha.get(Calendar.HOUR_OF_DAY);
        int minuto = fecha.get(Calendar.MINUTE);
        int segundo = fecha.get(Calendar.SECOND);

        tablasimboloshtml += "<html>\n\t<head>\n\t\t<title>Errores</title>" + "<meta charset=" + "\"" + "utf-8" + "\"" + ">"
                + "\n\t\t<link rel=" + "\"" + "stylesheet" + "\"" + "type=" + "\"" + "text/css" + "\"" + " href=" + "\"" + "Estilo.css"
                + "\"" + ">\n\t</head>\n\t<body>"
                + "\n\t\t<div style=" + "\"" + "text-align:center;" + "\"" + ">"
                + "\n\t\t\t<h2>ERRORES</h2>"
                + "\n\t\t\t<h3>Fecha:" + dia + " de " + getMes(mes) + " de " + año + "</h3>"
                + "\n\t\t\t<h3>Hora:" + hora + ":" + minuto + ":" + segundo + " " + getHora(hora) + "</h3>"
                + "\n\t\t\t<center><table style=\"margin: margin: 5 auto;\" border=\"1\">\n";
        tablasimboloshtml += "\t\t\t\t<TR>\n\t\t\t\t\t<TH>Tipo</TH> <TH>Descripcion</TH> <TH>Fila</TH><TH>Columna</TH>\n\t\t\t\t</TR>";

        for (Errores nodo : listaErrores) {
            tablasimboloshtml += "\n\t\t\t\t<TR>";
            tablasimboloshtml += "\n\t\t\t\t\t<TD>" + nodo.tipo + "</TD>" + "<TD>" + nodo.texto + "</TD>" + "<TD>" + nodo.linea + "</TD>"+ "<TD>" + nodo.columna + "</TD>";
            tablasimboloshtml += "\n\t\t\t\t</TR>";
        }

        tablasimboloshtml += " \n\t\t\t<h2>Melissa Lima</h2>\n\t\t\t</table></center>\n\t\t</div>\n\t</body>\n</html>";
        return tablasimboloshtml;
    }
     
     public String getMes(int mes){
        String m="";
        switch (mes){
            case 1:
                m="Enero";
                break;
            case 2:
                m="Febrero";
                break;
            case 3:
                m="Marzo";
                break;
            case 4:
                m="Abril";
                break;
            case 5:
                m="Mayo";
                break;
            case 6:
                m="Junio";
                break;
            case 7:
                m="Julio";
                break;
            case 8:
                m="Agosto";
                break;
            case 9:
                m="Septiembre";
                break;
            case 10:
                m="Octubre";
                break;
            case 11:
                m="Noviembre";
                break;
            default:
                m="Diciembre";
        }
        return m;
        
    }
     
     
     public String getHora(int hora){
        String h="p.m.";
        if(hora<12){
            h="a.m.";
        }
        return h;
    }
    
    
}
