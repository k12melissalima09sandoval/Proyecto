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
    
    public String tipo;
    public String nombre;
    public Object valor;
    
    public Parametros(String nombre){
        this.nombre = nombre;
    }
    public Parametros(){
     
    }
    
    public Parametros(String tipo,String nombre){
        this.tipo=tipo;
        this.nombre = nombre;
    }
    
    
    public Parametros(Object val, String tipo){
        this.valor = val;
        this.tipo = tipo;
    }
    
    public void setValor(Object val){
        this.valor = val;
    }
    
}
