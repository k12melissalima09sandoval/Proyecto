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
public class Variable {
    public String nombre;
    public String valor;
    public int tipo;

    public Variable(int tipo, String nombre, String valor) {
        this.nombre = nombre;
        this.valor = valor;
        this.tipo = tipo;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString() {
        return "Variable: " + "nombre=" + nombre + ", valor=" + valor + ", tipo=" + tipo;
    }
}
