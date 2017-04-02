/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class Variable implements Cloneable{
   
    public String nombre;
    public String visibilidad;
    public Object valor;
    public String tipo;
    Boolean hereda = false;
    public Boolean arreglo;
    public ArrayList dimensiones;
    public Boolean instancia;

    public Variable(){
        
    }
    public Variable copiar(){
        try{
            Variable nueva = new Variable();
            Object v = new Object();
            v = this.valor;
            nueva.valor = v;
            nueva.nombre = this.nombre;
            nueva.visibilidad = this.visibilidad ;
            nueva.tipo = this.tipo;
            nueva.hereda = this.hereda;
            nueva.arreglo = this.arreglo;
            nueva.dimensiones = this.dimensiones;
            nueva.instancia = this.instancia;
            return nueva;
        }catch(Exception e){
            return null;
        }
    }
    
    public Variable(String tipo, String nombre, String visible, Object valor,
            Boolean arreglo,Boolean instancia) {
        this.tipo = tipo;
        this.nombre = nombre;
        this.visibilidad = visible;
        this.valor = valor;
        this.arreglo = arreglo;
        this.instancia = instancia;
        this.hereda = false;
    }
    
    
    public Variable(String nombre, Object valor, String tipoLista){
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipoLista;
    }
    
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setHereda(Boolean si) {
        this.hereda = si;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setDimensiones(ArrayList dim){
        this.dimensiones = dim;
    }
    
}
