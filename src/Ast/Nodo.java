/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Ast;

import java.util.ArrayList;

/**
 *
 * @author MishaPks
 */
public class Nodo {
    
    public ArrayList<Nodo> hijos = new ArrayList();
    public Object valor;
    
    public Nodo(){
    
    }
    public void Hijo(Nodo hijo){
        hijos.add(hijo);
    }
    
    public Nodo(Object valor){
        this.valor = valor;
        this.hijos = new ArrayList();
    }
    
    public void setNombre(Object nombre){
        this.valor = nombre;
    }
}
