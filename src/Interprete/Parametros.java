/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

/**
 *
 * @author MishaPks
 */
public class Parametros {
    
    String tipo;
    String nombre;
    Object valor;
    
    public Parametros(String nombre){
        this.nombre = nombre;
    }
    
    public Parametros(String tipo,String nombre){
        this.tipo=tipo;
        this.nombre = nombre;
    }
    
    public void setValor(Object val){
        this.valor = val;
    }
    
}
