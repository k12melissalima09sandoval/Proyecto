/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

/**
 *
 * @author MishaPks
 */
public class Errores {
    String texto;
    String tipo;
    String descripcion;
    int linea;
    int columna;
    
    public Errores(String tipo, String descripcion, String texto, int linea,int columna ){
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.texto = texto;
        this.linea = linea;
        this.columna = columna;
        
    }
}
