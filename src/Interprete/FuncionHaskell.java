/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete;

import Ast.Nodo;

/**
 *
 * @author MishaPks
 */
public class FuncionHaskell {
    String nombre;
    public Object parametros;
    Nodo cuerpo;
    
    public FuncionHaskell(String nombre, Object Parametros, Nodo corpo){
        this.nombre = nombre;
        this.parametros = Parametros;
        this.cuerpo = corpo;
    }
    public FuncionHaskell(String nombre, Nodo corpo){
        this.nombre = nombre;
        this.cuerpo = corpo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Object getParametros() {
        return parametros;
    }

    public void setParametros(Object parametros) {
        this.parametros = parametros;
    }

    public Nodo getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(Nodo cuerpo) {
        this.cuerpo = cuerpo;
    }
    
    
}
