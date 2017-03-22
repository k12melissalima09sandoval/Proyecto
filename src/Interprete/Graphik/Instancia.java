/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interprete.Graphik;

/**
 *
 * @author MishaPks
 */
public class Instancia {
    
    String nombre;
    String nombreObjeto;
    Als cuerpoAls;
    
    public Instancia(String nombreObjeto, String nombre){
        this.nombre = nombre;
    }
    
    public void setInstancia(Als als){
        cuerpoAls = als;
    }
    
}
